//package com.example.token.jwt;
//
//import java.util.ArrayList;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserDetailsService {
//
//	//@Autowired
//	//LoginService loginService;
//	//@Autowired
//	//protected AppLogger appLogger;
//	
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//    	System.out.println(".......userNameuserName............"+userName);
//        //Logic to get the user form the Database
//    	//Login loginDoc = null;
//		try {
//			// loginDoc = loginService.isValidUser(userName);
//		
//		}catch (Exception e) {
//			
//			//appLogger.appErrorLog("Exception : ", e);
//			return new User(userName,"martham",new ArrayList<>());
//		//	http://localhost:8080/api/student/authenticate/rest?user=girish&password=martham
//		}
//		
//       // return new User("admin","password2",new ArrayList<>());
//		//System.out.println(".......loadUserByUsername............"+loginDoc);
//		//return new User(loginDoc.getUserName(),loginDoc.getPassword(),new ArrayList<>());
//		return new User("girish","martham",new ArrayList<>());
//    }
//}