package com.shopkart.merchantService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "merchants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {

    @Id
    private String merchantId= UUID.randomUUID().toString();
    private Long internalScore = new Long(0);
    private Float merchantRating ;
    private List<MerchantProductOffering> listOfProductsByMerchants = new ArrayList<>();
    private Long productsSold;

}

