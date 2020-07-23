package com.direction.core.modules.sys.role.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.direction.core.inf.sys.role.IRole;
import com.direction.core.modules.sys.role.entity.base.BaseRole;

// ~ File Information
// ====================================================================================================================

/**
 * 角色基础数据.
 * 
 * <pre>
 * 	角色基础数据
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseRole implements IRole {

	// ~ Static Fields
	// ==================================================================================================================
	
	private static final long serialVersionUID = -1357949700959822124L;
	
	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================
	
	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 是否系统.
	 * 
	 * @return
	 */
	public boolean isSystem() {
		return this.getSys() == null ? false : this.getSys();
	}
}
