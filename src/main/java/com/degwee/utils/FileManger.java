package com.degwee.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.degwee.model.FolderPath;

public class FileManger {
	
	private UploadedFile uploadedFile;
	private StreamedContent downloadedFile = null;
	
	
	public FileManger(UploadedFile uploadedFile) {
		this.uploadedFile=uploadedFile;
	}
	
	
	public StreamedContent handleFiledownload(String fileName, String path) throws Exception {
		InputStream stream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));
		if (stream != null) {
			downloadedFile=new DefaultStreamedContent(stream, FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName), fileName);
		}
		stream.close();
		return downloadedFile;
	}
	
	public int uploadFile(FolderPath folderPath)
	{
		// return 0 ==> success
		// return 1 ==> failure file already exists
		//return 2==>error happened while uploading
		//return 3==>No file uplaoded
		String fileName=null;
		if(this.uploadedFile!= null)
		{
			fileName=uploadedFile.getFileName();
			Path imagesPath=Paths.get(folderPath.getFolderPath()).resolve(fileName);
			return uploadFileByPath(imagesPath);
		}
		return 3;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public StreamedContent getDownloadedFile() {
		return downloadedFile;
	}


	public void setDownloadedFile(StreamedContent downloadedFile) {
		this.downloadedFile = downloadedFile;
	}


	private int uploadFileByPath(Path path) {
		
		try {
			Files.write(path, uploadedFile.getContents(), StandardOpenOption.CREATE_NEW);
			return 0;
		}catch (FileAlreadyExistsException e) {
			return 1;
		} catch (IOException e1) {
			return 2;
		}
	}
	

}
