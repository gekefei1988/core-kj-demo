package com.direction.core.modules.sys.dict.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.modules.sys.dict.entity.DictType;
import com.direction.core.modules.sys.dict.repository.DictDataRepository;
import com.direction.core.modules.sys.dict.repository.DictTypeRepository;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 字典类型业务逻辑层.
 * 
 * <pre>
 * 	字典类型业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class DictTypeService extends BaseService<DictTypeRepository, DictType, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private DictDataRepository dictDataRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据字典类型查询数据.
	 * 
	 * @param dictType
	 * @return
	 */
	public DictType getByType(String dictType) {

		if (StringUtils.isBlank(dictType)) {
			return null;
		}

		return this.getBaseRepository().getByType(dictType);
	}

	/**
	 * 字典类型保存.
	 * 
	 * @param dictType
	 * @return
	 */
	@Transactional(readOnly = false)
	public DictType save(DictType dictType) {

		try {

			this.getBaseRepository().save(dictType);

			return dictType;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 删除数据字典类型.
	 * 
	 * @param tenantId
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String tenantId, String id) {
		
		if (StringUtils.isAnyBlank(tenantId, id)) {
			throw new ServiceException("删除失败, 未获取到租户或主键为空");
		}

		// 删除明细数据
		DictType dictType = this.getBaseRepository().get(id);

		if (dictType != null) {
			
			// 删除字典数据
			this.dictDataRepository.deleteByDictType(dictType.getDictType());
			
			// 删除字典类型
			this.getBaseRepository().delete(dictType);
		}
	}
	
	/**
	 * 删除数据字典类型.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		
		if (StringUtils.isBlank(id)) {
			throw new ServiceException("删除失败, 主键为空");
		}

		// 删除明细数据
		DictType dictType = this.getBaseRepository().get(id);

		if (dictType != null) {
			
			// 删除字典数据
			this.dictDataRepository.deleteByDictType(dictType.getDictType());
			
			// 删除字典类型
			this.getBaseRepository().delete(dictType);
		}
	}
}
