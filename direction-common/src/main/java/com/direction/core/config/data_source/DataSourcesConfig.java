package com.direction.core.config.data_source;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// ~ File Information
// ====================================================================================================================

/**
 * 数据源配置.
 * 
 * <pre>
 * 数据源配置
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
public class DataSourcesConfig {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 定义主要默认数据源.
	 * 
	 * @return
	 */
	@Bean(name = "primaryDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
}
