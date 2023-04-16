package com.isep.acme.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;

@Service
public interface ProductService {

	Optional<Product> findBySku(final String sku);

	Product create(final Product manager);

	void deleteBySku(final String sku);
}
