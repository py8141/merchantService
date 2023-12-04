package com.shopkart.merchantService.service.impl;

import com.shopkart.merchantService.entity.Merchant;
import com.shopkart.merchantService.entity.MerchantProductOffering;
import com.shopkart.merchantService.repository.MerchantRepository;
import com.shopkart.merchantService.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> getMerchantById(String merchantId) {
        if(merchantId.length()==0){
            return null;
        }
        return merchantRepository.findById(merchantId);
    }

    @Override
    public Boolean saveMerchant(Merchant merchant) {
        Merchant savedMerchant = merchantRepository.save(merchant);
        if(savedMerchant == null){
            return false;
        }
        return true;
    }

    @Override
    public Float addMerchantRating(String merchantId, Float rating) {
        Merchant foundMerchant = merchantRepository.findById(merchantId).get();
        foundMerchant.setMerchantRating(rating);
        merchantRepository.save(foundMerchant);
        return rating;
    }

    @Override
    public Float getMerchantRating(String merchantId) {
        Merchant foundMerchant =  merchantRepository.findById(merchantId).get();
        return foundMerchant.getMerchantRating();
    }

    @Override
    public Boolean updateProductOffering(String merchantId, List<MerchantProductOffering> listProductOffering) {
        Merchant merchant = merchantRepository.findById(merchantId).get();
        List<MerchantProductOffering> existingList = merchant.getListOfProductsByMerchants();
        if(existingList.size() == 0){
            merchant.setListOfProductsByMerchants(listProductOffering);
            merchantRepository.save(merchant);
        }else{
            List<MerchantProductOffering> concatList = new ArrayList<>();
            concatList.addAll(existingList);
            concatList.addAll(listProductOffering);
            merchant.setListOfProductsByMerchants(concatList);
            merchantRepository.save(merchant);
        }


        return true;
    }

    @Override
    public Boolean updateMerchantSold(String merchantId, Long stock , String what) {
        Merchant merchant = merchantRepository.findById(merchantId).get();
        Long soldAlready = merchant.getProductsSold();
//        System.out.println(stock);

        if(what.equals("sold")){
            soldAlready += stock;
        }else if (what.equals("cancelled")){
            soldAlready-= stock;
        }else {
            System.out.println("nothing");
        }

//        System.out.println(soldAlready);
        merchant.setProductsSold(soldAlready);
        merchantRepository.save(merchant);
        return true;
    }
}
