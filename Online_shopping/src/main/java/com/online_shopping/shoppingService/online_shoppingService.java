package com.online_shopping.shoppingService;

import org.apache.catalina.connector.Response;
import org.springframework.http.RequestEntity;

import com.online_shopping.DTO.EmailDTO;

public interface online_shoppingService {

	Object user_login();

	Object getByUserName(String userName);

	Object getByEmailId(EmailDTO email);		

}
