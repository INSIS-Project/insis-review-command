package com.isep.acme.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;

@Component
// @Profile("bootstrap")
public class ProductBootstrapper implements CommandLineRunner {

	@Autowired
	private ProductRepository pRepo;

	@Override
	public void run(String... args) throws Exception {

		Product p1 = new Product("asd578fgh267");
		pRepo.save(p1);

		Product p2 = new Product("c1d4e7r8d5f2");
		pRepo.save(p2);

		Product p3 = new Product("c4d4f1v2f5v3");
		pRepo.save(p3);

		Product p4 = new Product("v145dc2365sa");
		pRepo.save(p4);

		Product p5 = new Product("fg54vc14tr78");
		pRepo.save(p5);

		Product p6 = new Product("12563dcfvg41");
		pRepo.save(p6);

		Product p7 = new Product("vcg46578vf32");
		pRepo.save(p7);

		Product p8 = new Product("vgb576hgb675");
		pRepo.save(p8);

		Product p9 = new Product("unbjh875ujg7");
		pRepo.save(p9);

		Product p10 = new Product("u1f4f5e2d2xw");
		pRepo.save(p10);

		Product p11 = new Product("j85jg76jh845");
		pRepo.save(p11);

		Product p12 = new Product("g4f7e85f4g54");
		pRepo.save(p12);

	}
}
