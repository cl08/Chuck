package com.ssafy.chuck.picture.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.chuck.picture.dto.PictureDto;
import com.ssafy.chuck.picture.dto.PictureResponse;
import com.ssafy.chuck.picture.service.PictureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "사진 관리", tags = "Picture")
@RestController
@RequestMapping("/chcuk/pictures")
public class PictureController {
	static final Logger logger = LoggerFactory.getLogger(PictureController.class);
	
	@Autowired
	private PictureService pictureService;
	
	@PostMapping("/insert")
	@ApiOperation(value = "사진 입력. path_list는 한 다이어리 안에 첨부된 사진들의 path list")
	public ResponseEntity<String> insertPicture(@RequestBody PictureResponse pictureResponse) {		
		for(int i=0;i<pictureResponse.getPath_list().size();i++) pictureService.insertPicture(pictureResponse.getDiary_id(), pictureResponse.getPath_list().get(i));
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/upload")
	@ApiOperation(value = "사진 업로드")
	public ResponseEntity<String> fileUpload(@RequestParam("filename") MultipartFile mFile, HttpServletRequest request){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowdate = new Date();
		String dateString = formatter.format(nowdate);	//현재시간 문자열
		
		String real_path = "/home/ubuntu/s03p31a206/back/src/main/webapp/resources/postRep/" + 
				dateString + "_" + mFile.getOriginalFilename();	//경로 + 날짜시간 + _ +파일이름으로 저장

		String access_path = "http://k3a206.p.ssafy.io/images/postRep/" + dateString + "_" + mFile.getOriginalFilename();
		
		try {
			mFile.transferTo(new File(real_path));							//실제경로로 파일을 저장
			return new ResponseEntity<String>(access_path, HttpStatus.OK);	//접근경로 return
		} catch (IOException e) {
			System.out.println("파일 업로드 실패");
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
}
