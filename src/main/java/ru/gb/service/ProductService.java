package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.carts.CartSingleton;
import ru.gb.dto.ProductDto;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;
import ru.gb.repository.specifications.ProductSpecifications;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CartSingleton cartSingleton;

    /*
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }*/

    public Page<Product> find(Integer minPrice, Integer maxPrice, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }
            // к ДЗ 10

    public void addToCart(Product product) {
        cartSingleton.getProductsInCart().add(product);
    }

    public void deleteProductByIdFromCart(Integer id) {
        Product p = cartSingleton.getProductsInCart().stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить продукт, не найден в корзине id: " + id));
        cartSingleton.getProductsInCart().remove(p);
    }

    public List<Product> getCart() {
        return cartSingleton.getProductsInCart();
    }
}

    // ниже к ДЗ не относится, оставлено на память )
    /*
    public List<Product> getAll() {return productRepository.findAll();}

    public List<Product> getAllOverPrice(Integer price) { return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(price) > 0)).toList();}

    public List<Product> getAllBelowPrice(Integer price) {return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(price) < 0)).toList();}

    public List<Product> getAllPriceRange(Integer min, Integer max) {return productRepository.findAll().stream()
            .filter(product -> (product.getprice().compareTo(min) > 0)&&(product.getprice().compareTo(max) < 0)).toList();}
    */


