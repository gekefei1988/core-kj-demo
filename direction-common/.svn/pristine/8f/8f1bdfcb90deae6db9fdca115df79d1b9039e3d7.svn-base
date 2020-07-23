package com.direction.core.inf.api.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.tree.Cascader;
import com.direction.common.tree.TreeNode;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.area.IAreaService;
import com.direction.core.inf.sys.dict.DictTableItem;
import com.direction.core.inf.sys.dict.IDictService;
import com.direction.core.inf.sys.menu.IMenuService;
import com.direction.core.inf.sys.post.IPostService;
import com.direction.core.inf.sys.user.UserProfile;
import com.direction.core.modules.sys.menu.entity.MenuVO;
import com.direction.core.modules.sys.post.entity.Post;
import com.direction.spring.mvc.controller.BaseController;

// ~ File Information
// ====================================================================================================================

@RestController
@RequestMapping("/core/open-api/sys")
public class ApiController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private IDictService iDictService;

	@Autowired
	private IMenuService iMenuService;

	@Autowired
	private IPostService iPostService;

	@Autowired
	private IAreaService iAreaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取字典集合.
	 * 
	 * @param dictType
	 * @param level
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/dict/dict-maps", method = RequestMethod.POST)
	public Map<String, String> getDictMaps(String dictType, Integer level, String value) {
		
		return this.iDictService.getValueMap(dictType, (level == null ? 0 : level), value);
	}

	/**
	 * 获取字典集合.
	 * 
	 * @param dictType
	 * @param level
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/dict/dict-list", method = RequestMethod.POST)
	public List<DictTableItem> getDictList(String dictType, Integer level, String value) {
		
		return this.iDictService.getDictTableItems(dictType, (level == null ? 0 : level), value);
	}

	/**
	 * 获取菜单树结构.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/getMenuVOTrees")
	public List<MenuVO> getMenuVOTrees() {

		return this.iMenuService.getMenuVOTrees();
	}

	/**
	 * 获取菜单树结构.
	 * 
	 * @param menuType
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/getCheckedMenuTrees")
	public List<TreeNode> getCheckedMenuTrees(String menuType, String roleId) {

		return this.iMenuService.getCheckedMenuTrees(menuType, roleId);
	}

	/**
	 * 查询所有启用岗位.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/post/getPostList")
	public List<Post> getPostList() {

		return this.iPostService.getPostList(UserProfileUtils.getTenantId(), "0");
	}

	/**
	 * 获取区域的级联数据.
	 * 
	 * @return
	 */
	@RequestMapping("/area/getAreaCascader")
	public List<Cascader> getAreaCascader(String areaCode) {

		return this.iAreaService.getAreaCascader(areaCode);
	}

	/**
	 * 验证权限.
	 * 
	 * @param accessCodes
	 * @return
	 */
	@RequestMapping("/shrio/access")
	public Map<String, Boolean> access(String accessCodes) {

		Map<String, Boolean> result = new HashMap<String, Boolean>();

		if (StringUtils.isNotBlank(accessCodes)) {

			String[] access = StringUtils.split(accessCodes, ",");
			Subject subject = SecurityUtils.getSubject();
			UserProfile user = UserProfileUtils.getUserProfile();

			for (String key : access) {
				if (StringUtils.isNotBlank(key)) {
					result.put(key, subject.isPermitted(key) || user.isSuperAdmin());
				}
			}
		}

		return result;
	}
}
