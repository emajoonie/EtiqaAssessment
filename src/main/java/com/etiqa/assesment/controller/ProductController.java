package com.etiqa.assesment.controller;

import com.etiqa.assesment.exceptions.NotFoundException;
import com.etiqa.assesment.model.Product;
import com.etiqa.assesment.model.Response;
import com.etiqa.assesment.service.ProductsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductsService service;

    @GetMapping
    public ResponseEntity<Response<List<Product>>> getProducts() {
        return sendSuccessResponse(service.getProducts());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Response<Product>> getProduct(@PathVariable int bookId) {
        logger.info("Request: bookId:"+ bookId);
        Product products = service.getProduct(bookId);

        if (service.getProduct(bookId) != null)
            return sendSuccessResponse(products);
        else
            throw new NotFoundException("No bookID Found");

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Response<Product>> updateProduct(@Valid @RequestBody Product products, @PathVariable int bookId){
        logger.info("Request:"+ dataToString(products));
        service.updateProduct(bookId,products);
        return sendSuccessResponse(products,"bookId="+bookId+" successfully updated");

    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProducts(@Valid @RequestBody Product products) {
        logger.info("Request:"+ dataToString(products));
        service.createProduct(products);
        return sendSuccessResponse(products, "Successfully added new product");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Response<Void>> deleteProduct(int bookId) {
        logger.info("Request:bookId"+ bookId);
        service.deleteProduct(bookId);
        return sendSuccessResponse("Product deleted successfully");
    }


}
