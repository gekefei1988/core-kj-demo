package com.direction.core.modules.sys.dict.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.dict.entity.DictData;
import com.direction.core.modules.sys.dict.service.DictDataService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 数据字典_数据控制层.
 * 
 * <pre>
 * 数据字典_数据控制层
 * </pre>
 * 
 * @author qiwei
 * @since $Rev$
 *
 */

@RestController
@RequestMapping("/core/sys/dict-data")
public class DictDataController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private DictDataService dictDataService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param dictData
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<DictData> list(DictData dictData, VuePage<DictData> page) {

		return this.dictDataService.findPage(dictData, page);
	}

	/**
	 * 保存字典数据.
	 * 
	 * @param dictData
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResultJson save(DictData dictData) {
		
		this.dictDataService.save(dictData);
		
		return success();
	}

	/**
	 * 根据id获取数据,打开编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public DictData get(String id) {
		
		DictData dictData = this.dictDataService.get(id);
		
		if (dictData == null) {
			dictData = new DictData();
			dictData.setStatus(StatusConst.ENABLE);
			dictData.setDefaultValue(false);
			dictData.setDisplayOrder(0);
		}
		
		return dictData;
	}

	/**
	 * Delete dict data.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ResultJson delete(String id) {
		
		if (StringUtils.isBlank(id)) {
			return error("删除失败, 无法获取参数ID");
		}
		
		this.dictDataService.delete(id);

		return success();
	}
}
