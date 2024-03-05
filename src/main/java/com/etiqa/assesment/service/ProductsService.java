package com.etiqa.assesment.service;

import com.etiqa.assesment.exceptions.ServiceException;
import com.etiqa.assesment.exceptions.NotFoundException;
import com.etiqa.assesment.model.Product;
import com.etiqa.assesment.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository repository;

    public List<Product> getProducts() {
        try {
            return repository.findAll();
        } catch (Exception e){
            throw new ServiceException("Failed to retrieve products",e);
        }
    }

    public Product getProduct(int bookId) {
        try {
            return repository.findById(bookId).orElse(null);
        } catch (Exception e){
            throw new ServiceException("Failed to retrieve order by ID: " + bookId, e);
        }
    }

    public void createProduct(Product products) {
        try {
             repository.save(products);
        } catch (Exception e) {
            throw new ServiceException("Failed to create product",e);
        }
    }

    public void updateProduct(int bookId, Product products) {
        if (!repository.existsById(bookId))  throw new NotFoundException("No Product ID Found");
        try {
            products.setBookId(bookId);
            repository.save(products);
        } catch (Exception e) {
            throw new ServiceException("Failed to update product with ID" +bookId,e);
        }
    }

    public void deleteProduct(int bookId) {
        try {
            if (!repository.existsById(bookId)) throw new NotFoundException("No Customer ID Found, Unable to delete");
            repository.deleteById(bookId);
        } catch(Exception e){
            throw new ServiceException("Failed to delete order with ID:"+ bookId,e);


        }
    }
}
