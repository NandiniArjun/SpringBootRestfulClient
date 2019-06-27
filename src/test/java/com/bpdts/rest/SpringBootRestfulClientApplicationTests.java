package com.bpdts.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bpdts.rest.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestfulClientApplicationTests {

    RestTemplate restTemplate = new RestTemplate();;

	@Test
    public void testAllUsersList() {
		User[] users = restTemplate.getForObject("https://bpdts-test-app.herokuapp.com/users", User[].class);

        Assert.assertEquals(users.length, 1000);
    }

 
}
