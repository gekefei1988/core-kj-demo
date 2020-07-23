package com.direction.core.modules.sys.tenant.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.modules.sys.tenant.entity.base.BaseTenantCompCert;
import com.direction.spring.vo.FileVO;

// ~ File Information
// ====================================================================================================================

/**
 * 企业认证信息.
 * 
 * <pre>
 * 企业认证信息
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_tenant_comp_cert")
public class TenantCompCert extends BaseTenantCompCert {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -9063274224781170289L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getIndustryDesc() {
		return StringUtils.isNotBlank(this.getIndustry()) ? (EIPService.getDictService().getTopValueMap("sys-industry-type").get(this.getIndustry())) : "";
	}
	
	public String getCompTypeDesc() {
		return StringUtils.isNotBlank(this.getCompType()) ? (EIPService.getDictService().getTopValueMap("sys_company_category").get(this.getCompType())) : "";
	}
	
	public List<FileVO> getLogoFiles() {
		
		List<FileVO> files = new ArrayList<FileVO>();
		
		if (StringUtils.isNoneBlank(this.getLogo(), this.getLogoFileId())) {
			files.add(new FileVO(this.getLogoFileId(), this.getLogo()));
		}
		return files;
	}
	
	public List<FileVO> getBusinessLicenseFiles() {
		
		List<FileVO> files = new ArrayList<FileVO>();
		
		if (StringUtils.isNoneBlank(this.getBusinessLicense(), this.getBusinessLicenseFileId())) {
			files.add(new FileVO(this.getBusinessLicenseFileId(), this.getBusinessLicense()));
		}
		return files;
	} 
}
