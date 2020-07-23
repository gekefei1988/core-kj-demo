package com.direction.core.config.data_source;

import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

// ~ File Information
// ====================================================================================================================

/**
 * 核心数据源配置.
 * 
 * <pre>
 * 	核心数据源配置
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary", basePackages = {
  "com.direction.core.modules.**.repository"
})
public class PrimaryDataSourceConfig {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// 注入主要数据源
	@Resource(name = "primaryDataSource")
	private DataSource primaryDataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired(required = false)
	private PersistenceUnitManager persistenceUnitManager;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 主要的实体管理工厂.
	 * 
	 * @param builder
	 * @return
	 */
	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {

		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "entityManagerFactoryBuilderPrimary")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilderPrimary() {

		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return new EntityManagerFactoryBuilder(adapter, getVendorProperties(), persistenceUnitManager);
	}

	/**
	 * 设置实体类所在位置.
	 */
	@Primary
	@Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {

		return builder
		  .dataSource(primaryDataSource)
		    .packages("com.direction.core.modules.**.entity")
		    .persistenceUnit("primaryPersistenceUnit")
		    .properties(getVendorProperties())
		    .build();
	}

	/**
	 * 设置事物管理.
	 * 
	 * @param builder
	 * @return
	 */
	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
		
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}

//	private Map<String, Object> getVendorProperties() {
//
//		return jpaProperties.getHibernateProperties(new HibernateSettings());
//	}
	
	private Map<String, String> getVendorProperties() {
		return jpaProperties.getProperties();
	}
}
