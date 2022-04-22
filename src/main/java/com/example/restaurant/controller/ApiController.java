package com.example.restaurant.controller;

import com.example.restaurant.config.domain.dto.WishListDto;
import com.example.restaurant.service.WishListSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private final WishListSerivce wishListSerivce;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListSerivce.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);
        return wishListSerivce.add(wishListDto);
    }

    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListSerivce.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        wishListSerivce.delete(id);
    }
}
