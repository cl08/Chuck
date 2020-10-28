package com.ssafy.chuck.picture.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.chuck.picture.dto.ClusterListResponse;
import com.ssafy.chuck.picture.dto.ClusterResponse;
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
	
	@Autowired
    RestTemplate restTemplate;

	@PostMapping("/insert")
	@ApiOperation(value = "사진 입력. path_list는 한 다이어리 안에 첨부된 사진들의 path list")
	public ResponseEntity<String> insertPicture(@RequestBody PictureResponse pictureResponse) {		
		for(int i=0;i<pictureResponse.getPath_list().size();i++) pictureService.insertPicture(pictureResponse.getDiary_id(), pictureResponse.getPath_list().get(i));
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteByPath")
	@ApiOperation(value = "사진의 경로로 사진 삭제")
	public ResponseEntity<String> deletePictureByPath(String path) {		
		pictureService.deletePictureByPath(path);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById")
	@ApiOperation(value = "사진의 ID로 사진 삭제")
	public ResponseEntity<String> deletePictureById(int id) {		
		pictureService.deletePictureById(id);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/test")
	@ApiOperation(value = "flask 통신")
	public ResponseEntity<ClusterListResponse> test(int groupId, String imagePath) throws Exception {
		
		//3. flask와 연결하여 클러스터링한 결과 반환
		List<ClusterResponse> clusterResponseList = new ArrayList<>();
		
		String obj = restTemplate.getForObject("http://127.0.0.1:5000/cluster?groupId=" + groupId + "&imagePath=" + imagePath, String.class);
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(obj);
		JsonArray jsonArray = jsonObject.get("info").getAsJsonArray();
		System.out.println(jsonArray);
		for(int i=0;i<jsonArray.size();i++) {
			JsonObject element = (JsonObject) jsonArray.get(i);
			String rep = element.get("rep").getAsString();
			JsonArray list = element.getAsJsonArray("paths");
			
			List<String> pathList = new ArrayList<>();
			for(int j=0;j<list.size();j++)  pathList.add(list.get(j).getAsString());
			
			clusterResponseList.add(new ClusterResponse(rep, pathList));
    	}
		
		ClusterListResponse clusterListResponse = new ClusterListResponse(clusterResponseList);
		return new ResponseEntity<ClusterListResponse>(clusterListResponse, HttpStatus.OK);
	}
	
	@GetMapping("/person_clustering")
	@ApiOperation(value = "인물 분류 페이지")
	public ResponseEntity<ClusterListResponse> personClustering(int groupId) throws Exception {
		
		//3. flask와 연결하여 클러스터링한 결과 반환
		List<ClusterResponse> clusterResponseList = new ArrayList<>();
		
		String obj = restTemplate.getForObject("http://127.0.0.1:5000/cluster?groupId=" + groupId, String.class);
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(obj);
		JsonArray jsonArray = jsonObject.get("info").getAsJsonArray();
		System.out.println(jsonArray);
		for(int i=0;i<jsonArray.size();i++) {
			JsonObject element = (JsonObject) jsonArray.get(i);
			String rep = element.get("rep").getAsString();
			JsonArray list = element.getAsJsonArray("paths");
			
			List<String> pathList = new ArrayList<>();
			for(int j=0;j<list.size();j++)  pathList.add(list.get(j).getAsString());
			
			clusterResponseList.add(new ClusterResponse(rep, pathList));
    	}
		
		ClusterListResponse clusterListResponse = new ClusterListResponse(clusterResponseList);
		return new ResponseEntity<ClusterListResponse>(clusterListResponse, HttpStatus.OK);
	}
	
	@PutMapping("/upload")
	@ApiOperation(value = "사진 업로드")
	public ResponseEntity<String> fileUpload(@RequestPart("filename") MultipartFile mFile, HttpServletRequest request, int groupId, int diary_id){
		
		//1. 그룹 ID의 폴더 생성
		String path = "/home/ubuntu/s03p31a206/backend/python/" + groupId;
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
		
		//2. 그룹 폴더 안에 사진 저장
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowdate = new Date();
		String dateString = formatter.format(nowdate);	//현재시간 문자열
		String real_path = "/home/ubuntu/s03p31a206/backend/python/" + groupId + "/" + 
				dateString + "_" + mFile.getOriginalFilename();			//경로 + 날짜시간 + _ +파일이름으로 저장
		String access_path = "http://k3a206.p.ssafy.io/images/" + groupId + "/" + dateString + "_" + mFile.getOriginalFilename();
		
		try {
			mFile.transferTo(new File(real_path));							// 실제경로로 파일을 저장
			pictureService.insertPicture(diary_id, access_path);			// DB에 접근경로 Insert
			return new ResponseEntity<String>(access_path, HttpStatus.OK);	// 접근경로 return
		} catch (IOException e) {
			System.out.println("파일 업로드 실패");
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
	
	
}
