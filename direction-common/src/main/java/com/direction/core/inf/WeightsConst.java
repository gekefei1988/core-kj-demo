package com.direction.core.inf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

// ~ File Information
// ====================================================================================================================

/**
 * 权重.
 * 
 * <pre>
 * 权重
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class WeightsConst {

	// ~ Static Fields
	// ==================================================================================================================

	public static final int WEIGHTS_USER = 1;
	public static final int WEIGHTS_MANAGER = 2;
	public static final int WEIGHTS_SUPER_MANAGER = 4;

	// ~ Fields
	// ==================================================================================================================

	private static Map<Integer, String> weightsMap;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 获取权重Map.
	 * 
	 * @return
	 */
	public static Map<Integer, String> getWeightsMap() {

		if (weightsMap != null) {
			return weightsMap;
		} else {
			weightsMap = new LinkedHashMap<>();
		}

		weightsMap.put(WEIGHTS_USER, "普通用户");
		weightsMap.put(WEIGHTS_MANAGER, "二级管理员");
		weightsMap.put(WEIGHTS_SUPER_MANAGER, "超级管理员");

		return weightsMap;
	}
	
	/**
	 * 获取权重集合.
	 * 
	 * @return
	 */
	public static List<Integer> getWeights(Integer weights) {
		
		List<Integer> results = new ArrayList<>();
		
		if (weights != null && weights > 0) {
			for (Integer weight : getWeightsMap().keySet()) {
				if ((weights & weight) == weight) {
					results.add(weight);
				}
			}
		}
		
		return results;
	}
	
	/**
	 * 获取选中名称.
	 * 
	 * @param weights
	 * @return
	 */
	public static String getWeightsDesc(Integer weights) {
		String results = "";
		
		if (weights != null && weights > 0) {
			for (Integer weight : getWeightsMap().keySet()) {
				if ((weights & weight) == weight) {
					if (StringUtils.isNotBlank(results)) {
						results += ",";
					}
					results += getWeightsMap().get(weight);
				}
			}
		}
		
		return results;
	}
}
