package com.ssafy.chuck.kakao.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "카카오 챗봇 연동", tags = "Kakao")
@RestController
@RequestMapping("/chuck/kakao")
public class KakaoController {
	
	@PostMapping("/upload")
	@ApiOperation(value = "카카오 챗봇 파일 업로드")
	public ResponseEntity<String> fileUploadForKakao(@RequestPart("filename") MultipartFile mFile, HttpServletRequest request, int id){

		//1. 개인별 폴더 생성
		String path = "/home/ubuntu/s03p31a206/backend/python/kakao/" + id;
		File folder = new File(path);
		if(!folder.exists()) {
			try {
				folder.mkdir();
				System.out.println("폴더 생성");
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		else System.out.println("이미 폴더가 있습니다.");
		
		//2. 개인별 폴더에 사진 저장
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowdate = new Date();
		String dateString = formatter.format(nowdate);	//현재시간 문자열
		String real_path = "/home/ubuntu/s03p31a206/backend/python/kakao/" + id + "/" + 
				dateString + "_" + mFile.getOriginalFilename();			//경로 + 날짜시간 + _ +파일이름으로 저장
		String access_path = "http://k3a206.p.ssafy.io/images/kakao/" + id + "/" + dateString + "_" + mFile.getOriginalFilename();
		
		try {
			mFile.transferTo(new File(real_path));							// 실제경로로 파일을 저장
			return new ResponseEntity<String>(access_path, HttpStatus.OK);	// 접근경로 return
		} catch (IOException e) {
			System.out.println("파일 업로드 실패");
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "계정별 올린 사진 리스트 반환")
	public ResponseEntity<List<String>> getList(int id){
		String path = "/home/ubuntu/s03p31a206/backend/python/kakao/" + id;
//		String path = "C:\\Users\\multicampus\\s03p31a206\\backend\\python";
		File folder = new File(path);
		File[] fileList = folder.listFiles();
		List<String> list = new ArrayList<>();
		for(int i = 0; i < fileList.length;i++) list.add("http://k3a206.p.ssafy.io/images/kakao/" + id + "/" + fileList[i].getName());
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	@ApiOperation(value = "계정별 사진 삭제")
	public ResponseEntity<String> delete(String path){
		String realPath = "/home/ubuntu/s03p31a206/backend/python/kakao/" + path.split("kakao/")[1];
		File file = new File(realPath);
		file.delete();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PostMapping("test")
	@ApiOperation(value = "챗봇 post test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@PostMapping("test2")
	@ApiOperation(value = "챗봇 post test")
	public ResponseEntity<String> test2(int id){
		return new ResponseEntity<String>("success" + id, HttpStatus.OK);
	}
	
}
