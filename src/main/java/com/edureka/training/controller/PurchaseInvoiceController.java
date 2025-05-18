

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
//@RequestMapping("/clientpurchaseinvoice")
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
	@GetMapping("/client/purchase-invoices")
public String showInvoices(
    @RequestParam(required = false) String search, 
    Model model) {
    
    RestTemplate restTemplate = new RestTemplate();
    String baseApiUrl = "http://localhost:8091/apiinvoice"; // example API base

    List<PurchaseInvoice> invoices;

    if (search != null && !search.isEmpty()) {
        // Call your search API with query param ?search=...
        String searchApiUrl = baseApiUrl + "/search?query=" + search;
        PurchaseInvoice[] responseArray = restTemplate.getForObject(searchApiUrl, PurchaseInvoice[].class);
        invoices = responseArray != null ? Arrays.asList(responseArray) : new ArrayList<>();
    } else {
        // Get last 5 invoices API endpoint
        String last5ApiUrl = baseApiUrl + "/last5";
        PurchaseInvoice[] responseArray = restTemplate.getForObject(last5ApiUrl, PurchaseInvoice[].class);
        invoices = responseArray != null ? Arrays.asList(responseArray) : new ArrayList<>();
    }

    model.addAttribute("invoices", invoices);
    model.addAttribute("purchaseInvoice", new PurchaseInvoice());
    model.addAttribute("search", search);

    return "purchase_invoice";
}
}
