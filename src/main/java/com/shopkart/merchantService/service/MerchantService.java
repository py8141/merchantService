package com.shopkart.merchantService.service;

import com.shopkart.merchantService.entity.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    List<Merchant> getAllMerchants();
    Optional<Merchant> getMerchantById(String merchantId);
    Boolean saveMerchant(Merchant merchant);
    Float addMerchantRating(String merchantId ,Float rating);
    Float getMerchantRating (String merchantId);



}