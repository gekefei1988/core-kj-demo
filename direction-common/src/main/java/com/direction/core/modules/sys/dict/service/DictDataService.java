package com.direction.core.modules.sys.dict.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.direction.common.exception.ServiceException;
import com.direction.core.modules.sys.dict.entity.DictData;
import com.direction.core.modules.sys.dict.entity.DictType;
import com.direction.core.modules.sys.dict.repository.DictDataRepository;
import com.direction.core.modules.sys.dict.repository.DictTypeRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

/**
 * 字典数据业务逻辑层.
 * 
 * <pre>
 * 	字典数据业务逻辑层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Service
@Transactional(readOnly = true)
public class DictDataService extends BaseService<DictDataRepository, DictData, String>{

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private DictTypeRepository dictTypeRepository;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 字典类型保存.
	 * 
	 * @param dictType
	 * @return
	 */
	@Transactional(readOnly = false)
	public DictData save(DictData dictData) {

		try {
			
			// 如果父级为空则赋值为0
			if (StringUtils.isBlank(dictData.getParentId())) {
				dictData.setParentId("0");
			}
			
			// 判断顶级
			if (StringUtils.equals(dictData.getParentId(), "0")) {
				dictData.setLevel(0);
				dictData.setParentIds("0");
			} 
			else {
				
				// 获取上级
				DictData parentDict = this.get(dictData.getParentId());
				
				if (parentDict != null) {
					dictData.setLevel(parentDict.getLevel() + 1);
					dictData.setParentIds(parentDict.getParentIds() + "," + parentDict.getId());
				}
				else {
					throw new ServiceException("上级字典数据已不存在!");
				}
			}
			
			// 判断是否新增, 新增时加入是否系统内置
			if (StringUtils.isBlank(dictData.getId())) {
				DictType dictType = this.dictTypeRepository.getByType(dictData.getDictType());
				if (dictType == null) {
					throw new ServiceException("未获取到字典类型!");
				}
				
				dictData.setDictTypeId(dictType.getId());
				dictData.setSys(dictType.getSys());
			}
			
			// 如果是默认值, 则修改所有的值为非默认
			if (dictData.isDefaultValue()) {
				this.getBaseRepository().updateDefaultValue(dictData.getDictTypeId(), dictData.getParentId(), false);
			}
			
			this.getBaseRepository().save(dictData);
			
			return dictData;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 删除数据字典数据(包含批量删除）
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		
		if (StringUtils.isNotBlank(id)) {
			DictData data = this.get(id);
			
			if (data != null) {
				
				// 删除所有下级
				this.getBaseRepository().deleteAllDatasByParents(data.getParentIds() + "," + data.getId());
				
				this.getBaseRepository().delete(data);
			}
		}
	}

	/**
	 * 根据字典类型获取数据.
	 * 
	 * @param dictType
	 * @return
	 */
	public List<DictData> findAllByType(String dictType) {
		return this.getBaseRepository().findAllByType(dictType);
	}
	
	/**
	 * 根据父级ID查询直接下级字典数据.
	 * 
	 * @param parentId
	 * @return
	 */
	public List<DictData> findAllDatasByParent(String parentId) {
		
		DictData data = this.get(parentId);
		if (data != null) {
			return this.getBaseRepository().findAllDatasByParents(data.getParentIds() + "," + data.getId());
		}
		return new ArrayList<DictData>();
	}
	
	/**
	 * 字典类型分页.
	 * 
	 * @param dictData
	 * @param page
	 * @return
	 */
	public VuePage<DictData> findPage(DictData dictData, VuePage<DictData> page) {
		
		if (StringUtils.isBlank(dictData.getDictType())) {
			return page;
		}

		// 封装查询条件
		Specification<DictData> dictParams = new Specification<DictData>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<DictData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();
				
				if (dictData != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(dictData.getDictLabel())) {
						predicateList.add(criteriaBuilder.like(root.get("dictLabel").as(String.class), "%" + dictData.getDictLabel() + "%"));
					}

					// 判断状态
					if (StringUtils.isNotBlank(dictData.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), dictData.getStatus()));
					}
					
					// 父级
					if (StringUtils.isNotBlank(dictData.getParentId())) {
						predicateList.add(criteriaBuilder.equal(root.get("parentId").as(String.class), dictData.getParentId()));
					}
				}
				
				// 如果字典数据是空的或者parentId是空的话 则加入条件
				if (dictData == null || StringUtils.isBlank(dictData.getParentId())) {
					predicateList.add(criteriaBuilder.equal(root.get("parentId").as(String.class), "0"));
				}
				
				predicateList.add(criteriaBuilder.equal(root.get("dictType").as(String.class), dictData.getDictType()));

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<DictData> dictTypePage = getBaseRepository().findAll(dictParams, page.getPageable());
		page.setPage(dictTypePage);

		return page;
	}
}
