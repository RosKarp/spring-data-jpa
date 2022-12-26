package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll() {return productRepository.findAll();}

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllOverPrice(Integer price) { return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(price) > 0)).toList();}

    public List<Product> getAllBelowPrice(Integer price) {return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(price) < 0)).toList();}

    public List<Product> getAllPriceRange(Integer min, Integer max) {return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(min) > 0)&&(product.getprice().compareTo(max) < 0)).toList();}
}
