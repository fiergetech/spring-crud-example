package com.examplecrud.springbootcrud.repository;
import com.examplecrud.springbootcrud.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    Product findByName(String name);

}
