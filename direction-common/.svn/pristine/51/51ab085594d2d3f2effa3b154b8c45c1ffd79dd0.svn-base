package com.direction.core.modules.sys.area.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.service.AreaService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 系统区域控制层.
 * 
 * <pre>
 * 系统区域控制层
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/area")
public class AreaController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private AreaService areaService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询区域
	 * 
	 * @param area
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Area> list(Area area, VuePage<Area> page) {
		return this.areaService.findPage(area, page);
	}

	/**
	 * 获取数据.
	 */
	@RequestMapping(value = "/get")
	public Area get(String id) {
		
		Area area = this.areaService.get(id);
		
		if (area == null) {
			area = new Area();
			area.setStatus(StatusConst.ENABLE);
			area.setParentCode("0");
			area.setDisplayOrder(0);
		}
		
		return area;
	}

	/**
	 * 保存区域.
	 * 
	 * @param Area
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(Area area) {
		this.areaService.save(area);
		return success();
	}

	/**
	 * 删除区域
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		this.areaService.delete(id);
		return success();
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String id) {
		return this.areaService.validateOnlyOne(propName, propValue, id);
	}
}
