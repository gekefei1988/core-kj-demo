package com.direction.core.modules.sys.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.user.entity.UserOnline;

// ~ File Information
// ====================================================================================================================

@Repository
public interface UserOnlineRepository extends JpaRepository<UserOnline, String>, JpaSpecificationExecutor<UserOnline> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================


	
	@Query(value = "from UserOnline where userId = ?1")
	UserOnline selectByUserId(String id);
	
	@Query(value = "from UserOnline where loginName = ?1")
	UserOnline selectByLoginName(String loginName);
	



	
	


}
