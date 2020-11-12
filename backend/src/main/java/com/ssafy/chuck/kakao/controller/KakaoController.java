package com.ssafy.chuck.kakao.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.chuck.picture.service.PictureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "카카오 챗봇 연동", tags = "Kakao")
@RestController
@RequestMapping("/chuck/kakao")
public class KakaoController {
	
	@Autowired
	private PictureService pictureService;
	@Autowired
    RestTemplate restTemplate;
	
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
	
	@GetMapping("/token")
	@ApiOperation(value = "카카오 토큰 얻기")
	public ResponseEntity<Map<String, Object>> token(){
		Map<String, Object> m = restTemplate.getForObject("http://kauth.kakao.com/oauth/authorize?client_id=39c29afd4d7851a73acd53290c07e56d&redirect_uri=http://k3a206.p.ssafy.io/kakao_oauth&response_type=code", Map.class);
		
		return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);
	}
	
	//카카오톡 오픈빌더로 리턴할 스킬 API
    @RequestMapping(value = "/connection" , method= {RequestMethod.POST , RequestMethod.GET },headers = {"Accept=application/json"})
    public HashMap<String,Object> callAPI(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
    	
    	System.out.println("request 출력!!!");
    	System.out.println(request.getParameterNames());
    	System.out.println(request.getParameter("user"));
    	System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    	
    	
    	HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
    	System.out.println("userRequest 출력!!!");
    	System.out.println(userRequest);
    	
    	HashMap<String, Object> user = (HashMap<String, Object>) userRequest.get("user");
    	System.out.println("user 출력!!!");
    	System.out.println(user);
    	
    	HashMap<String, Object> properties = (HashMap<String, Object>) user.get("properties");
    	System.out.println("properties 출력!!!");
    	System.out.println(properties);
    	
    	System.out.println("properties의 KeySet");
    	System.out.println(properties.keySet());
    	
    	System.out.println("plusfriendUserKey 출력!!");
    	System.out.println(properties.get("plusfriendUserKey"));
    	
    	properties.get("appUserID 출력!!");
    	System.out.println(properties.get("appUserId"));
    	
    	
//    	HashMap<String, Object> user = (HashMap<String, Object>) params.get("user");
//    	System.out.println("user 출력!!!");
//    	System.out.println(user);    	
    	
    	
    	HashMap<String,Object> action = (HashMap<String, Object>) params.get("action");
    	System.out.println("action 출력!!!");
    	System.out.println(action);
    	
    	HashMap<String,Object> detailParams = (HashMap<String, Object>) action.get("detailParams");
    	System.out.println("params 출력!!!");
    	System.out.println(detailParams);
    	
    	HashMap<String,Object> secureimage = (HashMap<String,Object>) detailParams.get("secureimage");
    	System.out.println("secureimage 출력!!!");
    	System.out.println(secureimage);
    	
    	String origin = secureimage.get("origin").toString();
    	System.out.println("origin 출력!!");
    	System.out.println(origin);
    	
    	String[] urls = origin.split(",");
    	if(urls.length >= 1) {
	    	urls[0] = urls[0].substring(5, urls[0].length());
	    	urls[urls.length-1] = urls[urls.length-1].substring(0, urls[urls.length-1].length() - 1);
    	}
    	for(String url : urls) {
    		System.out.println(url);
    		try {
				saveImage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

        HashMap<String, Object> resultJson = new HashMap<>();
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
//            System.out.println(jsonInString);

            List<HashMap<String,Object>> outputs = new ArrayList<>();
            HashMap<String,Object> template = new HashMap<>();
            HashMap<String, Object> simpleText = new HashMap<>();
            HashMap<String, Object> text = new HashMap<>();

            text.put("text","코딩32 발화리턴입니다.");
            simpleText.put("simpleText",text);
            outputs.add(simpleText);

            template.put("outputs",outputs);

            resultJson.put("version","2.0");
            resultJson.put("template",template);

        }catch (Exception e){

        }

        return resultJson;
    }

    
    private static void saveImage(String strUrl) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        int rand = (int)(Math.random()*100);
		Date nowdate = new Date();
		String dateString = formatter.format(nowdate) + rand;	//현재시간 문자열
        
        try {
            url = new URL(strUrl);
            in = url.openStream();
            out = new FileOutputStream("/home/ubuntu/s03p31a206/backend/python/kakao/" + dateString + ".jpg"); //저장경로
            while(true){
                //이미지를 읽어온다.
                int data = in.read();
                if(data == -1) break;
                //이미지를 쓴다.
                out.write(data);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(in != null){in.close();}
            if(out != null){out.close();}
        }
    }
	
}
