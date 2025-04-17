package com.online_shopping.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.online_shopping.DTO.EmailDTO;
import com.online_shopping.DTO.ResponseDto;
import com.online_shopping.DTO.User;
import com.online_shopping.shoppingService.online_shoppingService;
  

@Service
public class UserServiceImp implements online_shoppingService{
	  
	  private JavaMailSender javaMailSender;
	
	 private static Logger log=LoggerFactory.getLogger(UserServiceImp.class);
	
	public Object user_login(){
		log.info("=====user login service=====");
		ResponseDto response=new ResponseDto();
		String str="sahil";
		List<User> list=new ArrayList<>();
		list.add(new User("Sahil",25,7829292,"k@gmail.com"));
		list.add(new User("Dev",25,782929522,"k@gmail.com"));
		list.add(new User("Sajal",25,782927892,"k@gmail.com"));
		list.add(new User("Mukesh",25,78290292,"k@gmail.com"));
		list.add(new User("Rajesh",25,7823692,"k@gmail.com"));
		try {
			if(str.equalsIgnoreCase("sahil")) {
			response.setStatus("Success");
			response.setResponsemessage("User successfully loggedin");
			response.setResponseData(list);
			}
			else {
				response.setStatus("Failed");
				response.setResponsemessage("User not successfully loggedin");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			response.setStatus("Failed");
			response.setResponsemessage("something went wrong");
		}
		return response;
		
	}
	
	public Object getByUserName(String userName) {
		log.info("=====user Name service=====");
		ResponseDto response=new ResponseDto();
		List<User> list=new ArrayList<>();
		User user=null;
		list.add(new User("Sahil",25,7829292,"sahil@gmail.com"));
		list.add(new User("Dev",27,382929522,"Dev@gmail.com"));
		list.add(new User("Sajal",35,48292792,"Sajal@gmail.com"));
		list.add(new User("Mukesh",45,8290292,"Mukesh@gmail.com"));
		list.add(new User("Rajesh",65,9823692,"Rajesh@gmail.com"));
		try {
			
			if(list.isEmpty()) {
				response.setStatus("Failed");
				response.setResponsemessage("User not found");
			
			}
			
			else {
				log.info("=====checking user Name =====");
				/*for(User st:list)
				{
				    String fs=st.getUserName();
				    List<User> list2=new ArrayList<>();
				    list2= st.stream().filter(e->
				    	e.getUserName().equalsIgnoreCase(userName)
				    ).findFirst().get();
				    if(fs.equalsIgnoreCase(userName))
				    {
				    	List<User> list1=new ArrayList<>();
				    	int i=list.indexOf(st.getUserName());
				    	list1.add(i, st);
				    	response.setResponseData(list1);
				    }
				}*/
				user=list.stream().filter(e->e.getUserName().equalsIgnoreCase(userName)).findFirst().get();
				response.setResponseData(user);
				response.setStatus("Success");
				response.setResponsemessage("User found successfully ");
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			log.error("something went wrong in finding UserName");
			response.setStatus("Failed");
			response.setResponsemessage(e.getMessage());
		}
		return response;
	}
	
	@Autowired
	public UserServiceImp(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public Object getByEmailId(EmailDTO email) {

		log.info("=====User emails details serviceImpl=====");
		ResponseDto response = new ResponseDto();
		List<User> list = new ArrayList<>();
		User user = null;
		list.add(new User("Sahil", 25, 7829292, "sahilcapgi@gmail.com"));
		list.add(new User("Dev", 27, 382929522, "Dev@gmail.com"));
		list.add(new User("Sajal", 35, 48292792, "Sajal@gmail.com"));
		list.add(new User("Mukesh", 45, 8290292, "Mukesh@gmail.com"));
		list.add(new User("Rajesh", 65, 9823692, "Rajesh@gmail.com"));
		try {
			boolean flag = false;
			for (User st : list) {
				String fs = st.getEmailId();
				String em = email.getTo();
				if (fs.equalsIgnoreCase(em)) {
					log.info("+++++++++sending mail to :" + em);
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(em);
					message.setSubject(email.getSubject());
					message.setText(email.getBody());
					flag = true;
					javaMailSender.send(message);
					response.setResponseData(message);
					response.setResponsemessage("Mail has been sent");
					response.setStatus("success");
				}
			}
			if (flag == false) {
				response.setResponsemessage("Mail has not been sent due to incorrect Email address");
				response.setStatus("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			log.error("something went wrong in sending Email");
			response.setStatus("Failed");
			response.setResponsemessage(e.getMessage());
		}
		return response;

	}

}
