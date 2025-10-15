package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.model.Product;
import lombok.Data;

@Data
public class OrderLineRequest {

    private Integer quantity;
    private Product productName;




}
