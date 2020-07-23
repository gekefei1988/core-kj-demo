package com.direction.core.inf.sys.area;

import java.util.List;

import com.direction.common.tree.Cascader;

// ~ File Information
// ====================================================================================================================

public interface IAreaService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取所有区域.
	 * 
	 * @param areaCode
	 * @return
	 */
	List<Cascader> getAreaCascader(String areaCode);
}
