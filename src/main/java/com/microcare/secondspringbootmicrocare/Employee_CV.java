package com.microcare.secondspringbootmicrocare;

import org.springframework.web.multipart.MultipartFile;

public class Employee_CV {
	private int fileId;
	private MultipartFile file_content;
	private int employee_id;
	private String fileName;
	private long file_size;
	private byte[] content;
	
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	private String content_type;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public MultipartFile getFile_content() {
		return file_content;
	}
	public void setFile_content(MultipartFile file_content) {
		this.file_content = file_content;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
}
