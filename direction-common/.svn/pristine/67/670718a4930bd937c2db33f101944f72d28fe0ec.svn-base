package com.direction.core.modules.sys.tenant.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.modules.sys.tenant.entity.base.BaseTenantPer;
import com.direction.spring.vo.FileVO;


// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_tenant_per")
public class TenantPer extends BaseTenantPer {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -3373731786786036084L;

	// ~ Fields
	// ==================================================================================================================
	
	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public String getIndustryDesc() {
		return StringUtils.isNotBlank(this.getIndustry()) ? (EIPService.getDictService().getTopValueMap("sys-industry-type").get(this.getIndustry())) : "";
	}

	public List<FileVO> getLogoFiles() {
		
		List<FileVO> files = new ArrayList<FileVO>();
		
		if (StringUtils.isNoneBlank(this.getLogo(), this.getLogoFileId())) {
			files.add(new FileVO(this.getLogoFileId(), this.getLogo()));
		}
		return files;
	}
}
