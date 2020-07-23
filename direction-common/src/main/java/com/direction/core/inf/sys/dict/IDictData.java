package com.direction.core.inf.sys.dict;

// ~ File Information
// ====================================================================================================================

/**
 * 字典数据.
 * 
 * <pre>
 * 字典数据
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public interface IDictData {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 字典数据id.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 字典类型.
	 * 
	 * @return
	 */
	String getDictType();

	/**
	 * 字典数据上级id.
	 * 
	 * @return
	 */
	String getParentId();

	/**
	 * 字典数据显示名称.
	 * 
	 * @return
	 */
	String getDictLabel();

	/**
	 * 字典数据值.
	 * 
	 * @return
	 */
	String getDictValue();

	/**
	 * 是否末级.
	 * 
	 * @return
	 */
	Boolean isLeaf();

	/**
	 * 状态.
	 * 
	 * @return
	 */
	String getStatus();
	
	int getLevel();

	/**
	 * 排序.
	 * 
	 * @return
	 */
	Integer getDisplayOrder();

	String getCssClass();
	
	boolean isDefaultValue();

	String getExt1();

	String getExt2();

	String getExt3();

	String getExt4();

	String getExt5();

	String getExt6();
}
