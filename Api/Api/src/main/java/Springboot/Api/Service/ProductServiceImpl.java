package Springboot.Api.Service;


import Springboot.Api.Model.Products;
import Springboot.Api.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository prorepo;
    @Override
    public boolean addProduct(Products p){
        p.setLocalDateTime(LocalDateTime.now());
        if(prorepo.save(p)!=null)
            return true;
        return false;

    }
    @Override
    public List<Products> showAll(){
        return prorepo.findAll();

    }
    public List<Products> findProductwithSorting(String field){
        return prorepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    public Page<Products> findProductsWithPagination(int offset,int pageSize){

        Page<Products> p= prorepo.findAll(PageRequest.of(offset, pageSize));
        return p;
    }
    public Page<Products> findProductsWithPaginationandSorting(int offset,int pageSize,String field) {

        Page<Products> p = prorepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, field));
        return p;}
    @Transactional
    public boolean deleteProducts () {
        prorepo.deleteAll();

        return false;
    }
    @Transactional
    public boolean deleteProductById ( int pid){
        if (prorepo.existsById(pid)) {
            prorepo.deleteById(pid);
            return true;
        }
        return false;

    }
    @Transactional
    public boolean updateProducts (Products p){
        Products pro = prorepo.findById(p.getPid()).get();
        pro.setPname(p.getPname());
        pro.setPrice(p.getPrice());
        pro.setCategory(p.getCategory());
        Products p1 = prorepo.save(pro);
        if (p1 != null)
            return true;
        return false;
    }
    @Override
    public Products getProductById ( int pid){
        return prorepo.findById(pid).get();
    }
    @Override
    public List<Products> getProductByCat (String category){
        return prorepo.findByCategory(category);
    }
    //        private ResponseEntity<Object> buildSuccess(String message){
//            SuccessResponse response=new SuccessResponse(HttpStatus.OK.value(),LocalDateTime.now(),message);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
    public List<Products> getProductByDate(Date dor){
        System.out.println("searching for product"+dor);
        List <Products> p=prorepo.findByDor(dor);
        return p;
        }
        }


