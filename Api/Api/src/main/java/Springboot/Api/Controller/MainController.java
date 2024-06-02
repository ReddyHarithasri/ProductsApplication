package Springboot.Api.Controller;


import Springboot.Api.Exception.ResourceNotFoundException;
import Springboot.Api.Exception.ResponseHandler;
import Springboot.Api.Model.Products;
import Springboot.Api.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController

public class MainController {

    Logger logger= LoggerFactory.getLogger(MainController.class);
    @Autowired
    public ProductService service;
    @PostMapping("/products")
    public ResponseEntity<Object> addProduct(@RequestBody Products p)  throws ResourceNotFoundException{
        logger.debug("creating product...");
        if(service.addProduct(p))
        {

           return ResponseHandler.responseBuilder("Added successfully...",HttpStatus.OK);
        }
        throw new ResourceNotFoundException("NO DATA EXIST");
    }

    @GetMapping("/products")
    public List<Products> showAll(){
        logger.trace("Starting get all products with trace log level");
        logger.info("Starting get all products with info log level");
        return service.showAll();
    }
@GetMapping("/products/{field}")
public List<Products> findProductwithSorting(@PathVariable String field){
    return service. findProductwithSorting(field);
}
@GetMapping("/pagination/{offset}/{pageSize}")
public Page<Products> findProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize){
    return service. findProductsWithPagination(offset,pageSize);
}
    @GetMapping("/paginationandsorting/{offset}/{pageSize}/{field}")
    public Page<Products>findProductsWithPaginationandSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field){
        return service. findProductsWithPaginationandSorting(offset,pageSize,field);
    }
    @GetMapping("/products/{pid}")
public Products getProductById(@PathVariable int pid){
        logger.trace("reading product with id : {}",pid);

        return service.getProductById(pid);
}
    @GetMapping("/getbydate/{dor}")
    public List<Products> getProductsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dor){
        return service.getProductByDate(dor);
    }

    @GetMapping("/product/{category}")
       public ResponseEntity<Object>  getProductByCat(@PathVariable String category) throws ResourceNotFoundException {
        List<Products> p= service.getProductByCat(category);
        if(!p.isEmpty()){
            return new ResponseEntity<Object>(p, HttpStatus.FOUND);
        }
        logger.warn("no data found with this {}",category);
        throw new ResourceNotFoundException("NO DATA EXIST");
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<Object> deleteProducts(Products p)  throws ResourceNotFoundException{
        if(service.deleteProducts())
            return ResponseHandler.responseBuilder("Deleted successfully...",HttpStatus.OK);
        logger.error("error occurred in deleting");
        throw new ResourceNotFoundException("no products deleted");

    }
    @DeleteMapping("/products/{pid}")
    public ResponseEntity<Object> deleteProductById(@PathVariable int pid) throws ResourceNotFoundException {

        if(service.deleteProductById(pid))
        {
            return ResponseHandler.responseBuilder("Deleted successfully...",HttpStatus.OK);
        }


        throw new ResourceNotFoundException("no products deleted");

    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProducts(@PathVariable int id,@RequestBody Products p)  throws ResourceNotFoundException{
        p.setPid(id);
       if(service.updateProducts(p))
           return ResponseHandler.responseBuilder("Updated Successfully...",HttpStatus.OK);
        throw new ResourceNotFoundException("no data exist to update");
    }

}
