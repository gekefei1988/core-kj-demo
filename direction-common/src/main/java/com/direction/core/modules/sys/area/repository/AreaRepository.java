package com.direction.core.modules.sys.area.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.area.entity.Area;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface AreaRepository extends BaseRepository<Area, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据ID查询区域.
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "from Area where id = ?1")
	Area get(String id);
	
	/**
	 * 根据Code查询区域.
	 * 
	 * @param areaCode
	 * @return
	 */
	@Query(value = "from Area where areaCode = ?1")
	Area getAreaByCode(String areaCode);
	
	/**
	 * 删除所有下级.
	 * 
	 * @param parentCodes
	 * @return
	 */
	@Modifying
	@Query("delete from Area where parentCodes like ?1")
	int deleteAllSubs(String parentCodes);

	/**
	 * 删除区域-假删除.
	 * 
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Area t set t.status = '1'  where t.areaCode = ?1 ")
	int deleteByIds(String id);
}
