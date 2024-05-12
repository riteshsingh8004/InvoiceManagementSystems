package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice ,Long> {

}
