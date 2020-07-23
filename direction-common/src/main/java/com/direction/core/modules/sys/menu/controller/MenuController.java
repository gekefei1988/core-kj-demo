package com.direction.core.modules.sys.menu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.tree.TreeNode;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.WeightsConst;
import com.direction.core.modules.sys.menu.entity.Menu;
import com.direction.core.modules.sys.menu.entity.Menu.MenuType;
import com.direction.core.modules.sys.menu.service.MenuService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 系统菜单控制层.
 * 
 * <pre>
 * 系统菜单控制层
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/menu")
public class MenuController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private MenuService menuService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询菜单.
	 * 
	 * @param menu
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Menu> list(Menu menu, VuePage<Menu> page) {

		return this.menuService.findPage(menu, page);
	}

	/**
	 * 保存新增菜单.
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(Menu menu) {

		this.menuService.save(menu);
		return success();
	}
	
	/**
	 * 获取选中信息.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getWeightsMap")
	public Map<Integer, String> getWeightsMap() {
		return WeightsConst.getWeightsMap();
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String menuId) {
		return this.menuService.validateOnlyOne(null, propName, propValue, "menuId", menuId);
	}

	/**
	 * 删除菜单.
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {

		this.menuService.delete(id);
		return success();
	}
	
	/**
	 * 获取菜单信息.
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Menu get(String menuId) {
		
		Menu menu = this.menuService.get(menuId);
		
		if (menu == null) {
			menu = new Menu();
			menu.setMenuType(MenuType.MENU);
			menu.setParentId("0");
			
			// 默认使用缓存
			menu.setUseCache("T");
			
			// 显示目录
			menu.setShowFolder("T");
			
			// 是否显示
			menu.setVisible("T");
			
			menu.setIcon("md-square");
			
			// 菜单权重
			menu.setWeights(WeightsConst.WEIGHTS_USER);
			
			menu.setDisplayOrder(0);
			menu.setStatus(StatusConst.ENABLE);
		}
		
		return menu;
	}
	
	/**
	 * 获取菜单树结构.
	 * 
	 * @param menuType
	 * @return
	 */
	@RequestMapping(value = "/getMenuTrees")
	public List<TreeNode> getMenuTrees(String menuType) {
		return this.menuService.getMenuTrees(menuType);
	}
}
