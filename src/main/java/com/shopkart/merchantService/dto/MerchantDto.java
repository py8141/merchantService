package com.shopkart.merchantService.dto;

import com.shopkart.merchantService.entity.MerchantProductOffering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MerchantDto {
    private Long internalScore = new Long(0);
    private Float merchantRating ;
    private List<MerchantProductOffering> listOfProductsByMerchants = new ArrayList<>();
    private Long productsSold;
}
