package com.direction.core.modules.sys.log.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.sys.log.IErrorLog;
import com.direction.core.modules.sys.log.entity.base.BaseErrorLog;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_error_log")
public class ErrorLog extends BaseErrorLog implements IErrorLog {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -7097553193179461203L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getExpTypeDesc() {
		if (StringUtils.isNotBlank(this.getExpType())) {
			return IErrorLog.ErrorLogStatus.getErrorLogStatus().get(this.getExpType());
		}
		return null;
	}
}
