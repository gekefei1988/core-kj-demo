package com.direction.spring.mvc.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// ~ File Information
// ====================================================================================================================

public abstract class BaseDao<T, ID extends Serializable> {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	private EntityManager entityManager;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	public EntityManager getEntityManager() {

		return entityManager;
	}

	@PersistenceContext(unitName = "entityManagerFactoryPrimary")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
