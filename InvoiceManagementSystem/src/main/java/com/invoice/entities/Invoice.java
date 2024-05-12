package com.invoice.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
public class Invoice {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @NotBlank(message = "Name should not be blank")
	    private String name;

	    @NotBlank(message = "Invoice number should not be blank")
	    private String invoiceNumber;

	    @NotNull(message = "Date is required")
	    private LocalDate date;

	    @NotNull(message = "Amount must have some value")
	    private BigDecimal amount;
	    
	    

	    @OneToOne
	    private customerDetails customerDetails;

	    public Invoice() {
	        super();
	        // Initialize customerDetails object
	        this.customerDetails = new customerDetails();
	    }

		public Invoice(Long id, @NotBlank(message = "Name should not be blank") String name,
				@NotBlank(message = "Invoice number should not be blank") String invoiceNumber,
				@NotNull(message = "Date is required") LocalDate date,
				@NotNull(message = "Amount must have some value") BigDecimal amount,
				customerDetails customerDetails) {
			super();
			this.id = id;
			this.name = name;
			this.invoiceNumber = invoiceNumber;
			this.date = date;
			this.amount = amount;
			this.customerDetails = customerDetails;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public customerDetails getCustomerDetails() {
			return customerDetails;
		}

		public void setCustomerDetails(customerDetails customerDetails) {
			this.customerDetails = customerDetails;
		}

		@Override
		public String toString() {
			return "Invoice [id=" + id + ", name=" + name + ", invoiceNumber=" + invoiceNumber + ", date=" + date
					+ ", amount=" + amount + ", customerDetails=" + customerDetails + "]";
		}


	
}
