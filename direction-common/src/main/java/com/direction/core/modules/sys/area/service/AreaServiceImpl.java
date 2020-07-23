package com.direction.core.modules.sys.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.common.tree.Cascader;
import com.direction.core.inf.sys.area.IAreaService;

// ~ File Information
// ====================================================================================================================

@Service
public class AreaServiceImpl implements IAreaService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private AreaService areaServcie;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取区域级联信息.
	 * 
	 * @param areaCode
	 * @return
	 */
	@Override
	public List<Cascader> getAreaCascader(String areaCode) {

		return areaServcie.getAreaCascader(areaCode);
	}
}
