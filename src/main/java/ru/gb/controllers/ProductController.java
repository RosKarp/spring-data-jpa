package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.converters.ProductConverter;
import ru.gb.dto.ProductDto;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.model.Product;
import ru.gb.service.ProductService;
import ru.gb.validators.ProductValidator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")     // резерв для смены версий
@RequiredArgsConstructor
public class ProductController {
    private final ProductConverter productConverter;
    private final ProductService productService;
    private final ProductValidator productValidator;
    /*
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }*/

    @GetMapping("")         // для ДЗ 9
    public Page<ProductDto> getProducts(
            @RequestParam(name = "max", required = false) Integer maxPrice,
            @RequestParam(name = "min", required = false) Integer minPrice,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, page).map(product -> productConverter.entityToDto(product));
    }
    @DeleteMapping("/{id}")     // для ДЗ 9
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
    @GetMapping("/{id}")        // запрос Json продукта в адресной строке (устарело, теперь передаем Dto)
    public ProductDto getProductById(@PathVariable Integer id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден в базе, id: " + id));
        return productConverter.entityToDto(product);
    }
    @PostMapping("")        //добавление продукта через Dto
    public ProductDto saveNewProductDto(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }
    @PutMapping("")     //обновление продукта через Dto
    public ProductDto updateProductDto(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }
    // добавлено для ДЗ 10

    @GetMapping("/to_cart/{Id}/{Title}/{Price}")
    public void addProductDtoToCart(@PathVariable Integer Id, @PathVariable String Title, @PathVariable Integer Price) {
        ProductDto productDto = new ProductDto(Id, Title, Price);
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        productService.addToCart(product);
    }
    @DeleteMapping("/delete_from_cart/{id}")
    public void deleteProductFromCart(@PathVariable Integer id) {
        productService.deleteProductByIdFromCart(id);
    }
    @GetMapping("/load_cart")
    public List<ProductDto> getAllCart() {
        return productService.getCart().stream().map(productConverter::entityToDto).toList();
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
