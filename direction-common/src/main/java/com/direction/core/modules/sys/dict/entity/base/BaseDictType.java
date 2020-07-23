package com.direction.core.modules.sys.dict.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.direction.common.annotation.QueryType;
import com.direction.common.annotation.SearchColumn;
import com.direction.spring.mvc.entity.DataEntity;

// ~ File Information
// ====================================================================================================================

@MappedSuperclass
public class BaseDictType extends DataEntity {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -5959214290529876999L;

	// ~ Fields
	// ==================================================================================================================

	@Id
	@GeneratedValue(generator = "sys_dict_type_pk")
	@GenericGenerator(name = "sys_dict_type_pk", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "dict_name")
	@NotBlank(message = "字典名称不能为空")
	@SearchColumn(queryType = QueryType.LIKE)
	private String dictName;

	@Column(name = "dict_type")
	@NotBlank(message = "类型名称不能为空")
	private String dictType;

	@Column(name = "status", columnDefinition = "CHAR")
	@NotBlank(message = "状态不能为空")
	private String status;

	@Column(name = "is_sys", columnDefinition = "CHAR")
	@Type(type = "true_false")
	private Boolean sys;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getDictName() {

		return dictName;
	}

	public void setDictName(String dictName) {

		this.dictName = dictName;
	}

	public String getDictType() {

		return dictType;
	}

	public void setDictType(String dictType) {

		this.dictType = dictType;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public Boolean getSys() {

		return sys;
	}

	public void setSys(Boolean sys) {

		this.sys = sys;
	}
}
