package com.boot.test;

import java.util.List;
import java.net.URI;
import java.util.LinkedHashMap;

import org.springframework.web.client.RestTemplate;

import com.boot.model.User;

public class SpringBootRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/api";

	@SuppressWarnings("unused")
	private static void listAllUsers() {
		System.out.println("Testing listAllUser API......");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> userMap = restTemplate.getForObject(REST_SERVICE_URI + "/user/",
				List.class);

		if (userMap != null) {
			for (LinkedHashMap<String, Object> map : userMap) {
				System.out.println("User id=" + map.get("id") + ", Name=" + map.get("name") + ", Age=" + map.get("age")
						+ ", Salary=" + map.get("salarey"));
			}
		} else {
			System.out.println("No user exist......");
		}
	}

	/**
	 * GET
	 */
	private static void getUser() {
		System.out.println("Testing getUser......");
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
		System.out.println(user);
	}

	/**
	 * POST
	 */
	private static void createUser() {
		System.out.println("Testing createUser....");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User(5, "Sarah", 32, 340000);
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
		System.out.println("Location :" + uri.toASCIIString());
	}

	/**
	 * PUT
	 */
	private static void updateUser() {
		System.out.println("Testing update User API........");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User(1, "Tom", 33, 70000);
		restTemplate.put(REST_SERVICE_URI + "/user/1", user);
		System.out.println(user);
	}

	/**
	 * DELETE
	 */
	private static void deleteUser() {
		System.out.println("Testing delete User API..........");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/3");
	}

	/**
	 * DELETE
	 */
	private static void deleteAllUser() {
		System.out.println("Testing delete all user API............");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}

	public static void main(String[] args) {
		listAllUsers();
		getUser();
		createUser();
		updateUser();
		deleteUser();
		deleteAllUser();
	}

}
