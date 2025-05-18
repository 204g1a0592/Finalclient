package com.edureka.training.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.edureka.training.DTO.ProductDTO;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.Vendor;

@Controller
@RequestMapping("/clientproduct")
public class ProductController {
	
	@GetMapping("productlist")
	public String showAllProducts(Model model) {
		String apiUrl = "http://localhost:8091/apiproduct/allproducts";
	    RestTemplate restTemplate = new RestTemplate();

	    Product[] vendorsArray = restTemplate.getForObject(apiUrl, Product[].class);
	   // List<Vendor> vendorsList = Arrays.asList(vendorsArray);
	    List<Product> products =  Arrays.asList(vendorsArray);
	    model.addAttribute("products", products);
	    return "productlist"; // Thymeleaf template
	}

	@PostMapping("saveproduct")
	public String saveProduct(@RequestParam("productid") Long productId,
	                          @RequestParam("price") Double price,
	                          @RequestParam("quantity") Integer quantity,
	                          Model model) {

	    // Prepare Product
	    Product product = new Product();
	    product.setProductid(productId);
	    product.setPrice(price);
	    product.setQuantity(quantity);

	    String apiUrl = "http://localhost:8091/apiproduct/updatepriceandqty";
	    RestTemplate restTemplate = new RestTemplate();

	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, product, String.class);

	    // Always reload vendor list after response
	    String vendorApiUrl = "http://localhost:8091/apivendor/displayvendor";
	    Vendor[] vendorsArray = restTemplate.getForObject(vendorApiUrl, Vendor[].class);
	    List<Vendor> vendorsList = Arrays.asList(vendorsArray);
	    model.addAttribute("vendors", vendorsList);

	    if ("success".equalsIgnoreCase(response.getBody())) {
	        model.addAttribute("mess", "Product purchased successfully");
	    } else {
	        model.addAttribute("mess", "Purchase failed");
	    }

	    return "addProduct";  // vendors list now always available
	}



	@GetMapping("/purchase")
	public String showPurchasePage(Model model) {
		System.out.println("frompurgvhjkl");
	    // Call your API to get the vendor list
	    String apiUrl = "http://localhost:8091/apivendor/displayvendor";
	    RestTemplate restTemplate = new RestTemplate();

	    Vendor[] vendorsArray = restTemplate.getForObject(apiUrl, Vendor[].class);
	    List<Vendor> vendorsList = Arrays.asList(vendorsArray);

	    // Add vendors list to model
	    model.addAttribute("vendors", vendorsList);

	    // Return the Thymeleaf template name (purchase.html or whatever)
	    return "addProduct";  // match your HTML filename without extension
	}


	@PostMapping("/addproducttotable")
	public String addproducttotable(@ModelAttribute Product product, @RequestParam Long vendorId, Model model) {
	    Vendor vendor = new Vendor();
	    vendor.setVendorid(vendorId);
	    product.setVendor(vendor);

	    String apiUrl = "http://localhost:8091/apiproduct/addproduct";
	    RestTemplate restTemplate = new RestTemplate();

	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, product, String.class);

	    model.addAttribute("mess", "Product added successfully");

	    return "addProduct"; 
	}


//	    private final String apiBaseUrl = "http://localhost:8081/api/vendors"; // Adjust your REST API base URL
//
//	    // Show Add Product Page (with list of vendor products)
	@GetMapping("/purchaselist")
	public String showPurchaseProducts(Model model) {
	    String apiUrl = "http://localhost:8091/apiproduct/all"; // Your API returning List<PurchaseDTO>
	    RestTemplate restTemplate = new RestTemplate();
	    System.out.println("eneter client contoller");
	    ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(apiUrl, ProductDTO[].class);
	   ProductDTO[] purchaseArray = response.getBody();
	   System.out.println("retured fimr api"+ purchaseArray);
	   List<ProductDTO> list=Arrays.asList(purchaseArray);
	   System.out.println(list.get(0).getPrice());
	    model.addAttribute("purchases", Arrays.asList(purchaseArray));
	    return "addProduct";  // Your Thymeleaf view name
	}

	 




}
