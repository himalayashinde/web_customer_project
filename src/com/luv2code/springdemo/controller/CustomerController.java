package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the customerService
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		//get customers from DAO
		List<Customer> theCustomer= customerService.getCustomers();
		
		// Add the customers to the model
		
		theModel.addAttribute("customers",theCustomer);
		
		return "list-customers";

	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerID") int theId,Model theModel) {
		
		// get the customer from the Service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set the customer as a model attribute to prepopulate the form
		theModel.addAttribute("customer",theCustomer);
		
		//send over to our form 
		return "customer-form";	
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerID") int theId) {
		
		customerService.deleteCustomer(theId);
				
		return "redirect:/customer/list";
		
		
	}
}
