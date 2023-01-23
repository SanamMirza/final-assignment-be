package com.example.finalassignment.controller;

import com.example.finalassignment.dto.ProductDto;
import com.example.finalassignment.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.finalassignment.utils.Utils.getErrorString;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService Productservice;

    public ProductController(ProductService productservice) {
        Productservice = productservice;
    }

    @PostMapping("")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = Productservice.createProduct(productDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/products/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("Product created");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(Productservice.getProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        Productservice.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
