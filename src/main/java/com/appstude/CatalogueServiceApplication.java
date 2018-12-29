package com.appstude;

import com.appstude.dao.CategoryRepository;
import com.appstude.dao.ProductRepository;
import com.appstude.entities.Category;
import com.appstude.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CatalogueServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository){
		return args ->{

			categoryRepository.deleteAll();
			Stream.of("C1 Ordinateurs","C2 Imprimantes").forEach(category->{
				categoryRepository.save(new Category(category.split(" ")[0],category.split(" ")[1],new ArrayList<>()));
			});

			categoryRepository.findAll().forEach(System.out::println);

			productRepository.deleteAll();
			Category c1 = categoryRepository.findById("C1").get();
			Stream.of("HP","DELL","ASUS","LENEVO","THINKPAD").forEach(product->{
				productRepository.save(new Product(null,product,Math.random()*1000,c1));
			});

			Category c2 = categoryRepository.findById("C2").get();
			Stream.of("AX3D","BAF5","COMPAX MAX","GERO 55K").forEach(product->{
				productRepository.save(new Product(null,product,Math.random()*1000,c2));
			});

			productRepository.findAll().forEach(System.out::println);
		};
	}

}

