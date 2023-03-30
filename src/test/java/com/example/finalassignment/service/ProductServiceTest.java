package com.example.finalassignment.service;

import com.example.finalassignment.dto.ProductDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Product;
import com.example.finalassignment.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product product1;
    Product product2;

    ProductDto productDto1;


    @BeforeEach
    void setUp() {
        product1 = new Product(11L, "productTest1", null);
        product2 = new Product(22L, "productTest2", null);
        productDto1 = new ProductDto(11L, "productTest1", null);
    }

    @Test
    void createProduct() {
        when(productRepository.save(product1)).thenReturn(product1);

        productService.createProduct(productDto1);

        assertEquals(product1.getId(), productDto1.getId());
        assertEquals(product1.getTitle(), productDto1.getTitle());

    }

    @Test
    void getProduct() {
        Long id = 11L;
        when(productRepository.findById(id)).thenReturn(Optional.of(product1));
        Product product = productRepository.findById(id).get();
        ProductDto dto = productService.getProduct(id);

        assertEquals(product.getTitle(), dto.getTitle());
        assertEquals(product.getAppointment(), dto.getAppointment());
    }

    @Test
    void getProductThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> productService.getProduct(null));
    }

    @Test
    void getProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos = productService.getProducts();

        assertEquals(products.get(0).getId(), dtos.get(0).getId());
        assertEquals(products.get(0).getTitle(), dtos.get(0).getTitle());
        assertEquals(products.get(0).getAppointment(), dtos.get(0).getAppointment());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(11L);

        verify(productRepository).deleteById(11L);

    }

}
