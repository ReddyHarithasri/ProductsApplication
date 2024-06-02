package Springboot.Api.repository;

import Springboot.Api.Model.Products;
import Springboot.Api.Repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    Products p;
    @BeforeEach
    void setup(){
       p=new Products(1,"iqoo",25000,"mobile") ;
       productRepository.save(p);
    }
    @AfterEach
    void tearDown(){
        p=null;
        productRepository.deleteAll();
    }
    @Test
    void testFindByCategory(){
      List<Products> productsList= productRepository.findByCategory("mobile");
        assertNotNull(productsList);
    }

}
