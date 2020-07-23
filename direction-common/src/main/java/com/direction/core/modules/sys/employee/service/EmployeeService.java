package com.direction.core.modules.sys.employee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.common.result.ResultJson;
import com.direction.core.inf.EIPService;
import com.direction.core.inf.UserDataPermsUtils;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.service.AreaService;
import com.direction.core.modules.sys.employee.entity.Employee;
import com.direction.core.modules.sys.employee.repository.EmployeeRepository;
import com.direction.core.modules.sys.org.entity.OrgTreeNode;
import com.direction.core.modules.sys.org.service.OrgTreeNodeService;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 员工业务逻辑层.
 * 
 * <pre>
 * 	员工业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class EmployeeService extends BaseService<EmployeeRepository, Employee, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private OrgTreeNodeService orgTreeNodeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================


	/**
	 * 保存员工信息.
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public Employee save(Employee employee) {
		
		if (employee == null) {
			throw new ServiceException("员工信息为空");
		}

		// 判断机构
		if (StringUtils.isBlank(employee.getOrgId())) {
			throw new ServiceException("请选择一个机构!");
		}
		else {

			OrgTreeNode orgNode = orgTreeNodeService.get(employee.getOrgId());
			if (orgNode == null) {
				throw new ServiceException("您所选的机构不存在!");
			}
			
			employee.setOrgId(orgNode.getOrgId());
			employee.setOrgIds(orgNode.getParentIds() + "," + orgNode.getId());
			employee.setTenantId(orgNode.getTenantId());
		}
		
		// 区域
		if (StringUtils.isNotBlank(employee.getAreaCodes())) {
			String[] areaCodes = StringUtils.split(employee.getAreaCodes(), ",");
			if (areaCodes.length > 0) {
				Area area = this.areaService.getAreaByCode(areaCodes[areaCodes.length - 1]);
				employee.setAreaNames(area.getAllAreaName());
			}
		}

		this.getBaseRepository().save(employee);
		
		// 如果员工离职 或者 停职 将禁用账号
		if (!StringUtils.equals(employee.getStatus(), IUserInfo.UserStatus.ENABLE)) {
			this.userService.updateUserStatusByEmp(IUserInfo.UserStatus.DISABLED, employee.getEmpId());
		}
		return employee;
	}
	
	/**
	 * 开通账号.
	 * 
	 * @param openAccount
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void openAccount(boolean openAccount, User user) {

		try {
			
			Employee employee = this.get(user.getEmpId());
			
			if (employee == null) {
				throw new ServiceException("员工不存在, 不能根据员工信息开通账号!");
			}
			
			String password = new Md5Hash(user.getUserName() + user.getPassword()).toHex().toString();
			
			// 获取用户
			User baseUser = this.userService.getUserByEmp(employee.getEmpId());
			if (baseUser != null) {
				baseUser.setAllowMobile(user.isAllowMobile());
				baseUser.setAllowPC(user.isAllowPC());
				baseUser.setAllowWechat(user.isAllowWechat());
				baseUser.setStatus(openAccount ? IUserInfo.UserStatus.ENABLE : IUserInfo.UserStatus.DISABLED);
				baseUser.setUserType(IUserInfo.UserType.EMPLOYEE);
				baseUser.setOrgId(employee.getOrgId());
				baseUser.setOrgIds(employee.getOrgIds());
				baseUser.setUserName(user.getUserName());
				baseUser.setTenantId(employee.getTenantId());
				baseUser.setAvatar(user.getAvatar());
				baseUser.setAvatarId(user.getAvatarId());
				baseUser.setBirthday(employee.getBirthday());
				
				// 如果用户已存在, 比对用户密码, 确定是否修改密码
				if (this.userService.comparisonPassword(user.getUserId(), user.getPassword()) == 0) {
					baseUser.setPassword(password);
					baseUser.setLastPwdUpdate(new Date());
				}
				
				this.userService.save(baseUser);
			}
			else if (openAccount) {
				user.setStatus(IUserInfo.UserStatus.ENABLE);
				user.setEmpName(employee.getEmpName());
				user.setNickName(employee.getEmpName());
				user.setOrgId(employee.getOrgId());
				user.setOrgIds(employee.getOrgIds());
				user.setPassword(password);
				user.setRegPassword(password);
				user.setTenantId(employee.getTenantId());
				user.setBirthday(employee.getBirthday());
				this.userService.save(user);
			}
			
			// 设置账号开通状态
			employee.setOpenAccount(openAccount);
			this.getBaseRepository().save(employee);
			
		} catch (Exception e) {
			throw new ServiceException("员工信息保存失败, 错误信息:" + e.getMessage());
		}
	}

	/**
	 * 人员分页.
	 * 
	 * @param employee
	 * @return
	 */
	public VuePage<Employee> findPage(Employee employee, VuePage<Employee> page) {
		
		// 封装查询条件
		Specification<Employee> empParams = new Specification<Employee>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				// 权限
				predicateList.addAll(UserDataPermsUtils.getDataPermPredicates("orgId", "empId", "EMPLOYEE", root, criteriaBuilder));
				
				if (employee != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(employee.getEmpName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("empName").as(String.class), "%" + employee.getEmpName() + "%"),
						    criteriaBuilder.equal(root.get("empNo").as(String.class), employee.getEmpName())));
					}
					
					// 机构
					if (StringUtils.isNotBlank(employee.getOrgId())) {
						IOrgTreeNode node = EIPService.getOrgTreeService().getOrgTreeNode(employee.getOrgId());
						if (node != null) {
							predicateList.add(criteriaBuilder.like(root.get("orgIds").as(String.class), node.getParentIds() + "," + node.getOrgId() + "%"));
						}
					}
					
					// 账号开通
					if (employee.getOpenAccount() != null) {
						predicateList.add(criteriaBuilder.equal(root.get("openAccount").as(Boolean.class), employee.getOpenAccount()));
					}

					// 判断状态
					if (StringUtils.isNotBlank(employee.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), employee.getStatus()));
					}

				}
				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<Employee> AreaPage = getBaseRepository().findAll(empParams, page.getPageable());
		page.setPage(AreaPage);

		return page;
	}

	/**
	 * 验证属性是否唯一.
	 * 
	 * @param propName
	 * @param propValue
	 * @param empId
	 * @return
	 */
	public ResultJson validateOnlyOne(String propName, String propValue, String empId) {

		if (StringUtils.isAnyBlank(propName, propValue)) {
			return ResultJson.success();
		}

		Specification<Employee> params = new Specification<Employee>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				// id是否为空, 如果为空则是新增, 如果不为空则是修改
				if (StringUtils.isNotBlank(empId)) {

					return criteriaBuilder.and(criteriaBuilder.notEqual(root.get("empId").as(String.class), empId),
					    criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				} else {
					return criteriaBuilder.and(criteriaBuilder.equal(root.get(propName).as(String.class), propValue));
				}
			}
		};

		if (this.getBaseRepository().count(params) == 0) {
			return ResultJson.success();
		}

		return ResultJson.fail("您输入的内容已存在, 请重新输入");
	}

	/**
	 * 删除员工信息.
	 * 
	 * @param empId
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String empId) {
		
		if (StringUtils.isBlank(empId)) {
			throw new ServiceException("删除员工失败, 该员工已不存在");
		}

		try {
			this.getBaseRepository().deleteById(empId);
			this.userService.deleteUserByEmp(empId);
		} catch (Exception e) {
			throw new ServiceException("删除员工失败, 错误信息:" + e.getMessage());
		}
	}
}
