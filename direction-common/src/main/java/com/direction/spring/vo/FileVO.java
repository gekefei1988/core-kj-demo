package com.direction.spring.vo;

// ~ File Information
// ====================================================================================================================

public class FileVO {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================

	private String id;

	private String name;

	private String url;

	private String status = "finished";

	// ~ Constructors
	// ==================================================================================================================
	
	public FileVO() {

	}
	
	public FileVO(String id, String url) {
		this.id = id;
		this.name = id;
		this.url = url;
	}
	
	public FileVO(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	// ~ Methods
	// ==================================================================================================================


	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}
}
