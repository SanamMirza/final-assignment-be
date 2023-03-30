package com.example.finalassignment.service;

import com.example.finalassignment.dto.ProductDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Product;
import com.example.finalassignment.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(ProductDto productDto) {
        Product newProduct = new Product();

        newProduct.setTitle(productDto.getTitle());

        return newProduct.getId();

    }

    public ProductDto getProduct(Long id) {
        ProductDto dto;
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            dto = fromProduct(product.get());
        } else {
            throw new RecordNotFoundException("product not found");
        }
        return dto;
    }

    public List<ProductDto> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product p : allProducts) {
            ProductDto newProductDto = fromProduct(p);

            productDtoList.add(newProductDto);
        }
        return productDtoList;

    }

    public void deleteProduct (@RequestBody Long id) {
        productRepository.deleteById(id);
    }

    public static ProductDto fromProduct(Product product){
        var dto = new ProductDto();

        dto.id = product.getId();;
        dto.title = product.getTitle();


        return dto;
    }

    public static Product toProduct(ProductDto productDto){
        var product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());


        return product;
    }
}
