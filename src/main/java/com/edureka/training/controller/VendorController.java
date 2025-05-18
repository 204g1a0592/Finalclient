package com.edureka.training.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.edureka.training.DTO.VendorProductDTO;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredentail;
import com.edureka.training.entity.Vendor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.PathItem.HttpMethod;


@Controller
@RequestMapping("clientvendor")
public class VendorController {
	@GetMapping("admin")
	public String dashborad() {
		return "admin_dashboard";
	}

	@GetMapping("addvendor")
	public String addvendor(Model model) {
	    VendorProductDTO dto = new VendorProductDTO();
	    dto.setVendor(new Vendor());
	    dto.setProduct(new Product());
	    model.addAttribute("vendorProductDTO", dto);
	    return "addVendor";
	}

    // Handle form submit for both add & update
    @PostMapping("addvendor")
    public String addvendor(@ModelAttribute VendorProductDTO dto, Model model) {
        System.out.println("from add vendor...." + dto.getProduct().getProductname());

        String apiUrl = "http://localhost:8091/apivendor/addvendor";  // REST API endpoint
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, dto, String.class);
        System.out.println("response......" + response);

        if (response.getBody().equalsIgnoreCase("success")) {
        	
            return "redirect:/clientvendor/vendorlist";  // redirect to avoid resubmission
        } else {
            model.addAttribute("error", "Vendor already exists");
            return "addVendor";  // return form with error
        }
    }

    // Show vendor list
    @GetMapping("/vendorlist")
    public String displayvendors(Model model) throws JsonMappingException, JsonProcessingException {
        String apiUrl = "http://localhost:8091/apivendor/displayvendor";
        RestTemplate restTemplate = new RestTemplate();
        //added now
        String vendorResponse = restTemplate.getForObject("http://localhost:8091/apivendor/displayvendor", String.class);
        System.out.println("Vendor JSON: " + vendorResponse);
//commenter noew
//        Vendor[] vendorsArray = restTemplate.getForObject(apiUrl, Vendor[].class);
//        List<Vendor> vendorsList = Arrays.asList(vendorsArray);
        ObjectMapper mapper = new ObjectMapper();
        Vendor[] vendors = mapper.readValue(vendorResponse, Vendor[].class);
        List<Vendor> vendorsList = Arrays.asList(vendors);
        if(vendorsList.size()==0) {
        	model.addAttribute("error", "No Vendor are availble");
        }
        else {
        model.addAttribute("vendors", vendorsList);
        }
        return "vendor_management";
    }

    // Show edit form with pre-filled data in the same addVendor form page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long vendorId, Model model) {
        String apiUrl = "http://localhost:8091/apivendor/edit/" + vendorId;
        RestTemplate restTemplate = new RestTemplate();

        Vendor vendor = restTemplate.getForObject(apiUrl, Vendor.class);

        VendorProductDTO dto = new VendorProductDTO();
        dto.setVendor(vendor);
        dto.setProduct(vendor.getProduct());

        model.addAttribute("vendorProductDTO", dto);
        return "addVendor";  // reuse same form page for editing
    }
    @GetMapping("/delete/{id}")
    public String deleteFormtable(@PathVariable("id") long vendorId, Model model) {
        String apiUrl = "http://localhost:8091/apivendor/delete/" + vendorId;
        RestTemplate restTemplate = new RestTemplate();

        Vendor vendor = restTemplate.getForObject(apiUrl, Vendor.class);

        VendorProductDTO dto = new VendorProductDTO();
        dto.setVendor(vendor);
        dto.setProduct(vendor.getProduct());

        model.addAttribute("vendorProductDTO", dto);
        return "addVendor";  // reuse same form page for editing
    }
}



