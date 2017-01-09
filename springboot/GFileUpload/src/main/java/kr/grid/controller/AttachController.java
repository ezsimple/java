package kr.grid.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/attach")
public class AttachController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired Environment env;

	// 여기서는 application.properties 의 값을 불러올 수 없다.
	// @Value("${multipart.path }")
	// String multipartPath = "attachments";
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> uploadAttachment(@RequestParam("uploadfile") MultipartFile[] uploadFiles) {
    	
    	List<UploadAttachmentResponse> responses = new ArrayList<UploadAttachmentResponse>();
		final String uploadfilePath = env.getProperty("path.uploadfile");

    	try {
    		String sourceFileName;
    		String sourceFileNameExtension;
    		String filePath;
    		String destinationFileName;
    		File destinationFile;

			for(MultipartFile uploadFile: uploadFiles) {
    			sourceFileName = uploadFile.getOriginalFilename();
    			sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
    			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;

    			// 상대경로를 절대경로로 변경
    			String today = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
    			Path path = Paths.get(uploadfilePath+"/"+today+"/",destinationFileName);
    			filePath = path.toAbsolutePath().toString();
    			logger.info(filePath);

				// 임시 폴더에서 지정경로로 파일 이동
    			destinationFile = new File(filePath);
				destinationFile.getParentFile().mkdirs();
				uploadFile.transferTo(destinationFile);

				// 파일정보 저장 (서비스&DAO 로직 추가 필요)
				UploadAttachmentResponse response = new UploadAttachmentResponse();
				response.setFileName(uploadFile.getOriginalFilename());
				response.setFileSize(uploadFile.getSize());
				response.setFileContentType(uploadFile.getContentType());
				response.setAttachmentUrl(path.toString());
				responses.add(response);
			}

    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    
// application.properties의 값을 불러오는 방법이다.
//    public String getServerPath() {
//    	String addr = env.getProperty("management.address");
//    	String port = env.getProperty("management.port");
//    	String contextPath = env.getProperty("management.context-path");
//    	final String ServerPath = addr+":"+port+"/"+contextPath;
//    	return ServerPath;
//    }

    // 임시로 사용한다.
    @NoArgsConstructor
    @Data
    private static class UploadAttachmentResponse {
        private String fileName;
        private long fileSize;
        private String fileContentType;
        private String attachmentUrl;
    }

}