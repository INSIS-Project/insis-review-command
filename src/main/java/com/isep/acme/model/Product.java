package com.isep.acme.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productID;

	@Column(nullable = false, unique = true)
	public String sku;

	public Product(String sku) {
		this.sku = sku;
	}

	public void setSku(String sku) {
		if (sku == null || sku.isBlank()) {
			throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
		}
		if (sku.length() != 12) {
			throw new IllegalArgumentException("SKU must be 12 characters long.");
		}

		this.sku = sku;
	}

}
