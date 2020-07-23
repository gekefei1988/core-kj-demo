package com.direction.core.modules.sys.dict.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direction.common.utils.cache.CacheSysManageUtils;
import com.direction.common.utils.cache.CacheSysManageUtils.CacheSysModulesCategory;
import com.direction.core.inf.sys.dict.DictTable;
import com.direction.core.inf.sys.dict.DictTableItem;
import com.direction.core.inf.sys.dict.IDictService;
import com.direction.core.modules.sys.dict.entity.DictData;
import com.direction.core.modules.sys.dict.entity.DictType;

// ~ File Information
// ====================================================================================================================

@Service
public class DictServiceImpl implements IDictService {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	@Autowired
	private DictDataService dictDataService;

	@Autowired
	private DictTypeService dictTypeService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取顶级节点默认值.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getTopDefaultValue(java.lang.String)
	 */
	@Override
	public String getTopDefaultValue(String type) {
		
		String defaultValue = "";

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return defaultValue;
		}

		DictTable dictTable = this.getDictTable(type);

		if (dictTable.getChildren().size() > 0) {
			for (DictTableItem item : dictTable.getChildren()) {
				if (item.isDefaultValue()) {
					defaultValue = item.getValue();
					break;
				}
			}
		}

		return defaultValue;
	}

	/**
	 * 获取默认值.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getDefaultValue(java.lang.String, int, java.lang.String)
	 */
	@Override
	public String getDefaultValue(String type, int level, String value) {
		
		String defaultValue = "";

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return defaultValue;
		}

		// 如果value为空返回空map
		if (level == 0 && StringUtils.isBlank(value)) {
			return this.getTopDefaultValue(type);
		}

		DictTable dictTable = this.getDictTable(type);

		if (dictTable != null) {
			DictTableItem item = this.loadTableItem(dictTable.getChildren(), level, value);

			if (item != null) {
				for (DictTableItem child : item.getChildren()) {
					if (child.isDefaultValue()) {
						defaultValue = child.getValue();
						break;
					}
				}
			}
		}

		return defaultValue;
	}

	/**
	 * 根据字典类型获取字典数据.
	 * 
	 * @see com.direction.common.core.IDictService.dict.IDictService#getTopValueMap(java.lang.String)
	 */
	@Override
	public Map<String, String> getTopValueMap(String type) {

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return new LinkedHashMap<String, String>();
		}

		// 缓存中获取数据
		Map<String, String> maps = new LinkedHashMap<String, String>();

		DictTable dictTable = this.getDictTable(type);

		if (dictTable.getChildren().size() > 0) {
			for (DictTableItem item : dictTable.getChildren()) {
				maps.put(item.getValue(), item.getLabel());
			}
		}

		return maps;
	}
	
	/**
	 * 根据字典类型获取字典数据.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getTopItemMap(java.lang.String)
	 */
	@Override
	public Map<String, DictTableItem> getTopItemMap(String type) {


		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return new LinkedHashMap<String, DictTableItem>();
		}

		// 缓存中获取数据
		Map<String, DictTableItem> maps = new LinkedHashMap<String, DictTableItem>();

		DictTable dictTable = this.getDictTable(type);

		if (dictTable.getChildren().size() > 0) {
			for (DictTableItem item : dictTable.getChildren()) {
				maps.put(item.getValue(), item);
			}
		}

		return maps;
	
	}

	/**
	 * 根据字典类型获取字典数据.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getValueMap(java.lang.String, int, java.lang.String)
	 */
	@Override
	public Map<String, String> getValueMap(String type, int level, String value) {

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return new LinkedHashMap<String, String>();
		}

		// 如果value为空返回空map
		if (level == 0 && StringUtils.isBlank(value)) {
			return this.getTopValueMap(type);
		}
		
		// 获取缓存字典表明细
		@SuppressWarnings("unchecked")
		Map<String, String> maps = (Map<String, String>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, 
				String.format("getValueMap_%s_%s_%s", type, level, value));
		
		if (maps != null) {
			return maps;
		}

		maps = new LinkedHashMap<String, String>();

		DictTable dictTable = this.getDictTable(type);

		if (dictTable != null) {
			DictTableItem item = this.loadTableItem(dictTable.getChildren(), level, value);

			if (item != null) {
				for (DictTableItem child : item.getChildren()) {
					maps.put(child.getValue(), child.getLabel());
				}
			}
		}

		// 将数据放到缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, String.format("getValueMap_%s_%s_%s", type, level, value), maps);

		return maps;
	}
	

	/**
	 * 根据字典类型获取字典数据.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getItemMap(java.lang.String, int, java.lang.String)
	 */
	@Override
	public Map<String, DictTableItem> getItemMap(String type, int level, String value) {

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return new LinkedHashMap<String, DictTableItem>();
		}

		// 如果value为空返回空map
		if (level == 0 && StringUtils.isBlank(value)) {
			return this.getTopItemMap(type);
		}
		
		// 获取缓存字典表明细
		@SuppressWarnings("unchecked")
		Map<String, DictTableItem> maps = (Map<String, DictTableItem>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, 
				String.format("getItemMap%s_%s_%s", type, level, value));

		if (maps != null) {
			return maps;
		}

		maps = new LinkedHashMap<String, DictTableItem>();

		DictTable dictTable = this.getDictTable(type);

		if (dictTable != null) {
			DictTableItem item = this.loadTableItem(dictTable.getChildren(), level, value);

			if (item != null) {
				for (DictTableItem child : item.getChildren()) {
					maps.put(child.getValue(), child);
				}
			}
		}

		// 将数据放到缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, String.format("getItemMap%s_%s_%s", type, level, value), maps);

		return maps;
	}

	/**
	 * 获取字典元素值.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getDictTableItems(java.lang.String, int,
	 *      java.lang.String)
	 */
	@Override
	public List<DictTableItem> getDictTableItems(String type, int level, String value) {

		List<DictTableItem> results = new ArrayList<DictTableItem>();

		// 如果类型为空返回空map
		if (StringUtils.isBlank(type)) {
			return results;
		}

		// 获取字典表
		DictTable table = this.getDictTable(type);

		if (table == null) {
			return results;
		}

		// 如果是0, 则是顶级
		if (level == 0 && StringUtils.isBlank(value)) {
			return table.getChildren();
		}

		// 如果value为空返回空map
		DictTableItem item = this.loadTableItem(table.getChildren(), level, value);

		if (item != null) {
			results = item.getChildren();
		}

		return results;
	}

	/**
	 * 通过级别和值匹配字典数据.
	 * 
	 * @param children
	 * @param level
	 * @param value
	 * @return
	 */
	private DictTableItem loadTableItem(List<DictTableItem> children, int level, String value) {

		DictTableItem result = null;

		if (children != null && children.size() > 0) {
			for (DictTableItem item : children) {

				// 级别和值相等则返回结果
				if (item.getLevel() == level) {
					if (StringUtils.equals(value, item.getValue())) {
						result = item;
						break;
					}
				} else if (item.getLevel() < level) {
					result = this.loadTableItem(item.getChildren(), level, value);
					if (result != null) {
						break;
					}
				}
			}
		}

		return result;
	}

	/**
	 * 根据ID获取字典表数据.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getDictTableItem(java.lang.String)
	 */
	@Override
	public DictTableItem getDictTableItem(String id) {

		if (StringUtils.isBlank(id)) {
			return null;
		}

		// 获取缓存字典表明细
		DictTableItem tableItem = (DictTableItem) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, "getDictTableItem_" + id);

		if (tableItem != null) {
			return tableItem;
		}

		DictData data = dictDataService.get(id);

		if (data == null) {
			return null;
		}

		tableItem = new DictTableItem(data);

		// 包含下级
		List<DictData> datas = this.dictDataService.findAllDatasByParent(tableItem.getId());

		if (datas != null && datas.size() > 0) {

			Map<String, DictTableItem> maps = new LinkedHashMap<String, DictTableItem>();

			for (DictData dictData : datas) {
				maps.put(dictData.getId(), new DictTableItem(dictData));
			}

			for (DictTableItem item : maps.values()) {

				if (StringUtils.equals(tableItem.getId(), item.getParentId())) {
					tableItem.getChildren().add(item);
				} else if (maps.containsKey(item.getParentId())) {
					maps.get(item.getParentId()).getChildren().add(item);
				}
			}
		}

		// 加入缓存字典表明细
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, "getDictTableItem_" + id, tableItem);

		return tableItem;
	}

	/**
	 * 获取该租户下的字典表.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getDictTable(java.lang.String)
	 */
	@Override
	public DictTable getDictTable(String dictType) {

		if (StringUtils.isBlank(dictType)) {
			return null;
		}

		// 缓存中获取字典表
		DictTable dictTable = (DictTable) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, "getDictTable_" + dictType);

		if (dictTable != null) {
			return dictTable;
		}

		// 获取字典类型
		DictType type = this.dictTypeService.getByType(dictType);

		if (type == null) {
			return null;
		}

		dictTable = new DictTable(type.getDictType(), type.getDictName());

		List<DictData> datas = this.dictDataService.findAllByType(dictType);

		if (datas != null && datas.size() > 0) {

			Map<String, DictTableItem> maps = new LinkedHashMap<String, DictTableItem>();

			for (DictData dictData : datas) {
				maps.put(dictData.getId(), new DictTableItem(dictData));
			}

			for (DictTableItem item : maps.values()) {

				if (StringUtils.equals("0", item.getParentId())) {
					dictTable.getChildren().add(item);
				} else if (maps.containsKey(item.getParentId())) {
					maps.get(item.getParentId()).getChildren().add(item);
				}
			}
		}

		// 将字典表放到缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, "getDictTable_" + dictType, dictTable);

		return dictTable;
	}

	/**
	 * 根据租户获取字典值.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getAllValueMap(java.lang.String)
	 */
	@Override
	public Map<String, String> getAllValueMap(String type) {
		
		if (StringUtils.isBlank(type)) {
			return new HashMap<String, String>();
		}

		// 缓存中获取字典表
		@SuppressWarnings("unchecked")
		Map<String, String> result  = (Map<String, String>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, "getAllValueMap_" + type);

		if (result != null) {
			return result;
		}
		else {
			result = new HashMap<String, String>();
		}

		List<DictData> datas = this.dictDataService.findAllByType(type);

		if (datas != null && datas.size() > 0) {

			for (DictData dictData : datas) {
				result.put(dictData.getDictValue(), dictData.getDictLabel());
			}
		}

		// 将字典表放到缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, "getAllValueMap_" + type, result);
		
		return result;
	}
	
	/**
	 * 获取该类型下所有数据.
	 * 
	 * @see com.direction.core.inf.sys.dict.IDictService#getAllItemsMap(java.lang.String)
	 */
	@Override
	public Map<String, DictTableItem> getAllItemsMap(String type) {
		
		if (StringUtils.isBlank(type)) {
			return new HashMap<String, DictTableItem>();
		}

		// 缓存中获取字典表
		@SuppressWarnings("unchecked")
		Map<String, DictTableItem> result  = (Map<String, DictTableItem>) CacheSysManageUtils.get(CacheSysModulesCategory.SYS_DICT, "getAllItemsMap_" + type);

		if (result != null) {
			return result;
		}
		else {
			result = new HashMap<String, DictTableItem>();
		}

		List<DictData> datas = this.dictDataService.findAllByType(type);

		if (datas != null && datas.size() > 0) {

			for (DictData dictData : datas) {
				result.put(dictData.getDictValue(), new DictTableItem(dictData));
			}
		}

		// 将字典表放到缓存中
		CacheSysManageUtils.put(CacheSysModulesCategory.SYS_DICT, "getAllItemsMap_" + type, result);
		
		return result;
	}
}
