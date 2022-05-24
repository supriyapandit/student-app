package com.employee_app.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.employee_app.entity.User;

public class RestClient {
	
	private static final String GET_ALL_USER_API = "http://localhost:8080/users/getAllUsers";
	private static final String GET_USER_BY_ID_API = "http://localhost:8080/users/getUserById/{id}";
	private static final String CREATE_USER__API = "http://localhost:8080/users/createUser";
	private static final String UPDATE_USER_API = "http://localhost:8080/users/updateUser/{id}";
	private static final String DELETE_USER_API = "http://localhost:8080/users/deleteUser/{id}";
	
	
	static RestTemplate restTemplate = new RestTemplate(); 
	
	public static  void main(String[] args) {
		CallGetAllUsers();
		callGetUserById();
		callCreateUserApi(); 
		callUpdateuserApi();
		callDeleteUser();

		
	}
	
	public static void CallGetAllUsers(){
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 
		 HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		 
		 ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USER_API, HttpMethod.GET, entity, String.class); 
		 System.out.println(result);
		
	}
	
		private static void callGetUserById() {
		
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 2);
		
		User user = restTemplate.getForObject(GET_USER_BY_ID_API, User.class, param);
		System.out.println(user.getFirstname());
		System.out.println(user.getLastname());
		System.out.println(user.getEmail());
	}
	
		
	private static void callCreateUserApi() {
		User user = new User("Ashwani ", "Shetty","Ashwani@gmail.com");
		ResponseEntity<User> user2 = restTemplate.postForEntity(CREATE_USER__API, user, User.class);
		System.out.println(user2.getBody());
	}
	
	private static void callUpdateuserApi() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 3);
		User Updateuser = new User("priya", "Samant","priya@gmail.com");	
		restTemplate.put(UPDATE_USER_API, Updateuser, param);
	
	}
	
	
	private static void callDeleteUser() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 2);
		restTemplate.delete(DELETE_USER_API, param);
	}
}
