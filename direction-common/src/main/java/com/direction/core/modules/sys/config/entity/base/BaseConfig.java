package com.direction.core.modules.sys.config.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseConfig extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -927553372041112867L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_config_pk")
	@GenericGenerator(name = "sys_config_pk", strategy = "uuid")
	@Column(name = "config_id", unique = true, nullable = false)
	private String configId;

	@Column(name = "config_no")
	private String configNo;

	@Column(name = "config_desc")
	private String configDesc;

	@Column(name = "config_value")
	private String configValue;

	@Column(name = "is_system", columnDefinition = "CHAR")
	@org.hibernate.annotations.Type(type="true_false")
	private boolean system;

	@Column(name = "status", columnDefinition = "CHAR")
	private String status;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getConfigId() {

		return configId;
	}

	public void setConfigId(String configId) {

		this.configId = configId;
	}

	public String getConfigNo() {

		return configNo;
	}

	public void setConfigNo(String configNo) {

		this.configNo = configNo;
	}

	public String getConfigDesc() {

		return configDesc;
	}

	public void setConfigDesc(String configDesc) {

		this.configDesc = configDesc;
	}

	public String getConfigValue() {

		return configValue;
	}

	public void setConfigValue(String configValue) {

		this.configValue = configValue;
	}

	public boolean isSystem() {

		return system;
	}
	
	public boolean getSystem() {

		return system;
	}

	public void setSystem(boolean system) {

		this.system = system;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
