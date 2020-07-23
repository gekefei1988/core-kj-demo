package com.direction.core.modules.sys.dict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.dict.entity.DictData;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface DictDataRepository extends BaseRepository<DictData, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据字典类型删除所有字典数据.
	 * 
	 * @param dictType
	 * @return
	 */
	@Modifying
	@Query("delete from DictData where dictType = ?1 ")
	public int deleteByDictType(String dictType);

	/**
	 * 查询所有下级.
	 * 
	 * @param dictType
	 * @return
	 */
	@Query("from DictData where dictType=?1 order by displayOrder")
	List<DictData> findAllByType(String dictType);
	
	/**
	 * 查询字典数据的所有下级.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Query("from DictData where parentIds like CONCAT(?1, '%') order by displayOrder")
	List<DictData> findAllDatasByParents(String parentIds);
	
	/**
	 * 删除字典数据的所有下级.
	 * 
	 * @param parentIds
	 * @return
	 */
	@Modifying
	@Query("delete from DictData where parentIds like CONCAT(?1, '%')")
	int deleteAllDatasByParents(String parentIds);
	
	/**
	 * 更新默认值.
	 * 
	 * @param dictType
	 * @param parentId
	 * @param defaultValue
	 * @return
	 */
	@Modifying
	@Query("update DictData set defaultValue = :defaultValue where dictTypeId = :dictTypeId and parentId = :parentId")
	int updateDefaultValue(@Param("dictTypeId") String dictTypeId, @Param("parentId") String parentId, @Param("defaultValue") boolean defaultValue);
}
