package ru.gb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {       // обертка над Product для обмена с фронтом
    private Integer id;
    private String title;
    private Integer price;
}
