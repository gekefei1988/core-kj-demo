package com.direction.core.modules.sys.config.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.sys.config.IConfig;
import com.direction.core.modules.sys.config.entity.base.BaseConfig;

// ~ File Information
// ====================================================================================================================

/**
 * 系统配置.
 * 
 * <pre>
 * 	系统配置
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_config")
public class Config extends BaseConfig implements IConfig {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 906112830765522196L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
}
