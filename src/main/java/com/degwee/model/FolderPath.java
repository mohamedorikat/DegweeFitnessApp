package com.degwee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="folderpath")
public class FolderPath {
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="FolderPath")
	@NotNull
	private String folderPath;
	@Column(name="FolderDescription")
	private String folderDescription;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public String getFolderDescription() {
		return folderDescription;
	}
	public void setFolderDescription(String folderDescription) {
		this.folderDescription = folderDescription;
	}

}
