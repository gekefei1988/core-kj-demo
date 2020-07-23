package com.direction.core.modules.sys.area.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import com.direction.core.modules.sys.area.entity.base.BaseArea;

// ~ File Information
// ====================================================================================================================

/**
 * 区域.
 * 
 * <pre>
 * 	区域
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_area")
public class Area extends BaseArea {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getAllAreaName() {
		return RegExUtils.replaceAll(this.getParentNames(), ",", "") + this.getAreaName();
	}
	
	public String getAreaTypeDesc() {
		if (StringUtils.isBlank(this.getAreaType())) {
			this.setAreaType(AreaType.TYPE_1);
		}
		
		return AreaType.getAreaTypeMap().get(this.getAreaType());
	}

	public static class AreaType {
		public static final String TYPE_1 = "1";
		public static final String TYPE_2 = "2";
		public static final String TYPE_3 = "3";
		public static final String TYPE_4 = "4";
		public static final String TYPE_5 = "5";
		
		public static Map<String, String> getAreaTypeMap() {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put(TYPE_1, "省份直辖市");
			maps.put(TYPE_2, "地市");
			maps.put(TYPE_3, "区县");
			maps.put(TYPE_4, "镇街");
			maps.put(TYPE_5, "村级");
			return maps;
		}
	} 
}
