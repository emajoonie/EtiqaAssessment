package com.etiqa.assesment.repository;

import com.etiqa.assesment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Integer> {

}
