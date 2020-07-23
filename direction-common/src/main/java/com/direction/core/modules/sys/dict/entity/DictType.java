package com.direction.core.modules.sys.dict.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.StatusConst;
import com.direction.core.modules.sys.dict.entity.base.BaseDictType;

// ~ File Information
// ====================================================================================================================

/**
 * 字典类型.
 * 
 * <pre>
 * 字典类型
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_dict_type")
public class DictType extends BaseDictType {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	public DictType() {

	}

	// ~ Methods
	// ==================================================================================================================

	public String getStatusDesc() {

		return StatusConst.getStatusMap().get(this.getStatus());
	}

	public String getStatusColor() {

		return StatusConst.getStatusColorMap().get(this.getStatus());
	}

	public boolean isSystem() {

		return getSys() == null ? false : this.getSys();
	}
}
