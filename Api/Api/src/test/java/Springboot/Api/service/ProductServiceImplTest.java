package Springboot.Api.service;

import Springboot.Api.Model.Products;
import Springboot.Api.Repository.ProductRepository;
import Springboot.Api.Service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceImplTest {
    @MockBean
    ProductRepository productRepository;
    @Autowired
    ProductServiceImpl productService;
    @Test
    public void test_addProduct(){
        Products p=new Products(1001,"sony",90000,"tv");
        when(productRepository.save(p)).thenReturn(p);
        Assertions.assertEquals(true,productService.addProduct(p));

    }
    @Test
    public void test_getProductById(){
        Products p=new Products(1001,"sony",90000,"tv");
        when(productRepository.findById(1001)).thenReturn(Optional.of(p));
      Assertions.assertEquals( p,productService.getProductById(1001));
    }
    @Test
    public void test_getAllProducts(){
        List<Products> p= Arrays.asList(new Products(1001,"lg",50000,"tv"),new Products(1002,"samsung",90000,"tv"));
        when(productRepository.findAll()).thenReturn(p);
        Assertions.assertEquals(2,productService. showAll().size());

    }
    @Test
    public void test_deleteProducts(){
        Products product=new Products(1001,"sony",90000,"tv");
        productService.deleteProducts();
        verify(productRepository,times(1)).deleteAll();


    }
}
