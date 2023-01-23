package com.example.finalassignment.service;

import com.example.finalassignment.dto.ProductDto;
import com.example.finalassignment.model.Product;
import com.example.finalassignment.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(ProductDto productDto) {
        Product newProduct = new Product();

        newProduct.setTitle((productDto.title));

        return newProduct.getId();

    }

    public List<ProductDto> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product p : allProducts) {
            ProductDto newProductDto = new ProductDto();
            newProductDto.title = p.getTitle();

            productDtoList.add(newProductDto);
        }
        return productDtoList;

    }

    public void deleteProduct (@RequestBody Long id) {
        productRepository.deleteById(id);
    }
}
