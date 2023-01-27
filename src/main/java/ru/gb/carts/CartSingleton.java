package ru.gb.carts;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.model.Product;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class CartSingleton {

    private List<Product> productsInCart = new ArrayList<>();
}
//