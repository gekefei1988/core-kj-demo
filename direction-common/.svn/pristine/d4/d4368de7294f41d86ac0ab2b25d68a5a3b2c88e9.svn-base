package com.direction.spring.mvc.repository.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import com.direction.spring.mvc.repository.support.BaseDirRepositoryImpl;

// ~ File Information
// ====================================================================================================================

public class BaseRepositoryFactoryBean<JR extends Repository<T, ID>, T, ID extends Serializable>
    extends JpaRepositoryFactoryBean<JR, T, ID> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	public BaseRepositoryFactoryBean(Class<? extends JR> repositoryInterface) {
		super(repositoryInterface);
		System.out.println("THIS IS BaseRepositoryFactoryBean");
	}

	// ~ Methods
	// ==================================================================================================================

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		return new BaseRepositoryFactory(em);
	}

	// 用内部类完成工厂
	private static class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager em;

		public BaseRepositoryFactory(EntityManager em) {
			super(em);
			this.em = em;
		}

		// 设置=实现类是BaseRepositoryImpl
		@Override
		protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,
		    EntityManager entityManager) {

			JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(information.getDomainType());
			Object repository = this.getTargetRepositoryViaReflection(information, new Object[] {
			  entityInformation,
			  entityManager
			});
			
			Assert.isInstanceOf(BaseDirRepositoryImpl.class, repository);
			return (JpaRepositoryImplementation) repository;
		}

		// 设置自定义实现类class
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseDirRepositoryImpl.class;
		}
	}
}
