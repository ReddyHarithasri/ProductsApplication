package Springboot.Api.Service;

import Springboot.Api.Model.Products;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ProductService {
    public boolean addProduct(Products p);
    public List<Products> showAll();
    public boolean deleteProducts();
    public boolean updateProducts(Products p);
    public Products getProductById(int pid);

    public  List<Products> getProductByCat(String category);
    public boolean deleteProductById(int pid);
    public List<Products> findProductwithSorting(String field);

    public Page<Products> findProductsWithPagination(int offset, int pageSize);
    public Page<Products> findProductsWithPaginationandSorting(int offset,int pageSize,String field);
    public List<Products> getProductByDate(Date dor);
}
