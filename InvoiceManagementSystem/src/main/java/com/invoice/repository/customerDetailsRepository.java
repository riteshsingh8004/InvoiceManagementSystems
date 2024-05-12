package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entities.customerDetails;



public interface customerDetailsRepository extends JpaRepository<customerDetails ,Long>  {

}
