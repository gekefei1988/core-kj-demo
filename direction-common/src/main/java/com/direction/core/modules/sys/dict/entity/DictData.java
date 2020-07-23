package com.direction.core.modules.sys.dict.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.StatusConst;
import com.direction.core.inf.sys.dict.IDictData;
import com.direction.core.modules.sys.dict.entity.base.BaseDictData;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_dict_data")
public class DictData extends BaseDictData implements IDictData {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	public boolean isSystem() {
		return getSys() == null ? false : getSys();
	}

	public String getStatusDesc() {
		return StatusConst.getStatusMap().get(getStatus());
	}
	
	public String getStatusColor() {
		return StatusConst.getStatusColorMap().get(this.getStatus());
	}
}
