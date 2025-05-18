

package com.edureka.training.controller;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.edureka.training.entity.Product;
import com.edureka.training.entity.PurchaseInvoice;
import com.edureka.training.entity.Vendor;

@Controller
@RequestMapping("/clientpurchaseinvoice")
//public class PurchaseInvoiceController {
//
//    @GetMapping("purchase-invoices")
//    public String invoices(Model model) {
//    	String apiUrl = "http://localhost:8091/apipurchaseinvoice/displayinvoices";  // REST API endpoint
//        RestTemplate restTemplate = new RestTemplate();
//
//       // ResponseEntity<PurchaseInvoice> response = restTemplate.postForEntity(apiUrl, dto, String.class);
//        PurchaseInvoice[] vendorsArray = restTemplate.getForObject(apiUrl, PurchaseInvoice[].class);
// 	   // List<Vendor> vendorsList = Arrays.asList(vendorsArray);
// 	    List<PurchaseInvoice> products =  Arrays.asList(vendorsArray);
// 	    model.addAllAttributes(products);
//    	return "purchase_invoice";
//    }
//}

public class PurchaseInvoiceController {
	
	//added today
//	@PostMapping("addinvoice")
//	public String saveProduct(@RequestBody Vendor vendor,
//	                          @RequestParam("price") Double price,
//	                          @RequestParam("quantity") Integer quantity,
//	                          Model model) {
//
//	    // Prepare Product
//	  //  Product product = new Product();
//		PurchaseInvoice invoice=new PurchaseInvoice();
//	    invoice.setVendor(vendor);
//	    invoice.setPrice(price);
//	    invoice.setQuantity(quantity);
//	   
//	    //System.out.println("product diplay  "+ product.getPrice()+"  "+product.getQuantity());
//	    String apiUrl = "http://localhost:8091/apipurchaseinvoice/addinvoice";
//	    RestTemplate restTemplate = new RestTemplate();
//
//	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, invoice, String.class);
//
//	    // Always reload vendor list after response
//	    String vendorApiUrl = "http://localhost:8091/apivendor/displayvendor";
//	    Vendor[] vendorsArray = restTemplate.getForObject(vendorApiUrl, Vendor[].class);
//	    List<Vendor> vendorsList = Arrays.asList(vendorsArray);
//	    model.addAttribute("vendors", vendorsList);
//	    //new content added here today
//	    String inapi="http://localhost:8091/apipurchaseinvoice/addinvoice";
//	    PurchaseInvoice[] invoices=restTemplate.getForObject(inapi, PurchaseInvoice[].class);
//	    
//	    
//	    if ("success".equalsIgnoreCase(response.getBody())) {
//	        model.addAttribute("mess", "Product purchased successfully");
//	    } else {
//	        model.addAttribute("mess", "Purchase failed");
//	    }
//
//	    return "addProduct";  // vendors list now always available
//	}
	 @GetMapping("/viewinvoices")
	    public String viewInvoices(Model model) {
	        String apiUrl = "http://localhost:8091/apiipurchasenvoice/all";
	        RestTemplate restTemplate = new RestTemplate();
	        PurchaseInvoice[] invoices = restTemplate.getForObject(apiUrl, PurchaseInvoice[].class);
	        model.addAttribute("invoices", Arrays.asList(invoices));
	        return "purchase_invoice"; // maps to viewInvoices.html
	    }

}
