package com.ssafy.chuck.common.aspect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ssafy.chuck.user.dto.Response;
import com.ssafy.chuck.user.dto.UserDto;
import com.ssafy.chuck.user.service.UserService;

@Aspect
@Component
public class UserAspect {
	private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

	@Autowired
	UserService userService;

	@Before("@annotation(com.ssafy.chuck.common.annotation.LoginCheck)")
	private void LoginCheck(JoinPoint point) {
		logger.debug("회원가입인지 로그인인지 체크");
		Object[] parameterValues = point.getArgs();
		String accessToken = String.valueOf(parameterValues[0]);

		Gson gson = new Gson();
		Response response = gson.fromJson(accessToken, Response.class);
		Response userInfo = this.getUserInfo(response.getAccessToken());

		long userId = Long.parseLong(userInfo.getId());
		String name = userInfo.getData().get("nickname");

		UserDto user = userService.read(userId);

		parameterValues[1] = userId;
		parameterValues[2] = name;

		if(user != null) {
			parameterValues[3] = false;
		} else {
			parameterValues[3] = true;
		}
	}


	private String getResponseBody(HttpURLConnection conn) throws IOException {
		logger.debug("ResponseBody 요청 호출");
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}
		// System.out.println("response body : " + result);
		br.close();
		return result;
	}

	private Response getUserInfo(String accessToken) {
		logger.debug("유저정보 요청 호출");
		// Response Type 선언 (유저 정보 여러개 필요)
		Response response = new Response();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			// Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			String result = this.getResponseBody(conn);

			Gson gson = new Gson();
			response = gson.fromJson(result, Response.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
