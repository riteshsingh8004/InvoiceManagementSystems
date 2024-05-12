package com.invoice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.invoice.entities.Invoice;
import com.invoice.entities.customerDetails;
import com.invoice.repository.InvoiceRepository;
import com.invoice.repository.customerDetailsRepository;

@Service
public class InvoiceServiceImpl {

	 private final InvoiceRepository invoiceRepository;
	  private final customerDetailsRepository customerDetailsRepository;
		/*
		 * @Autowired public InvoiceService(InvoiceRepository invoiceRepository) {
		 * this.invoiceRepository = invoiceRepository; }
		 */
	 
	  @Autowired
	    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, customerDetailsRepository customerDetailsRepository) {
	        this.invoiceRepository = invoiceRepository;
	        this.customerDetailsRepository = customerDetailsRepository;
	    }

	    public Page<Invoice> getAllInvoices(Pageable pageable) {
	        return invoiceRepository.findAll(pageable);
	    }

	    public Invoice getInvoiceById(Long id) {
	        return invoiceRepository.findById(id).orElse(null);
	    }

	    public Invoice createInvoice(Invoice invoice) {
	       
	        customerDetails customerDetails = invoice.getCustomerDetails();
	        customerDetailsRepository.save(customerDetails);
	       
	        invoice.setCustomerDetails(customerDetails);
	      
	        return invoiceRepository.save(invoice);
	    }

		
	    
		/*
		 * public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
		 * Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(id);
		 * if (existingInvoiceOptional.isPresent()) { Invoice existingInvoice =
		 * existingInvoiceOptional.get();
		 * existingInvoice.setName(updatedInvoice.getName());
		 * existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
		 * existingInvoice.setDate(updatedInvoice.getDate());
		 * existingInvoice.setAmount(updatedInvoice.getAmount()); //
		 * existingInvoice.setCustomerDetails(updatedInvoice.getCustomerDetails());
		 * 
		 * customerDetails updatedCustomerDetails = updatedInvoice.getCustomerDetails();
		 * existingInvoice.setCustomerDetails(updatedCustomerDetails);
		 * 
		 * return invoiceRepository.save(existingInvoice); } else {
		 * 
		 * throw new IllegalArgumentException("Invoice not found with ID: " + id); } }
		 */
	    
	    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
	        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(id);
	        if (existingInvoiceOptional.isPresent()) {
	            Invoice existingInvoice = existingInvoiceOptional.get();
	            existingInvoice.setName(updatedInvoice.getName());
	            existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
	            existingInvoice.setDate(updatedInvoice.getDate());
	            existingInvoice.setAmount(updatedInvoice.getAmount());
	            
	            // Retrieve the existing customer details
	            customerDetails existingCustomerDetails = existingInvoice.getCustomerDetails();
	            
	            // Update customer details
	            customerDetails updatedCustomerDetails = updatedInvoice.getCustomerDetails();
	            existingCustomerDetails.setPhoneNumber(updatedCustomerDetails.getPhoneNumber());
	            existingCustomerDetails.setAddress(updatedCustomerDetails.getAddress());
	            existingCustomerDetails.setCity(updatedCustomerDetails.getCity());
	            existingCustomerDetails.setCountry(updatedCustomerDetails.getCountry());
	            
	            return invoiceRepository.save(existingInvoice);
	        } else {
	            throw new IllegalArgumentException("Invoice not found with ID: " + id);
	        }
	    }

	    public void deleteInvoice(Long id) {
	        invoiceRepository.deleteById(id);
	    }
}


