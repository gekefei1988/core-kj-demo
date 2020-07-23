package com.direction.core.modules.sys.log.repository;

import org.springframework.stereotype.Repository;

import com.direction.core.modules.sys.log.entity.ErrorLog;
import com.direction.spring.mvc.repository.BaseRepository;

// ~ File Information
// ====================================================================================================================

/**
 * 错误日志数据层.
 * 
 * <pre>
 * 	错误日志数据层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Repository
public interface ErrorLogRepository extends BaseRepository<ErrorLog, String> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

}
