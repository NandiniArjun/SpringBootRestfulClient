package com.bpdts.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.bpdts.rest.model.User;

@SpringBootApplication
public class SpringBootRestfulClientApplication {
	
	static final String URL_USERS_BY_LOCATION = "https://bpdts-test-app.herokuapp.com/city/{city}/users";

	static final String URL_USERS = "https://bpdts-test-app.herokuapp.com/users";
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity entity = new HttpEntity(headers);
		
		HttpEntity<User[]> response = restTemplate.exchange(URL_USERS_BY_LOCATION,
			    HttpMethod.GET,
			    entity,
			    User[].class,
			    "London"
			);
		if (response != null) { 
			System.out.println("================= Users by Location ====================");
			for (User u : response.getBody()) { 
				System.out.println("User: " +u.getId() + " - " + u.getFirst_name()); 
			}
			System.out.println("========================================================");
		}

		User[] list = restTemplate.getForObject(URL_USERS, User[].class);
		System.out.println("================= Users by Coordinates ====================");
		for (User u : list) { 
			double distance = distance(u.getLatitude(), u.getLongitude(), 51.50853, -0.12574);
			if(distance <= 50.0)
				System.out.println("User: " +u.getId() + " - " + u.getFirst_name()); 
		}
		System.out.println("========================================================");
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist);
		}
	}
}
