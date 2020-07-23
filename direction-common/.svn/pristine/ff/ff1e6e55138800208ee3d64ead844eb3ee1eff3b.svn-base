package com.direction.core.modules.sys.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.direction.core.inf.EIPService;
import com.direction.core.inf.WeightsConst;
import com.direction.core.inf.sys.org.IOrgTreeNode;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.user.entity.base.BaseUser;
import com.direction.spring.vo.FileVO;

// ~ File Information
// ====================================================================================================================

@Entity
@Table(name = "sys_user")
public class User extends BaseUser implements IUserInfo {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -4995910776841618506L;

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================
	
	/**
	 * 租户名称.
	 * 
	 * @return
	 */
	public String getTenantName() {
		return EIPService.getTenantService().getTenantName(this.getTenantId());
	}
	
	public List<FileVO> getAvatarFiles() {
		
		List<FileVO> files = new ArrayList<FileVO>();
		if (StringUtils.isNotBlank(getAvatar())) {
			FileVO file = new FileVO();
			file.setUrl(this.getAvatar());
			if (StringUtils.isNotBlank(this.getAvatar()) && StringUtils.isBlank(this.getAvatarId())) {
				file.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			files.add(file);
		}
		return files;
	}
	
	/**
	 * 获取状态说明.
	 * 
	 * @return
	 */
	public String getStatusDesc() {
		if (StringUtils.isNotBlank(this.getStatus())) {
			return UserStatus.getUserStatusMap().get(this.getStatus());
		}
		return UserStatus.getUserStatusMap().get(IUserInfo.UserStatus.ENABLE);
	}
	
	/**
	 * 用户类型.
	 * 
	 * @return
	 */
	public String getUserTypeDesc() {
		return UserType.getUserTypeMap().get(this.getUserType());
	}
	
	/**
	 * 获取权重.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserInfo#getWeights()
	 */
	@Override
	public int getWeights() {
		
		if (StringUtils.equals(IUserInfo.UserType.MANAGE, this.getUserType())) {
			return WeightsConst.WEIGHTS_MANAGER;
		}

		return IUserInfo.super.getWeights();
	}

	/**
	 * 机构名称.
	 * 
	 * @return
	 */
	public String getOrgName() {
		
		String orgName = null;
		
		if (StringUtils.isNotBlank(this.getOrgId())) {
			IOrgTreeNode node = EIPService.getOrgTreeService().getOrgTreeNode(this.getOrgId());
			if (node != null) {
				orgName = node.getOrgName();
			}
		}
		return orgName;
	}

	/**
	 * 获取名称.
	 * 
	 * @see com.direction.core.inf.sys.user.IUserInfo#getName()
	 */
	@Override
	public String getName() {
		
		if (StringUtils.isNotBlank(this.getNickName())) {
			return this.getNickName();
		}

		return this.getEmpName();
	}
	
	//~ Classes
	// ==================================================================================================================
}
