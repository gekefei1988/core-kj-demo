package com.direction.core.modules.sys.config.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.config.entity.Config;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

@Repository
public interface ConfigRepository extends BaseRepository<Config, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 根据编号查询系统参数设置.
	 * 
	 * @param configNo
	 * @return
	 */
	@Query("from Config where configNo = ?1")
	Config getConfig(String configNo);
}
