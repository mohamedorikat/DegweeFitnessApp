package com.degwee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="report")
public class Report {
		@Id
		@Column(name="Id")
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer Id;
		
		@ManyToOne
		@JoinColumn(name="ClientId",referencedColumnName="ClientId",nullable=false)
		private Client client;
		
		@ManyToOne
		@JoinColumn(name="FolderPathId",referencedColumnName="Id",nullable=false)
		private FolderPath folderPath;
		
		@Column(name="ReportName")
		@NotNull
		private String reportName;
		
		@Column(name="ReportDate")
		@NotNull
		@DateTimeFormat(pattern="dd/MM/yyyy")
		private Date reportDate;

		public Integer getId() {
			return Id;
		}

		public void setId(Integer id) {
			Id = id;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public FolderPath getFolderPath() {
			return folderPath;
		}

		public void setFolderPath(FolderPath folderPath) {
			this.folderPath = folderPath;
		}

		public String getReportName() {
			return reportName;
		}

		public void setReportName(String reportName) {
			this.reportName = reportName;
		}

		public Date getReportDate() {
			return reportDate;
		}

		public void setReportDate(Date reportDate) {
			this.reportDate = reportDate;
		}
		
		

}
