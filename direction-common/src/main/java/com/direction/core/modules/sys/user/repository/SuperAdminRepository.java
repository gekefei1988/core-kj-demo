package com.direction.core.modules.sys.user.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.user.entity.SuperAdmin;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 超级管理员.
 * 
 * <pre>
 * 	超级管理员
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface SuperAdminRepository extends BaseRepository<SuperAdmin, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据账号查询超级用户.
	 * 
	 * @param userName
	 * @return
	 */
	@Query("from SuperAdmin where userName = ?1")
	SuperAdmin getByUserName(String userName);
	
	/**
	 * 根据账号查询用户.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@Query(value = "from SuperAdmin where userName = ?1 and password = ?2 ")
	SuperAdmin getSuperAdminBy(String userName, String password);
	
	/**
	 * 查询是否存在该用户和密码.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@Query("select count(userId) from SuperAdmin where userId = ?1 and password = ?2")
	long comparisonPassword(String userId, String password);
	
	/**
	 * 获取有错误次数的用户.
	 * 
	 * @param logginErrorDate
	 * @return
	 */
	@Modifying
	@Query("update SuperAdmin set loginErrorCount = 0 where loginErrorDate < ?1 and loginErrorCount > 0")
	int resetByErrorDate(Date logginErrorDate);
}
