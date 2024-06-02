package Springboot.Api.Repository;

import Springboot.Api.Model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Products,Integer> {


    List<Products> findByCategory(String category);
@Query("{'dor':?0}")
    List<Products> findByDor(Date dor);
}
