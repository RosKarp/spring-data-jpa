package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.dto.ProductDto;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")     // резерв для смены версий
public class ProductController {

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")         // для ДЗ 9
    public Page<ProductDto> getProducts(
            @RequestParam(name = "max", required = false) Integer maxPrice,
            @RequestParam(name = "min", required = false) Integer minPrice,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, page).map(product -> new ProductDto(product));
    }

    @DeleteMapping("/{id}")     // для ДЗ 9
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/{id}")        // запрос Json продукта в адресной строке (устарело, теперь передаем Dto)
    public Product getProductById(@PathVariable Integer id) {
        return productService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")        //добавление продукта через Dto
    public ProductDto saveNewProductDto(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);
        product.setTitle(productDto.getTitle());
        product.setprice(productDto.getprice());
        return new ProductDto(productService.save(product));
    }
    @PutMapping("")     //обновление продукта через Dto
    public ProductDto updateProductDto(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setprice(productDto.getprice());
        return new ProductDto(productService.save(product));
    }
}
            // ниже к ДЗ не относится, оставлено на память )
    /*
    @GetMapping("/allJSON")
    @ResponseBody
    public List<Product> allProductsJSON() {
        return  productService.getAll();
    }
    @GetMapping("/new")                  // вывод формы для добавления
    public String showFormPage() {
        return "addProductForm";
    }
    @GetMapping("/priceRange")
    @ResponseBody
    public List<Product> allProductsPriceRange(@RequestParam(defaultValue = "0") Integer min, @RequestParam (defaultValue = "2000000") Integer max) {
        return productService.getAllPriceRange(min, max);
    }
    @GetMapping("/minPrice/{price}")
    public String allProductsOverPrice(Model model, @PathVariable Integer price) {
        model.addAttribute("allProducts", productService.getAllOverPrice(price));
        return "Products_page";
    }
    @GetMapping("/maxPrice/{price}")
    public String allProductsBelowPrice(Model model, @PathVariable Integer price ) {
        model.addAttribute("allProducts", productService.getAllBelowPrice(price));
        return "Products_page";
    }
    @GetMapping("/priceRange/{min}/{max}")
    public String allProductsPriceRange(Model model, @PathVariable Integer min, @PathVariable Integer max) {
        model.addAttribute("allProducts", productService.getAllPriceRange(min, max));
        return "Products_page";
    }*/
