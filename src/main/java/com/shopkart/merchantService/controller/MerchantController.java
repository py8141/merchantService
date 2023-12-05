package com.shopkart.merchantService.controller;


import com.shopkart.merchantService.dto.MerchantDto;
import com.shopkart.merchantService.entity.Merchant;
import com.shopkart.merchantService.entity.MerchantProductOffering;
import com.shopkart.merchantService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDto>> getAllMerchants(){
        try {
            List<Merchant> merchants = merchantService.getAllMerchants();
            List<MerchantDto> merchantDtos = new ArrayList<>();

            for (Merchant merchant : merchants) {
                MerchantDto merchantDto = new MerchantDto();
                BeanUtils.copyProperties(merchant, merchantDto);
                merchantDtos.add(merchantDto);
            }

            return new ResponseEntity<>(merchantDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<MerchantDto> getMerchantById(@PathVariable String merchantId){
        try {
            Merchant merchant = merchantService.getMerchantById(merchantId).get();

            MerchantDto merchantDto = new MerchantDto();
            BeanUtils.copyProperties(merchant, merchantDto);

            return ResponseEntity.ok(merchantDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/rating/{merchantId}")
    public ResponseEntity<Float> getMerchantRating (@PathVariable String merchantId){
        try {
            Float rating = merchantService.getMerchantRating(merchantId);
            if (rating != null) {
                return ResponseEntity.ok(rating);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace(); // This is just an example. You should log the exception properly.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @PostMapping
//    public ResponseEntity<Boolean> addMerchant(MerchantDto merchantDto){
//        Merchant merchant = new Merchant();
//        BeanUtils.copyProperties(merchantDto,merchant);
//        merchantService.saveMerchant(merchant);
//
//        if(merchant == null){
//            ResponseEntity.ok(Boolean.FALSE);
//        }
//        return ResponseEntity.ok(Boolean.TRUE);
//    }
//
//    @PostMapping
//    public ResponseEntity<Float> addMerchantRating(String merchantId , Float rating){
//     return ResponseEntity.ok(merchantService.addMerchantRating(merchantId,rating));
//    }

    @PostMapping
    public ResponseEntity<Boolean> addMerchant(@RequestBody MerchantDto merchantDto) {
        try {
            Merchant merchant = new Merchant();
            BeanUtils.copyProperties(merchantDto, merchant);
            merchantService.saveMerchant(merchant);

            if (merchant == null) {
                return ResponseEntity.ok(Boolean.FALSE);
            }
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE);
        }
    }

    @PostMapping("/rating/{merchantId}")
    public ResponseEntity<Float> addMerchantRating(@PathVariable String merchantId, Float rating) {
        try {
            return ResponseEntity.ok(merchantService.addMerchantRating(merchantId, rating));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{merchantId}/update-offerings")
    public ResponseEntity<Boolean> updateOffering(@PathVariable String merchantId, @RequestBody List<MerchantProductOffering> updateOfferingList){
        return ResponseEntity.ok(merchantService.updateProductOffering(merchantId,updateOfferingList));
    }

    @PutMapping("/{merchantId}/update-sold/{stock}/{what}")
    public ResponseEntity<Boolean> updateProductsSold (@PathVariable String merchantId, @PathVariable Long stock , @PathVariable String what){
        merchantService.updateMerchantSold(merchantId,stock,what);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
