package com.direction.core.modules.sys.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.company.entity.Company;

// ~ File Information
// ====================================================================================================================

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>, JpaSpecificationExecutor<Company> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据父级Codes 删除所有下级.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Modifying
	@Query("delete from Company where parentIds like ?1%")
	int deleteSubsByParentIds(String parentIds);
	
	/**
	 * 根据父级ID查询直接下级.
	 * 
	 * @param parentId
	 * @return
	 */
	@Query("from Company where parentId = ?1 order by displayOrder asc")
	List<Company> getChildrens(String parentId);
	
	/**
	 * 根据父级ID查询所有下级.
	 * 
	 * @param parentId
	 * @return
	 */
	@Query("from Company where parentIds like %?1% order by displayOrder asc")
	List<Company> getAllChildrens(String parentId);
	
	/**
	 * 根据父级ID查询所有下级.
	 * 
	 * @param parentId
	 * @return
	 */
	@Query("select companyId from Company where parentIds like %?1% ")
	List<String> getChildIds(String parentId);
}
