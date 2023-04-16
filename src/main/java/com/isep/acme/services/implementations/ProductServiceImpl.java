package com.isep.acme.services.implementations;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Optional<Product> findBySku(String sku) {
		return repository.findBySku(sku);
	}

	@Override
	public Product create(final Product product) {
		return repository.save(product);
	}

	@Override
	public void deleteBySku(String sku) {
		repository.deleteBySku(sku);
	}    
}
