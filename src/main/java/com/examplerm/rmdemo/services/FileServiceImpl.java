package com.examplerm.rmdemo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.examplerm.rmdemo.services.interfaces.IFileService;

@Service
public class FileServiceImpl implements IFileService{
    private AmazonS3 s3client;

	private String ENDPOINT_URL = "****************************";
	
	private String BUCKET_NAME = "****************************";
	
	private String ACCESS_KEY = "****************************";
	
	private String SECRET_KEY = "****************************";

    @Override
    public String upload(MultipartFile multipartFile) {
        String fileUrl = "";
	    try {
	        File file = convertMultiPartToFile(multipartFile);
	        String fileName = generateFileName(multipartFile);
	        fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + fileName;
	        uploadFileTos3bucket(fileName, file);
	        file.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    return fileUrl;
    }
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private String generateFileName(MultipartFile multipartFile) {
	    return multipartFile.getOriginalFilename().replace(" ", "_");
	}
	
	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
	

	@PostConstruct 
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		s3client = AmazonS3ClientBuilder
				   .standard()
				   .withCredentials(new AWSStaticCredentialsProvider(credentials))
				   .withRegion(Regions.US_EAST_2)
				   .build();
	}
}
