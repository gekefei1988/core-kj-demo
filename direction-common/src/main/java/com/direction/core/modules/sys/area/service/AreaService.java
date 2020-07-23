package com.direction.core.modules.sys.area.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.direction.common.exception.ServiceException;
import com.direction.common.tree.Cascader;
import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.area.entity.Area;
import com.direction.core.modules.sys.area.repository.AreaRepository;
import com.direction.spring.mvc.page.VuePage;
import com.direction.spring.mvc.service.BaseService;

// ~ File Information
// ====================================================================================================================

@Service
@Transactional(readOnly = true)
public class AreaService extends BaseService<AreaRepository, Area, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 保存区域信息.
	 * 
	 * @param area
	 */
	@Transactional(readOnly = false)
	public Area save(Area area) {

		try {

			// 如果不是顶层的获取父层code
			if (StringUtils.equals(area.getParentCode(), "0")) {
				area.setParentCodes(area.getParentCode());
			} else {
				Area parentArea = this.getBaseRepository().getAreaByCode(area.getParentCode());
				if (parentArea == null) {
					throw new ServiceException("父级区域已被删除, 不能进行保存 ");
				}

				area.setParentCodes(parentArea.getParentCodes() + "," + parentArea.getAreaCode());
				area.setParentNames(parentArea.getParentNames() + "," + parentArea.getAreaName());
			}

			this.getBaseRepository().save(area);

			// 清空缓存
			this.clearAreaCache(area.getAreaCode());
			return area;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("行政区划, 保存失败, 错误信息: " + e.getMessage());
		}
	}

	/**
	 * 删除行政区划.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {

		try {

			Area area = this.get(id);
			if (area != null) {

				// 删除下级
				this.getBaseRepository().deleteAllSubs(area.getParentCodes() + "," + area.getAreaCode() + "%");

				// 删除当前数据
				this.getBaseRepository().delete(area);

				// 清空缓存
				this.clearAreaCache(area.getAreaCode());
			}
		} catch (Exception e) {
			throw new ServiceException("行政区划, 保存失败, 错误信息: " + e.getMessage());
		}
	}

	/**
	 * 获取区域级联信息.
	 * 
	 * @param areaCode
	 * @return
	 */
	public List<Cascader> getAreaCascader(String areaCode) {

		if (StringUtils.isBlank(areaCode)) {
			areaCode = "0";
		}

		// 缓存中获取行政区划
		@SuppressWarnings("unchecked")
		List<Cascader> cascaderList = (List<Cascader>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_AREA, "getAreaCascader_" + areaCode);

		if (cascaderList != null) {
			return cascaderList;
		} else {
			cascaderList = new ArrayList<Cascader>();
		}

		Cascader baseCascader = new Cascader();
		baseCascader.setCode(areaCode);

		if (!StringUtils.equals(areaCode, "0")) {

			Area parentArea = this.getAreaByCode(areaCode);

			// 如果传递的区域是空的, 则不获取区域信息
			if (parentArea == null) {
				return cascaderList;
			}

			baseCascader.setCode(parentArea.getAreaCode());
			baseCascader.setLabel(parentArea.getAreaName());
			baseCascader.setValue(parentArea.getAreaCode());
			baseCascader.setParentCode(parentArea.getParentCode());
		}

		List<Area> areas = this.getBaseRepository().findAll(new Sort(Sort.Direction.ASC, "displayOrder"));

		if (areas != null && areas.size() > 0) {

			Map<String, Cascader> areaCascaders = new LinkedHashMap<String, Cascader>();

			for (Area area : areas) {
				areaCascaders.put(area.getAreaCode(),
				    new Cascader(area.getAreaName(), area.getAreaCode(), area.getAreaCode(), area.getParentCode()));
			}

			// 设置关联
			for (Cascader node : areaCascaders.values()) {

				if (StringUtils.equals(node.getParentCode(), areaCode)) {
					baseCascader.getChildren().add(node);
				} else {
					if (areaCascaders.get(node.getParentCode()) != null) {
						areaCascaders.get(node.getParentCode()).getChildren().add(node);
					}
				}
			}
		}

		// 如果是顶层, 直接获取下级
		if (StringUtils.equals(baseCascader.getCode(), "0")) {
			cascaderList.addAll(baseCascader.getChildren());
		} else {
			cascaderList.add(baseCascader);
		}
		// 放入缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_AREA, "getAreaCascader_" + areaCode, cascaderList);

		return cascaderList;
	}

	/**
	 * 清空缓存内数据.
	 * 
	 * @param areaCode
	 */
	private void clearAreaCache(String areaCode) {

		CacheSysManageUtils.remove(CacheSysModulesCategory.SYS_AREA, "getAreaCascader_" + areaCode);
	}

	/**
	 * 根据Code获取区域信息.
	 * 
	 * @param areaCode
	 * @return
	 */
	public Area getAreaByCode(String areaCode) {

		return this.getBaseRepository().getAreaByCode(areaCode);
	}

	/**
	 * 上传区域.
	 * 
	 * @param file
	 */
	@Deprecated
	public void uploadAreaFile(MultipartFile file) {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line = "";
			String[] cols = null;
			Map<String, String> types = new HashMap<String, String>();
			types.put("province", "1");
			types.put("city", "2");
			types.put("country", "3");
			types.put("town", "4");
			types.put("village", "5");
			Area area = null;
			int count = 0;
			// 1 code, 3: name, 5: 区划类型, province city country town village7: 父级CODE, 8: 排序
			while ((line = br.readLine()) != null) {

				line = StringUtils.replaceAll(line, "\"", "");

				cols = StringUtils.split(line, ",");
				if (StringUtils.equals(cols[5], "town")) {
					count++;

					System.out.println(count);
					area = new Area();
					area.setAreaCode(cols[1]);
					area.setAreaName(cols[3]);
					area.setAreaType(types.get(cols[5]));
					area.setParentCode(cols[7]);
					area.setDisplayOrder(Integer.valueOf(cols[8]));
					area.setStatus(StatusConst.ENABLE);
					this.save(area);
				}
			}
			System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 行政区划分页.
	 * 
	 * @param area
	 * @param page
	 * @return
	 */
	public VuePage<Area> findPage(Area area, VuePage<Area> page) {

		// 封装查询条件
		Specification<Area> dictParams = new Specification<Area>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<>();

				if (area != null) {

					// 判断关键字
					if (StringUtils.isNotBlank(area.getAreaName())) {
						predicateList.add(criteriaBuilder.or(
						    criteriaBuilder.like(root.get("areaName").as(String.class), "%" + area.getAreaName() + "%"),
						    criteriaBuilder.like(root.get("areaCode").as(String.class), "%" + area.getAreaName() + "%")));
					}

					// 判断状态
					if (StringUtils.isNotBlank(area.getStatus())) {
						predicateList.add(criteriaBuilder.equal(root.get("status").as(String.class), area.getStatus()));
					}

					// 父级CODE
					if (StringUtils.isNotBlank(area.getParentCode())) {
						predicateList.add(criteriaBuilder.equal(root.get("parentCode").as(String.class), area.getParentCode()));
					}
				}

				// 区域为空或父级为空
				if (area == null || StringUtils.isBlank(area.getParentCode())) {
					predicateList.add(criteriaBuilder.equal(root.get("parentCode").as(String.class), "0"));
				}

				Predicate[] restrictions = new Predicate[predicateList.size()];
				return criteriaBuilder.and(predicateList.toArray(restrictions));
			}
		};

		Page<Area> areaPage = getBaseRepository().findAll(dictParams, page.getPageable());
		
		page.setPage(areaPage);
		
		return page;
	}
}
