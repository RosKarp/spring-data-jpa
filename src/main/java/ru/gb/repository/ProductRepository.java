package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    //Page<Product> findAll(Specification<Product> spec, Pageable p);
    //void deleteById(Integer id);
   //Product save(Product product);
    //Optional<Product> findById(Integer id);
}
