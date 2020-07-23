package com.direction.common.exception;

// ~ File Information
// ====================================================================================================================

/**
 * 自定义Service exception.
 * 
 * <pre>
 * 	自定义Service exception
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class ServiceException extends RuntimeException {

	// ~ Static Fields
	// ==================================================================================================================

	private static final long serialVersionUID = -509520194502513376L;

	// ~ Fields
	// ==================================================================================================================
	
	private String code;
	
	// ~ Constructors
	// ==================================================================================================================
	
	public ServiceException() {

		super();
	}

	public ServiceException(String message) {

		super(message);
	}
	
	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(Throwable cause) {

		super(cause);
	}

	public ServiceException(String message, Throwable cause) {

		super(message, cause);
	}

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 返回错误编号.
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
}