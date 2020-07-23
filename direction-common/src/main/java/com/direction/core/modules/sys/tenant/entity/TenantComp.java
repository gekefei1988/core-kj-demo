package com.direction.core.modules.sys.tenant.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.modules.sys.tenant.entity.base.BaseTenantComp;

// ~ File Information
// ====================================================================================================================

/**
 * 企业认证基础类.
 * 
 * <pre>
 * 企业认证基础类
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_tenant_comp")
public class TenantComp extends BaseTenantComp {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -3020432060327780809L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
}
