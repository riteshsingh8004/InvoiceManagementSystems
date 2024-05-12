package com.invoice.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.invoice.Service.InvoiceServiceImpl;
import com.invoice.entities.Invoice;
import com.invoice.entities.customerDetails;
import com.invoice.helper.message;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/invoices")
public class InvoiceController {

	
	private final InvoiceServiceImpl invoiceService;

    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }
     
    
    @GetMapping("/")
    public String test(Model model) {
    	model.addAttribute("title","Home - Invoice Management");
         
        return "home";
    }
    
    @GetMapping("/AddInvoice")
    public String addInvoice(Model model) {
    	try {
    		model.addAttribute("title","Add-Invoice");
        	
       	 Invoice invoice = new Invoice();
       	 customerDetails customerDetails = new customerDetails();
       	 invoice.setCustomerDetails(customerDetails);
       	  model.addAttribute("invoice", invoice);
           
       	  return "add_invoice";
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "redirect:/invoices/error";
    	}
    }

    @PostMapping("/do_register")
    public String createInvoices(@Valid @ModelAttribute("invoice") Invoice invoice,
                                 BindingResult result,
                                 Model model,
                                 HttpSession session) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("invoice", invoice);
                return "add_invoice";
            }

            
            invoiceService.createInvoice(invoice);
            
            model.addAttribute("invoice", new Invoice());
            
            session.setAttribute("message", new message("Registration Successful!!", "alert-success"));

            return "add_invoice";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("invoice", invoice);
            session.setAttribute("message", new message("Something went wrong: " + e.getMessage(), "alert-danger"));
            return "add_invoice";
        }
    }
	    
    @GetMapping("/addInvoice")
	public String removeSessionAttribute(HttpSession session,Model model) {
	    session.removeAttribute("message");
	    
	    model.addAttribute("invoice",new Invoice());
	    return "add_invoice"; 
	}

///////////////////////// registration work done //////////// 
    
	/* show all invoice */
    
// per page 5 contact  per page=5[h]
    // current page = 0[page]  page means current page 
    
    @GetMapping("/allinvoice")
    public String getAllInvoices(@RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  Model model) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Invoice> invoicesPage = invoiceService.getAllInvoices(pageable);

        List<Invoice> invoices = invoicesPage.getContent();
        System.out.println("Number of invoices: " + invoices.size());

        model.addAttribute("invoices", invoices);
        model.addAttribute("totalPages", invoicesPage.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        return "all_invoices";
    }
    
	/* delete invoice */
    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("id") Long id, Model model, HttpSession session) {
        //System.out.println("id==="+id);
    	invoiceService.deleteInvoice(id);
        session.setAttribute("message", new message("Invoice Deleted Successfully...", "success"));
        return "redirect:/invoices/allinvoice";
    }
    
    
    ///// open update form controller
    
    @PostMapping("update-invoice/{id}")
    public String getInvoiceById(@PathVariable("id") Long id,Model model) {
    	System.out.println("csdfdgf==="+id);
    	
    	model.addAttribute("title", "Update Invoice");
    	Invoice invoice =invoiceService.getInvoiceById(id);
    	model.addAttribute("invoice",invoice);
    	return "update_invoice";
    }
    
	
    @PostMapping("/do_update")
    public String updateInvoice(@ModelAttribute("invoice") Invoice updatedInvoice, HttpSession session) {
        try {
            // Retrieve the existing invoice from the database
            Invoice existingInvoice = invoiceService.getInvoiceById(updatedInvoice.getId());
            
            
            existingInvoice.setName(updatedInvoice.getName());
            existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
            existingInvoice.setDate(updatedInvoice.getDate());
            existingInvoice.setAmount(updatedInvoice.getAmount());
            
            // this is for updating customer details
            customerDetails updatedCustomerDetails = updatedInvoice.getCustomerDetails();
            customerDetails existingCustomerDetails = existingInvoice.getCustomerDetails();
            
            existingCustomerDetails.setPhoneNumber(updatedCustomerDetails.getPhoneNumber());
            existingCustomerDetails.setAddress(updatedCustomerDetails.getAddress());
            existingCustomerDetails.setCity(updatedCustomerDetails.getCity());
            existingCustomerDetails.setCountry(updatedCustomerDetails.getCountry());
            
            // Save the updated invoice with updated customer details
            invoiceService.updateInvoice(existingInvoice.getId(), existingInvoice);
            
            session.setAttribute("message", new message("Invoice updated successfully.", "success"));
            return "redirect:/invoices/allinvoice";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            session.setAttribute("message", new message("Error updating invoice: " + e.getMessage(), "error"));
            return "redirect:/invoices/allinvoice";
        }
    }
    
	/* exception handle which is come in this controller */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        ex.printStackTrace();
        redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "redirect:/invoices/error";
    }
    
}
