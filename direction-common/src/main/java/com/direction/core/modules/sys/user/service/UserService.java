package com.direction.core.modules.sys.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.common.utils.codec.Md5Utils;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.UserDataPermsUtils;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.inf.sys.user.IUserInfo.UserStatus;
import com.direction.core.inf.sys.user.IUserInfo.UserType;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.service.OrgTreeNodeService;
import com.direction.core.modules.sys.tenant.entity.Tenant;
import com.direction.core.modules.sys.tenant.entity.TenantReg;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.repository.UserRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<UserRepository, User, String> {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SuperAdminService superAdminService;

	@Autowired
	private OrgTreeNodeService orgTreeNodeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据员工ID查询用户.
	 * 
	 * @param empId
	 * @return
	 */
	public User getUserByEmp(String empId) {

		return this.userRepository.getUserByEmp(empId);
	}

	/**
	 * 根据账号查询用户.
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName) {

		return this.userRepository.getUserByUserName(userName);
	}

	/**
	 * 修改登陆次数.
	 * 
	 * @param userName
	 */
	@Async
	@Transactional(readOnly = false)
	public void loginErrorCount(String userName) {

		User user = this.getUserByUserName(userName);
		user.setLoginErrorCount(user.getLoginErrorCount() + 1);
		user.setLoginErrorDate(new Date());
		this.userRepository.save(user);
	}

	/**
	 * 修改登陆次数.
	 * 
	 * @param userName
	 */
	@Transactional(readOnly = false)
	public void resetLoginErrorCount(String userName) {
		if (StringUtils.isNotBlank(userName)) {
			User user = this.getUserByUserName(userName);
			if (user != null) {
				user.setLoginErrorCount(0);
				this.userRepository.save(user);
			}
		}
		else {
			int count = this.userRepository.resetByErrorDate(new Date());
			LoggerFactory.getLogger(UserService.class).debug("重置密码错误, 重置用户: " + count);
		}
	}
	
	/**
	 * 修改最后登陆日期.
	 * 
	 * @param userId
	 */
	@Async
	@Transactional(readOnly = false)
	public void updateLoginDate(String userId) {
		User user = this.get(userId);
		if (user != null) {
			user.setLastLoginDate(new Date());
			this.userRepository.save(user);
		}
	}

	/**
	 * 根据账号和密码查询用户.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User getUserBy(String userName, String password) {

		return this.userRepository.getUserBy(userName, password);
	}

	/**
	 * 比对密码.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public long comparisonPassword(String userId, String password) {

		return this.userRepository.comparisonPassword(userId, password);
	}

	/**
	 * 保存用户信息.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public User save(User user) {

		try {

			OrgTreeNode orgNode = this.orgTreeNodeService.get(user.getOrgId());

			if (orgNode == null) {
				throw new ServiceException("未获取到机构");
			} else {
				user.setOrgIds(orgNode.getParentIds() + "," + orgNode.getOrgId());
			}

			// 用户类型
			if (StringUtils.isBlank(user.getUserType())) {
				user.setUserType(IUserInfo.UserType.EMPLOYEE);
			}

			// 判断密码
			if (StringUtils.isNotBlank(user.getUserId())) {

				// 如果用户已存在, 比对用户密码, 确定是否修改密码
				if (this.userRepository.comparisonPassword(user.getUserId(), user.getPassword()) == 0) {
					user.setLastPwdUpdate(new Date());
				}
			}

			this.userRepository.save(user);
			return user;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 修改用户信息.
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public User modifyMyUser(User user) {
		if(user == null) {
			throw new ServiceException("用户为空，不能进行修改");
		}
		else if (StringUtils.isBlank(user.getUserId())) {
			throw new ServiceException("用户主键为空，不能进行修改");
		}
		
		// 获取原始User
		User baseUser = this.get(user.getUserId());
		
		if (baseUser == null) {
			throw new ServiceException("该用户对象已不存在，不能进行修改");
		}
		
		baseUser.setUserName(user.getUserName());
		baseUser.setNickName(user.getNickName());
		baseUser.setAvatar(user.getAvatar());
		baseUser.setAvatarId(user.getAvatarId());
		baseUser.setAllowMobile(user.isAllowMobile());
		baseUser.setAllowPC(user.isAllowPC());
		baseUser.setAllowWechat(user.isAllowWechat());
		baseUser.setSign(user.getSign());
		baseUser.setStatus(user.getStatus());
		
		this.userRepository.save(baseUser);
		
		return baseUser;
	}
	
	/**
	 * 修改密码.
	 * 
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @param surePassword
	 * @return
	 */
	@Transactional(readOnly = false)
	public User modifyUserPWD(String userId, String password, String newPassword, String surePassword) {
		
		if(StringUtils.isAnyBlank(userId, password, newPassword, surePassword)) {
			throw new ServiceException("参数为空，不能进行修改");
		}
		else if (!StringUtils.equals(newPassword, surePassword)) {
			throw new ServiceException("新密码和确认密码不相同，不能进行修改");
		}
		
		// 获取原始User
		User baseUser = this.get(userId);
		
		if (baseUser == null) {
			throw new ServiceException("该用户对象已不存在，不能进行修改");
		}
		
		// 判断密码是否正确
		if (!StringUtils.equals(password, baseUser.getPassword())) {
			throw new ServiceException("登录密码错误，不能进行修改");
		}
		
		baseUser.setPassword(newPassword);
		this.userRepository.save(baseUser);
		
		return baseUser;
	}
	
	/**
	 * 重置该用户密码为默认.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public User resetPWD(String userId) {
		
		String resetPwd = "000000";
		
		// 获取系统配置数据
		IConfig config = EIPService.getConfigService().getConfig("sys_reset_pwd_value");
		
		if (config != null) {
			resetPwd = config.getConfigValue();
		}
		
		// 获取原始User
		User baseUser = this.get(userId);
		
		if (baseUser == null) {
			throw new ServiceException("该用户对象已不存在，不能进行密码重置");
		}
		resetPwd = new Md5Hash(baseUser.getUserName() + resetPwd).toHex().toString();
		baseUser.setPassword(resetPwd);
		
		return this.userRepository.save(baseUser);
	}

	/**
	 * 修改临时租户的显示名称.
	 * 
	 * @param tenantReg
	 */
	@Transactional(readOnly = false)
	public void saveTenantUser(TenantReg tenantReg) {

		if (tenantReg == null) {
			throw new ServiceException("租户为空");
		}

		User user = userRepository.getUserByProps(tenantReg.getId(), UserType.TENANT);

		if (user == null) {
			throw new ServiceException("未获取到临时租户相关用户信息");
		}
		user.setNickName(tenantReg.getTenantName());
		user.setEmpName(tenantReg.getTenantName());

		this.userRepository.save(user);
	}
	
	/**
	 * 修改临时租户信息.
	 * 
	 * @param tenant
	 */
	@Transactional(readOnly = false)
	public User updateUserByTenant(Tenant tenant) {

		if (tenant == null || StringUtils.isBlank(tenant.getId())) {
			throw new ServiceException("未获取到租户信息");
		}

		// 临时租户
		User user = this.userRepository.getUserByProps(tenant.getId(), UserType.TENANT);

		if (user == null) {
			// 租户管理员
			user = this.userRepository.getUserByProps(tenant.getId(), UserType.MANAGE);
			
			if (user == null) {
				logger.info("未获取到临时租户或者租户管理员信息, 开始创建租户管理员");
				user = this.createUserByTenant(tenant, false);
			}
		}

		// 获取组织结构节点
		List<OrgTreeNode> treeNodes = this.orgTreeNodeService.getDefaultTopTreeNodes(tenant.getId());
		if (treeNodes == null || treeNodes.size() == 0) {
			throw new ServiceException("未获取到组织树相关信息");
		}
		
		// 赋值节点
		user.setOrgId(treeNodes.get(0).getOrgId());
		
		this.save(user);
		return user;
	}

	/**
	 * 根据租户注册信息, 创建用户.
	 * 
	 * @param reg
	 */
	@Transactional(readOnly = false)
	public User createUserByReg(TenantReg reg) {

		User user = new User();
		user.setAllowPC(true);
		user.setAllowMobile(false);
		user.setAllowWechat(false);
		user.setNickName(reg.getTenantName());
		user.setUserName(this.autoComptUserName(reg.getTelephone()));
		user.setUserType(UserType.TENANT);
		user.setStatus(UserStatus.ENABLE);
		user.setTenantId(reg.getId());
		user.setPassword(reg.getPassword());
		user.setRegPassword(reg.getInitPwd());

		this.userRepository.save(user);

		return user;
	}
	
	/**
	 * 根据租户信息, 创建用户.
	 * 
	 * @param tenant
	 * @return
	 */
	@Transactional(readOnly = false)
	public User createUserByTenant(Tenant tenant, boolean autoSave) {

		User user = new User();
		user.setAllowPC(true);
		user.setAllowMobile(false);
		user.setAllowWechat(false);
		user.setNickName(tenant.getTenantName());
		user.setUserName(this.autoComptUserName(tenant.getTelephone()));
		user.setUserType(UserType.MANAGE);
		user.setStatus(UserStatus.ENABLE);
		user.setTenantId(tenant.getId());
		user.setPassword(tenant.getPassword());
		user.setRegPassword(tenant.getInitPwd());
		
		// 是否需要保存
		if (autoSave) {
			this.userRepository.save(user);
		}

		return user;
	}
	
	/**
	 * 自动计算用户名, 如果存在则加入随机码.
	 * 
	 * @param userName
	 * @return
	 */
	private String autoComptUserName(String userName) {
		
		String result = userName;
		
		// 如果用户名存在
		if (this.isExistsUser(result)) {
			
			// 随机数
			Random rand = new Random();
			
			// 循环随机数找寻不存在的用户.
			while (true) {
				result = userName + StringUtils.leftPad(String.valueOf(rand.nextInt(1000)), 3, "0");
				
				// 如果不存在返回用户
				if (!this.isExistsUser(result)) {
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 检查用户名是否存在.
	 * 
	 * @return
	 */
	private boolean isExistsUser(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			throw new ServiceException("用户名为空, 不能进行检核");
		}
		
		return this.userRepository.getCountByUserName(userName) > 0;
	}

	/**
	 * 用户分页.
	 * 
	 * @param user
	 * @return
	 */
	public VuePage<User> findPage(User user, VuePage<User> page) {

		// 封装查询条件
		Specification<User> params = new Specification<User>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				// 增加权限
				predicateList.addAll(UserDataPermsUtils.getDataPermPredicates("orgId", "userId", root, criteriaBuilder));

				if (user != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(user.getUserName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("empName").as(String.class), "%" + user.getUserName() + "%"),
						    criteriaBuilder.like(root.get("userName").as(String.class), "%" + user.getUserName() + "%")));
					}

					// 机构
					if (StringUtils.isNotBlank(user.getOrgId())) {
						IOrgTreeNode node = EIPService.getOrgTreeService().getOrgTreeNode(user.getOrgId());
						if (node != null) {
							predicateList.add(criteriaBuilder.like(root.get("orgIds").as(String.class),
							    node.getParentIds() + "," + node.getOrgId() + "%"));
						}
					}

					// 判断状态
					if (StringUtils.isNotBlank(user.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), user.getStatus()));
					}

					// 判断用户类型
					if (StringUtils.isNotBlank(user.getUserType())) {
						predicateList.add(criteriaBuilder.equal(root.get("userType").as(String.class), user.getUserType()));
					} else {
						predicateList
						  .add(criteriaBuilder.equal(root.get("userType").as(String.class), IUserInfo.UserType.EMPLOYEE));
					}
					
					// 租户
					if (StringUtils.isNotBlank(user.getTenantId())) {
						predicateList.add(criteriaBuilder.equal(root.get("tenantId").as(String.class), user.getTenantId()));
					}
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<User> userPage = userRepository.findAll(params, page.getPageable());
		page.setPage(userPage);

		return page;
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param userId
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String userId) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<User> params = new Specification<User>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(userId)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("userId").as(String.class), userId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		if (this.userRepository.count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您填写的内容已存在, 请重新输入");
	}

	/**
	 * 获取所有用户, 不区分权限.
	 * 
	 * @return
	 */
	public List<User> getUsersByTenant(String tenantId) {

		return this.userRepository.getUsersByTenant(tenantId);
	}

	/**
	 * 获取所有用户, 不区分权限.
	 * 
	 * @return
	 */
	public List<User> getUsersByTenant() {

		UserProfile user = UserProfileUtils.getUserProfile();

		if (user != null) {
			return this.userRepository.getUsersByTenant(user.getTenantId());
		}
		return new ArrayList<User>();
	}

	/**
	 * 检测用户是否唯一.
	 * 
	 * @param userId
	 * @param propValue
	 * @return
	 */
	public ResultJson checkUserName(String userId, String propValue) {

		ResultJson result = this.validateOnlyOne("userName", propValue, userId);

		// 验证成功后, 验证超级管理员
		if (result.isSuccess()) {
			result = this.superAdminService.validateOnlyOne("userName", propValue, userId);
			if (result.isSuccess()) {
				return result;
			}
		}
		return ResultJson.fail("您输入的账号已存在, 请重新输入");
	}

	/**
	 * 根据员工ID删除用户.
	 * 
	 * @param empId
	 */
	@Transactional(readOnly = false)
	public void deleteUserByEmp(String empId) {

		this.userRepository.deleteUserByEmp(empId);
	}

	/**
	 * 根据员工修改用户状态.
	 * 
	 * @param status
	 * @param empId
	 */
	@Transactional(readOnly = false)
	public void updateUserStatusByEmp(String status, String empId) {

		this.userRepository.updateUserStatusByEmp(status, empId);
	}

	/**
	 * 删除用户.
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String userId) {

		if (StringUtils.isBlank(userId)) {
			throw new ServiceException("删除用户信息失败, 该用户已不存在");
		}

		try {
			this.userRepository.deleteById(userId);
		} catch (Exception e) {
			throw new ServiceException("删除用户信息失败");
		}
	}

	/**
	 * 根据openid创建用户.
	 * 
	 * @param openid
	 * @return
	 */
	public User saveUserByOpenid(String openid) {

		User user = new User();
		user.setUserName(openid);
		this.save(user);

		return user;
	}
}
