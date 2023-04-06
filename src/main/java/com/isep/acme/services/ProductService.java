package com.isep.acme.services;

import java.util.Optional;
import com.isep.acme.model.Product;

public interface ProductService {

	Optional<Product> findBySku(final String sku);

	Product create(final Product manager);

	void deleteBySku(final String sku);
}
