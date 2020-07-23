package com.direction.core.inf.sys.dict;

import java.util.List;
import java.util.Map;

// ~ File Information
// ====================================================================================================================

/**
 * 数据字典对外api接口.
 * 
 * <pre>
 * 	数据字典对外api接口
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public interface IDictService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 根据ID获取字典表数据.
	 * 
	 * @param id
	 * @return
	 */
	DictTableItem getDictTableItem(String id);
	
	/**
	 * 获取字典表.
	 * 
	 * @param dictType
	 * @return
	 */
	DictTable getDictTable(String dictType);
	
	/**
	 * 获取字典元素值.
	 * 
	 * @param type
	 * @param level
	 * @param value
	 * @return
	 */
	List<DictTableItem> getDictTableItems(String type, int level, String value);
	
	/**
	 * 获取字典数据.
	 * 
	 * @param type
	 * @return
	 */
	Map<String, String> getTopValueMap(String type);
	
	/**
	 * 获取字典数据.
	 * 
	 * @param type
	 * @return
	 */
	Map<String, DictTableItem> getTopItemMap(String type);
	
	/**
	 * 获取字典数据.
	 * 
	 * @param type
	 * @param level
	 * @param value
	 * @return
	 */
	Map<String, String> getValueMap(String type, int level, String value);
	
	/**
	 * 获取顶级节点默认值.
	 * 
	 * @param type
	 * @return
	 */
	String getTopDefaultValue(String type);
	
	/**
	 * 获取默认值.
	 * 
	 * @param type
	 * @param level
	 * @param value
	 * @return
	 */
	String getDefaultValue(String type, int level, String value);
	
	/**
	 * 获取字典数据.
	 * 
	 * @param type
	 * @param level
	 * @param value
	 * @return
	 */
	Map<String, DictTableItem> getItemMap(String type, int level, String value);
	
	/**
	 * 获取该类型下所有数据.
	 * 
	 * @param type
	 * @return
	 */
	Map<String, String> getAllValueMap(String type);
	
	/**
	 * 获取该类型下所有数据.
	 * 
	 * @param type
	 * @return
	 */
	Map<String, DictTableItem> getAllItemsMap(String type);
}
