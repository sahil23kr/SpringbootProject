package com.online_shopping.Controller;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online_shopping.DTO.EmailDTO;
import com.online_shopping.shoppingService.online_shoppingService;

@RestController
public class ShoppingController {
	
	@Autowired
	online_shoppingService online_shoppingService;
	
	private static Logger log=LoggerFactory.getLogger(ShoppingController.class);
	@GetMapping("/user_login")
	public Object user_login()
	{
		log.info("======User login controller=====");
		
			return online_shoppingService.user_login();
	}
	
	@GetMapping("/getByUserName/{username}")
	public Object getByUserName(@PathVariable("username") String userName )
	{
		log.info("======find by UserName  controller=====");
		
		return online_shoppingService.getByUserName(userName);
	}
	
	@GetMapping("/sendEmail")
	public Object sendEmail(@RequestBody EmailDTO email){
     log.info("======Send Email controller=====");
		
		return online_shoppingService.getByEmailId(email);
	}
		

}
