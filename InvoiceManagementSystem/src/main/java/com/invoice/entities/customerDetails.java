package com.invoice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class customerDetails {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long cid;

	  
	    private String phoneNumber;

	   
	    private String address;

	    private String city;
	    
	    private String country;

		public customerDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		public customerDetails(Long cid, String phoneNumber, String address, String city, String country) {
			super();
			this.cid = cid;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.city = city;
			this.country = country;
		}







		public Long getCid() {
			return cid;
		}

		public void setCid(Long cid) {
			this.cid = cid;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

	   

		
	    
	    

	
	
	

}
