package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

@Controller
@RequestMapping("/app/products")
public class  RestController {

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Integer id) {
        return productService.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @GetMapping("")
    public String allProducts(Model model) {
        model.addAttribute("allProducts", productService.getAll());
        return "Products_page";
    }
    @PostMapping("")
    public String addProduct(@RequestParam String title, @RequestParam Integer price) {
        Product product = new Product(title, price);
        productService.save(product);
        return "redirect:/app/products";
    }
    @GetMapping("/new")                  // вывод формы для добавления
    public String showFormPage() {
        return "addProductForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/app/products";
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
    }
}