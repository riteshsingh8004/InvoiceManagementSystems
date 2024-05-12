package com.invoice.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.invoice.entities.Invoice;

public interface InvoiceServices {

	 public Page<Invoice> getAllInvoices(Pageable pageable);
	 
	 public Invoice getInvoiceById(Long id);
	 
	 public Invoice createInvoice(Invoice invoice);
	 
	 public Invoice updateInvoice(Long id, Invoice updatedInvoice);
	 
	 public void deleteInvoice(Long id);
}
