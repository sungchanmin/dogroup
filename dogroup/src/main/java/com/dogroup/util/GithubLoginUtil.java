package com.dogroup.util;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Github API를 사용하기 위한 Util
 * @author NYK
 *
 */
public class GithubLoginUtil {

	/**
	 * private 멤버변수 설명
	 * CLIENT_ID_FILE_PATH : API 요청을 위해 필요한 client id값이 들어있는 txt 파일 위치
	 * CLIENT_SECRET_FILE_PATH : API 요청을 위해 필요한 client secret값이 들어있는 txt 파일 위치
	 */
	private static String CLIENT_ID_FILE_PATH = "C:\\client_id.txt";
	private static String CLIENT_SECRET_FILE_PATH = "C:\\client_secret.txt";
	
	/**
	 * public 멤버변수 설명
	 * AUTHORIZE_URL : Access token 요청을 위해 필요한 1회성 인증 code를 요청하는 URL
	 * REDIRECT_URL : AUTHORIZE_URL에의 요청에서 사용자 인증이 성공했을 때 github가 redirect 시켜줄 URL
	 * ACCESS_TOKEN_URL : AUTHORIZE_URL에의 요청을 통해 발급받은 code로 access token을 요청할 때 사용되는 URL
	 * USER_EMAIL_URL : ACCESS_TOKEN_URL에의 요청을 통해 발급받은 Access Token으로 user email을 요청한다.
	 */
	public static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
	public static final String REDIRECT_URL = "/auth/github/callback";
	public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
	public static final String USER_EMAIL_URL = "https://api.github.com/user/emails";
	
	/**
	 * 로컬 파일의 내용을 읽어와서 반환한다.
	 * @param path			로컬파일 경로
	 * @return				파일 내용
	 * @throws Exception	파일을 읽어오지 못할 경우 발생
	 */
	private static String getApiClientKeyFileValue(String path) throws Exception {
		StringBuffer sb = new StringBuffer();
		//FILE_PATH에 있는 파일을 읽어온다
		File txtFile = new File(path);
		Scanner sc = new Scanner(new FileReader(txtFile));
		while(sc.hasNextLine()) {
			sb.append(sc.nextLine());
		}
		//읽어온 값
		String clientkey = sb.toString();
		sc.close();
		return clientkey;
	}
	
	/**
	 * API 요청에 필요한 client id 값을 반환한다.
	 * client id 값 : CLIENT_ID_FILE_PATH의 파일 내용
	 * @return				client id 값
	 * @throws Exception	값을 읽어오지 못할 때 발생한다.
	 */
	public static String getApiClientId() throws Exception {
		return getApiClientKeyFileValue(CLIENT_ID_FILE_PATH);
	}
	
	/**
	 * API 요청에 필요한 client secret 값을 반환한다.
	 * client secret 값 : CLIENT_SECRET_FILE_PATH의 파일 내용
	 * @return				client secret 값
	 * @throws Exception	값을 읽어오지 못할 때 발생한다.
	 */
	public static String getApiClientSecret() throws Exception {
		return getApiClientKeyFileValue(CLIENT_SECRET_FILE_PATH);
	}
	
	/**
	 * AUTHORIZE_URL에의 요청을 통해 발급받은 인증 code로 access token을 요청한다.
	 * @param code			AUTHORIZE_URL에의 요청을 통해 발급받은 인증 code
	 * @return				ACCESS_TOKEN_URL에의 요청을 통해 발급받은 Access Token
	 * @throws Exception	요청 과정에서 문제가 발생했을 때 발생한다.
	 */
	public static String getAccessToken(String code) throws Exception {
		RestTemplate rt = new RestTemplate();
		
		//요청 파라미터를 세팅한다.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", getApiClientKeyFileValue(CLIENT_ID_FILE_PATH));
		params.add("client_secret", getApiClientKeyFileValue(CLIENT_SECRET_FILE_PATH));
		params.add("code", code);
		
		//요청 헤더를 세팅한다.
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		//위에서 세팅한 파라미터와 헤더를 바탕으로 요청 entity를 만들고, POST 요청을 보낸다.
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		ResponseEntity<String> response = rt.exchange(ACCESS_TOKEN_URL,HttpMethod.POST,entity,String.class);
		
		//응답의 JSON 문자열을 Parsing한다.
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response.getBody(),
				new TypeReference<Map<String, Object>>() {});
		
		//Parsing한 Map에서 accessToken을 가져온다.
		String accessToken = (String) map.get("access_token");
		return accessToken;
	}
	
	/**
	 * ACCESS_TOKEN_URL에의 요청을 통해 발급받은 Access Token으로 user email을 요청한다. 
	 * @param accessToken		ACCESS_TOKEN_URL에의 요청을 통해 발급받은 Access Token
	 * @return					USER_EMAIL_URL에의 요청을 통해 받아온 사용자의 email 값
	 */
	public static String getUserEmail(String accessToken) throws Exception {
		RestTemplate rt = new RestTemplate();
		
		//요청 헤더를 세팅한다.
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(accessToken);
		
		//위에서 세팅한 파라미터와 헤더를 바탕으로 요청 entity를 만들고, GET 요청을 보낸다.
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(USER_EMAIL_URL, HttpMethod.GET, entity,String.class);
		
		//응답의 JSON 문자열을 Parsing한다. List의 첫번째 요소가 email값으로 온다.
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> map = mapper.readValue(response.getBody(),
				new TypeReference<List<Map<String, Object>>>() {});
		Map<String, Object> emailMap = map.get(0);
		
		//Parsing한 Map에서 email을 가져온다.
		String email = (String) emailMap.get("email");
		return email;
	}
}
