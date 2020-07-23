package com.direction.core.modules.sys.menu.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.WeightsConst;
import com.direction.core.modules.sys.menu.entity.base.BaseMenu;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_menu")
public class Menu extends BaseMenu {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getWeightsDesc () {
		return WeightsConst.getWeightsDesc(this.getWeights());
	}
	
	public List<Integer> getWeightsValue() {
		return WeightsConst.getWeights(this.getWeights());
	}
	
	/**
	 * 是否显示.
	 * 
	 * @return
	 */
	public boolean isVisibled() {
		if (StringUtils.isNotBlank(this.getVisible()) && "T".equals(this.getVisible())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否使用缓存.
	 * 
	 * @return
	 */
	public boolean isUsedCache() {
		if (StringUtils.isNotBlank(this.getUseCache()) && "T".equals(this.getUseCache())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否显示目录.
	 * 
	 * @return
	 */
	public boolean isShowedFolder() {
		if (StringUtils.isNotBlank(this.getShowFolder()) && "T".equals(this.getShowFolder())) {
			return true;
		}
		return false;
	}
	
	public String getMenuTypeDesc() {
		if (StringUtils.isNotBlank(this.getMenuType())) {
			return MenuType.getMenuTypeMap().get(this.getMenuType());
		}
		
		return MenuType.getMenuTypeMap().get(MenuType.MENU);
	}

	/**
	 * 菜单分类.
	 * 
	 * <pre>
	 * 	菜单分类
	 * </pre>
	 * 
	 * @author liutao
	 * @since $Rev$
	 *
	 */
	public static class MenuType {
		
		// 菜单
		public static final String MENU = "M";
		
		// 权限
		public static final String PERMISSION = "P";
		
		// 按钮或链接
		public static final String BUTTON_LINK = "B";
		
		// 所有
		public static final String ALL = "ALL";
		
		public static Map<String, String> getMenuTypeMap() {
			
			Map<String, String> typeMap = new LinkedHashMap<>();
			typeMap.put(MENU, "菜单");
			typeMap.put(PERMISSION, "权限");
			typeMap.put(BUTTON_LINK, "按钮或链接");
			
			return typeMap;
		}
	}
}
