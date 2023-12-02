package com.shopkart.merchantService.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "merchantProductOffering")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantProductOffering {

    private String productId;
    private Boolean isActive;
    private Long numberSold;
}
