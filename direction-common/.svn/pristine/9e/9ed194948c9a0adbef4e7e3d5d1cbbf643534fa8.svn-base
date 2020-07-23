package com.direction.core.modules.sys.dict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.dict.entity.DictType;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 数据字典数据层.
 * 
 * <pre>
 * 	操作数据字典的相关方法.
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface DictTypeRepository extends BaseRepository<DictType, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据ID获取数据.
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "from DictType where id = ?1")
	DictType get(String id);
	
	/**
	 * 删除数据-假删除.
	 * 
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("delete from DictType where id = ?1 ")
	int delete(String id);
	
	/**
	 * 批量删除数据-假删除.
	 * 
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("delete DictType where id in (?1)")
	int batchDeleteByIds(List<String> ids);
	
	/**
	 * 根据字典类型获取数据.
	 * 
	 * @param dictType
	 * @return
	 */
	@Query(value = "from DictType where dictType = ?1")
	DictType getByType(String dictType);
}
