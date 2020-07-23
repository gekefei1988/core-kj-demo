package com.direction.common.utils.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PermsDataUtils {

	/**
	 * 过滤权限集合.
	 * 
	 * @param perms
	 * @return
	 */
	public static Set<String> getResults(List<String> perms) {

		Set<String> permsSet = new HashSet<String>();
		for (String perm : perms) {
			if (perm != null && StringUtils.isNotBlank(perm)) {
				permsSet.add(perm);
			}
		}
		return permsSet;
	}
}
