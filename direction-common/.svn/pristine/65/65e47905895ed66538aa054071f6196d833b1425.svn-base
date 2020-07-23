package com.direction.common.utils.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;

import com.direction.common.result.ResultJson;

// ~ File Information
// ====================================================================================================================

/**
 * Hiberante validation 检核工具类.
 * 
 * <pre>
 * 	后台验证用.
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class HibernateValidationUtils {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * hiberante validation.
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> ResultJson validateEntity(T entity) {

		ResultJson result = ResultJson.success();
		
		Set<ConstraintViolation<Object>> sets = Validation.buildDefaultValidatorFactory().getValidator().validate(entity, Default.class);
		
		if (CollectionUtils.isNotEmpty(sets)) {
			result.setSuccess(false);
			
			for (ConstraintViolation<Object> cv : sets) {
				result.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
		}
		
		return result;
	}
	
	/**
	 * hiberante validation.
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> ResultJson validateEntityToHTML(T entity) {

		ResultJson result = ResultJson.success();
		
		Set<ConstraintViolation<Object>> sets = Validation.buildDefaultValidatorFactory().getValidator().validate(entity, Default.class);
		
		if (CollectionUtils.isNotEmpty(sets)) {
			result.setSuccess(false);
			
			StringBuilder errors = new StringBuilder();
			
			for (ConstraintViolation<Object> cv : sets) {
				if (errors.toString().length() > 0) {
					errors.append("；");
				}
				errors.append(cv.getMessage());
				
				result.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setMsg(errors.toString());
		}
		
		return result;
	}
}
