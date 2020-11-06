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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.chuck.picture.dto.ClusterListResponse;
import com.ssafy.chuck.picture.dto.ClusterResponse;
import com.ssafy.chuck.picture.dto.PathListResponse;
import com.ssafy.chuck.picture.dto.PictureDto;
import com.ssafy.chuck.picture.dto.PictureResponse;
import com.ssafy.chuck.picture.service.PictureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "사진 관리", tags = "Picture")
@RestController
@RequestMapping("/chuck/pictures")
public class PictureController {
	static final Logger logger = LoggerFactory.getLogger(PictureController.class);

	@Autowired
	private PictureService pictureService;

	@Autowired
    RestTemplate restTemplate;

	@PostMapping("/insert")
	@ApiOperation(value = "다이어리 글 작성을 완료시, 사진들을 업로드해서 얻은 accessPath 리스트를 realPath로 변경해서 DB에 저장 및 클러스터링")
	public ResponseEntity<String> insertPicture(@RequestBody PictureResponse pictureResponse) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<pictureResponse.getPath_list().size();i++) {
			String real_path = "/home/ubuntu/s03p31a206/backend/python/" + pictureResponse.getPath_list().get(i).split("images/")[1];
			pictureService.insertPicture(pictureResponse.getDiary_id(), real_path);
			sb.append(real_path + ":");
		}
		String obj = restTemplate.getForObject("http://127.0.0.1:5000/insert?groupId=" + pictureResponse.getGroup_id() + "&imagePath=" + sb.toString(), String.class);
		System.out.println(obj);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/modify")
	@ApiOperation(value = "수정된 다이어리의 accessPath 리스트로 DB, Pickle 수정 및 Clutering")
	public ResponseEntity<String> modify(@RequestBody PictureResponse pictureResponse){
		// diary id에 포함된 사진 리스트.
		List<PictureDto> list = pictureService.selectPictureByDiaryId(pictureResponse.getDiary_id());
		System.out.println(list);
		// diary id에 포함된 사진들을 모두 삭제.
		// 해당 path list의 사진들로 모두 db에 insert
		
		// pickle 수정. 기존 사진의 pickle 모두 삭제 및 새 사진 pickle 추가
		
		// clustering.
		for(int i=0;i<pictureResponse.getPath_list().size();i++) {
			String real_path = "/home/ubuntu/s03p31a206/backend/python/" + pictureResponse.getPath_list().get(i).split("images/")[1];

		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	

	@DeleteMapping("/deleteByPath")
	@ApiOperation(value = "사진의 상대 경로로 사진 삭제(File)")
	public ResponseEntity<String> deletePictureByPath(String path) {
		String real_path = "/home/ubuntu/s03p31a206/backend/python/" + path.split("images/")[1];
		System.out.println(real_path);
		//1. 파일 삭제
		File file = new File(real_path);
		file.delete(); 
		//2. DB에서 삭제. 필요없음
//		pictureService.deletePictureByPath(path);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

//	@DeleteMapping("/deleteById")
//	@ApiOperation(value = "사진의 ID로 사진 삭제")
//	public ResponseEntity<String> deletePictureById(int id) {
//		pictureService.deletePictureById(id);
//		return new ResponseEntity<String>("success", HttpStatus.OK);
//	}
 
	@GetMapping("/person_clustering")
	@ApiOperation(value = "인물 분류 페이지(인물 분류 페이지에 접근할 때, 그룹별 클러스터링 결과를 반환)")
	public ResponseEntity<ClusterListResponse> personClustering(int groupId) throws Exception {

		//3. flask와 연결하여 클러스터링한 결과 반환
		List<ClusterResponse> clusterResponseList = new ArrayList<>();

		String obj = restTemplate.getForObject("http://127.0.0.1:5000/getInfo?groupId=" + groupId, String.class);
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(obj);
		JsonArray jsonArray = jsonObject.get("info").getAsJsonArray();
		System.out.println(jsonArray);
		for(int i=0;i<jsonArray.size();i++) {
			JsonObject element = (JsonObject) jsonArray.get(i);
			String rep = "http://k3a206.p.ssafy.io/images/" + element.get("rep").getAsString().split("python/")[1];
			JsonArray list = element.getAsJsonArray("paths");

			List<String> pathList = new ArrayList<>();
			for(int j=0;j<list.size();j++) pathList.add("http://k3a206.p.ssafy.io/images/" + list.get(j).getAsString().split("python/")[1]);

			clusterResponseList.add(new ClusterResponse(rep, pathList));
    	}

		ClusterListResponse clusterListResponse = new ClusterListResponse(clusterResponseList);
		return new ResponseEntity<ClusterListResponse>(clusterListResponse, HttpStatus.OK);
	}

	@PostMapping("/upload")
	@ApiOperation(value = "사진 업로드(사진이 업로드 될 때마다) - 그룹id로 폴더 생성, 그룹 폴더 내 사진 저장", produces = "multipart/form-data")
	public ResponseEntity<String> fileUpload(
		@RequestPart("file") MultipartFile mFile, HttpServletRequest request, int groupId){
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
			return new ResponseEntity<String>(access_path, HttpStatus.OK);	// 접근경로 return
		} catch (IOException e) {
			System.out.println("파일 업로드 실패");
			return new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
	}
	
	
	
	@PostMapping("/mkVideo")
	@ApiOperation(value = "Path List로 동영상 생성 후 동영상의 경로 return")
	public ResponseEntity<String> mkVideo(@RequestBody PathListResponse pathResponse) {

		//개인 ID의 폴더 생성
		String path = "/home/ubuntu/s03p31a206/backend/python/videos/" + pathResponse.getUserId();
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
		System.out.println(pathResponse);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<pathResponse.getPath_list().size();i++) {
			String real_path = "/home/ubuntu/s03p31a206/backend/python/" + pathResponse.getPath_list().get(i).split("images/")[1];
			sb.append(real_path + ":");
		}
		String obj = restTemplate.getForObject("http://127.0.0.1:5000/video?userid=" + pathResponse.getUserId() + "&paths=" + sb.toString(), String.class);

		return new ResponseEntity<String>(path + "/final.mp4", HttpStatus.OK);
	}


}
