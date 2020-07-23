package com.direction.core.modules.sys.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.dept.entity.Dept;

// ~ File Information
// ====================================================================================================================

@Repository
public interface DeptRespository extends JpaRepository<Dept, String>, JpaSpecificationExecutor<Dept> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID查询部门.
	 * 
	 * @param deptId
	 * @return
	 */
	@Query(value = "from Dept where deptId = ?1  and status='0'")
	Dept get(String deptId);
}
