package com.direction.core.modules.sys.log.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.sys.log.IOperLog;
import com.direction.core.modules.sys.log.entity.base.BaseOperLog;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_oper_log")
public class OperLog extends BaseOperLog {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = 3002144593360861057L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================
	
	public OperLog() {}
	
	public OperLog(String title, String operType, String params) {

		this.setTitle(title);
		this.setOperType(operType);
		this.setParams(params);
	}
	
	public OperLog(String title, String operType, String method, String operResult, String params) {

		this.setTitle(title);
		this.setOperType(operType);
		this.setMethod(method);
		this.setOperResult(operResult);
		this.setParams(params);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 操作类型说明.
	 * 
	 * @return
	 */
	public String getOperTypeDesc() {
		if (StringUtils.isNotBlank(getOperType())) {
			return IOperLog.OperLogType.getOperTypeMap().get(getOperType());
		}
		
		return null;
	}
}
