package com.direction.core.modules.sys.tenant.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.modules.sys.tenant.entity.base.BaseTenantCertHonor;
import com.direction.spring.vo.FileVO;

// ~ File Information
// ====================================================================================================================

/**
 * 租户荣誉基础类.
 * 
 * <pre>
 * 租户荣誉基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_tenant_cert_honor")
public class TenantCertHonor extends BaseTenantCertHonor {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7307531194627910130L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public List<FileVO> getFiles() {

		List<FileVO> files = new ArrayList<FileVO>();

		if (StringUtils.isNoneBlank(this.getImageUrls(), this.getImageFileIds())) {
			String[] urls = StringUtils.split(this.getImageUrls(), ",");
			String[] ids = StringUtils.split(this.getImageFileIds(), ",");
			for (int i = 0; i < urls.length; i++) {
				files.add(new FileVO(ids[i], urls[i]));
			}
		}
		return files;
	}
}
