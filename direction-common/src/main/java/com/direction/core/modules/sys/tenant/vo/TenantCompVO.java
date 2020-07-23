package com.direction.core.modules.sys.tenant.vo;

import com.direction.core.modules.sys.tenant.entity.TenantCertHonor;
import com.direction.core.modules.sys.tenant.entity.TenantCertWeb;
import com.direction.core.modules.sys.tenant.entity.TenantComp;
import com.direction.core.modules.sys.tenant.entity.TenantCompCert;

// ~ File Information
// ====================================================================================================================

public class TenantCompVO {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	private TenantComp tenantComp;

	private TenantCompCert tenantCompCert;

	private TenantCertHonor tenantCertHonor;

	private TenantCertWeb tenantCertWeb;

	private boolean submit;

	// ~ Constructors
	// ==================================================================================================================

	public TenantCompVO() {

		tenantComp = new TenantComp();
		tenantCompCert = new TenantCompCert();
		tenantCertHonor = new TenantCertHonor();
		tenantCertWeb = new TenantCertWeb();
	}

	// ~ Methods
	// ==================================================================================================================

	public TenantComp getTenantComp() {

		return tenantComp;
	}

	public boolean isSubmit() {

		return submit;
	}

	public void setSubmit(boolean submit) {

		this.submit = submit;
	}

	public void setTenantComp(TenantComp tenantComp) {

		this.tenantComp = tenantComp;
	}

	public TenantCertHonor getTenantCertHonor() {

		return tenantCertHonor;
	}

	public void setTenantCertHonor(TenantCertHonor tenantCertHonor) {

		this.tenantCertHonor = tenantCertHonor;
	}

	public TenantCertWeb getTenantCertWeb() {

		return tenantCertWeb;
	}

	public void setTenantCertWeb(TenantCertWeb tenantCertWeb) {

		this.tenantCertWeb = tenantCertWeb;
	}

	public TenantCompCert getTenantCompCert() {

		return tenantCompCert;
	}

	public void setTenantCompCert(TenantCompCert tenantCompCert) {

		this.tenantCompCert = tenantCompCert;
	}
}
