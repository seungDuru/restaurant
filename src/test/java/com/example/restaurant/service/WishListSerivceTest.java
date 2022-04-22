package com.example.restaurant.service;

import com.example.restaurant.config.domain.dto.WishListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WishListSerivceTest {

    @Autowired
    private WishListSerivce wishListSerivce;

    @Test
    public void searchTest(){
        WishListDto result = wishListSerivce.search("갈비집");

        System.out.println(result);

        Assertions.assertNotNull(result);
    }
}