package com.direction.core.modules.sys.user.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.user.entity.User;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface UserRepository extends BaseRepository<User, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据账号查询用户.
	 * 
	 * @param userName
	 * @return
	 */
	@Query(value = "from User where userName = ?1")
	User getUserByUserName(String userName);
	
	/**
	 * 查询用户个数.
	 * 
	 * @param userName
	 * @return
	 */
	@Query(value = "select count(userId) from User where userName = ?1")
	int getCountByUserName(String userName);
	
	/**
	 * 根据员工ID获取用户.
	 * 
	 * @param empId
	 * @return
	 */
	@Query(value = "from User where empId = ?1")
	User getUserByEmp(String empId);
	
	/**
	 * 根据员工ID删除用户.
	 * 
	 * @param empId
	 * @return
	 */
	@Modifying
	@Query(value = "delete from User where empId = ?1")
	int deleteUserByEmp(String empId);
	
	/**
	 * 根据员工ID修改用户状态.
	 * 
	 * @param status
	 * @param empId
	 * @return
	 */
	@Modifying
	@Query(value = "update User set status = ?1 where empId = ?2")
	int updateUserStatusByEmp(String status, String empId);
	
	/**
	 * 根据账号查询用户.
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	@Query(value = "from User where userName = ?1 and password = ?2")
	User getUserBy(String loginName, String password);
	
	/**
	 * 获取租户管理员.
	 * 
	 * @param tenantId
	 * @param userType
	 * @return
	 */
	@Query(value = "from User where tenantId = ?1 and userType = ?2")
	User getUserByProps(String tenantId, String userType);
	
	/**
	 * 获取租户管理员.
	 * 
	 * @param tenantId
	 * @param userTypes
	 * @return
	 */
	@Query(value = "from User where tenantId = ?1 and userType in ?2")
	List<User> getUserByProps(String tenantId, List<String> userTypes);

	/**
	 * 查询是否存在该用户和密码.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@Query("select count(userId) from User where userId = ?1 and password = ?2")
	long comparisonPassword(String userId, String password);
	
	/**
	 * 查询所有启用的用户.
	 * 
	 * @param tenantId
	 * @return
	 */
	@Query(value = "from User where status='0' and tenantId = ?1 order by createDate desc")
	List<User> getUsersByTenant(String tenantId);
	
	/**
	 * 获取有错误次数的用户.
	 * 
	 * @param logginErrorDate
	 * @return
	 */
	@Modifying
	@Query("update User set loginErrorCount = 0 where loginErrorDate < ?1 and loginErrorCount > 0")
	int resetByErrorDate(Date logginErrorDate);
}
