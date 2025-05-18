package com.edureka.training.controller;

import java.util.HashMap;


import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestTemplate;

import com.edureka.training.entity.Login;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredentail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/client")
public class UserController {
	
	
	@GetMapping("/products")
	public String getAllProducts(Model model, @AuthenticationPrincipal Login user) {
	    RestTemplate restTemplate = new RestTemplate();
	    String apiUrl = "http://localhost:8091/api/allProducts"; // Match your REST endpoint

	    ResponseEntity<Product[]> response = restTemplate.getForEntity(apiUrl, Product[].class);
	    Product[] products = response.getBody();

	    model.addAttribute("username", user.name());
	    model.addAttribute("products", products);

	    return "user_dashboard"; // This should be the page you styled
	}

	@GetMapping("/userlogin")
	public String userlogin() {
		return "user";
	}
	@GetMapping("adminlogin")
	public String adminlogin() {
		return "admin";
	}
	@GetMapping("register")
	public String register() {
		return "register";
	}
	@GetMapping("product")
	public String product() {
		return "product_management";
	}
	@GetMapping("vendor")
	public String vendor() {
		return "vendor_management";
	}
	@GetMapping("add_vendor")
	public String addvendor() {
		return "addVendor";
	}
	@GetMapping("fordashboard")

	public String dashboard() {
		return "admin_dashboard";
	}

	@GetMapping("addproduct")

	public String addproduct() {
		return "addProduct";
	}


	
	
	@PostMapping("loginsuccess")
	@GetMapping("/user/dashboard")

	public String dashboard(@AuthenticationPrincipal Login user, Model model) {
	    String apiUrl = "http://localhost:8091/api/loginvalidation"; 
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, user, String.class);
	    System.out.println("respones.........."+response);
	    if(response.getBody().contains(user.name())) {
	    	 model.addAttribute("username", user.name()); // e.g., "John Doe"
	 	    return "user_dashboard";
	    //	 return "user_dashboard";
	    	
	    }
	    else {
	    	model.addAttribute("error", "Invalid Credentails");
	    	return "user";
	    }
	}
	@PostMapping("admin")
	public String admin(@ModelAttribute Login user, Model model) {
		String apiUrl="http://localhost:8091/api/admin";
		RestTemplate resttemplate=new RestTemplate();
		ResponseEntity<String> response= resttemplate.postForEntity(apiUrl, user, String.class);
		System.out.println("responde form aadmin..."+response);
		if(response.getBody().contains("Samyuktha") && response.getBody().contains("123456")) {
			model.addAttribute("mess", "Samyuktha");
			return "admin_dashboard";
		}
		else {
			model.addAttribute("error", "Invalid credentails");
			return "admin";
		}
		
		
	}
	 @PostMapping("/register")
	    public String registerUser(@ModelAttribute UserCredentail user, Model model) {
		
	        String apiUrl = "http://localhost:8091/api/register";  // REST API endpoint
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, user, String.class);
	        System.out.println("response......"+response);
	        if (!response.getBody().contains("null")) {
	        	System.out.println("succufull......."+response.getBody().contains("null"));
	            return "redirect:/client/userlogin";  // Redirect to login page
	        } else if( response.getBody().toString().substring(18, 22).equalsIgnoreCase("null")){
	            model.addAttribute("error", "User Name already exists");
	            return "register";  // Show registration form again
	        }
	        else {//if(response.getBody().toString().substring(response.getBody().indexOf('e'), 36).equalsIgnoreCase("null")){
	        	model.addAttribute("error", "User with email already exists");
	            return "register";
	        }
	 }
//	        @PostMapping("/loginsuccess")
//	        public String login(@ModelAttribute Login user, Model model) {
//	        	   String apiUrl = "http://localhost:8091/api/loginvalidation"; 
//	        	   RestTemplate restTemplate = new RestTemplate();
//	   	      
////	       
//	            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl,user, String.class);
//
//	            // Basic validation
//	            if (!response.getBody().contains("Invalid credentials")) {
//		        	System.out.println("succufull......."+response.getBody().contains("null"));
//		            return "redirect:/client/dashboard";  // Redirect to login page
//	            }
//		        else {
//		        	model.addAttribute("error", "Invalid credentails");
//		            return "user";
//		        }
//	        }

	    }


