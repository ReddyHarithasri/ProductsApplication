package Springboot.Api.controller;

import Springboot.Api.Controller.MainController;
import Springboot.Api.Model.Products;
import Springboot.Api.Service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productService;
    Products p1;
    Products p2;
    List<Products> productsList=new ArrayList<>();
    @BeforeEach
    void setup(){
        p1=new Products(1,"iqoo",25000,"mobile");
        p2=new Products(2,"samsung",25000,"mobile");
        productsList.add(p1);
        productsList.add(p2);

    }
    @AfterEach
    void tearDown(){
    }
    @Test
    void testGetAllProducts() throws Exception {
        when(productService.showAll()).thenReturn(productsList);
        this.mockMvc.perform(get("/products"))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testDeleteProductById() throws Exception {
        when(productService.deleteProductById(1))
                .thenReturn(true);
        this.mockMvc.perform(delete("/deletebyid/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getEmpByIdTest() throws Exception {
        when(productService.getProductById(1))
                .thenReturn(p1);
        this.mockMvc.perform(get("/getbyid/1"))
                .andDo(print()).andExpect(status().isOk());
    }

}
