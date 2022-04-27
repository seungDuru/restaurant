package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){

        SearchLocalReq search = new SearchLocalReq();
        search.setQuery("갈비집");

        SearchLocalRes result = naverClient.searchLocal(search);
        System.out.println(result);
    }

    @Test
    public void searchImageTest(){
        SearchImageReq search = new SearchImageReq();
        search.setQuery("갈비집");

        SearchImageRes result = naverClient.searchImage(search);
        System.out.println(result);
    }

}