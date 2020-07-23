package com.direction.core.modules.sys.dict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.common.utils.validation.HibernateValidationUtils;
import com.direction.core.inf.StatusConst;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.modules.sys.dict.entity.DictType;
import com.direction.core.modules.sys.dict.service.DictTypeService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 数据字典控制层.
 * 
 * <pre>
 * 数据字典控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/dict-type")
public class DictTypeController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private DictTypeService dictTypeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param dictType
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<DictType> list(DictType dictType, VuePage<DictType> page) {
		
		return this.dictTypeService.findPage(dictType, page);
	}

	/**
	 * 
	 * @param dictType
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultJson save(DictType dictType) {
		
		// 验证实体
		ResultJson result = HibernateValidationUtils.validateEntity(dictType);
		
		if (result.isSuccess()) {
			this.dictTypeService.save(dictType);
		}
		
		return result;
	}

	/**
	 * 根据id获取数据,打开编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public DictType get(String id) {
		
		DictType dictType = this.dictTypeService.get(id);
		
		if (dictType == null) {
			dictType = new DictType();
			dictType.setStatus(StatusConst.ENABLE);
			dictType.setSys(false);
		}
		
		return dictType; 
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
		return this.dictTypeService.validateOnlyOne(propName, propValue, id);
	}
	
	/**
	 * 删除.
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultJson delete(String id) {
		
		// 是否超级管理员
		if (UserProfileUtils.isSuperAdmin()) {
			this.dictTypeService.delete(id);
		}
		else {
			this.dictTypeService.delete(UserProfileUtils.getTenantId(), id);
		}
		return success();
	}
}
