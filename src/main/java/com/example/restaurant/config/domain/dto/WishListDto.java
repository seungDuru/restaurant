package com.example.restaurant.config.domain.dto;

import com.example.restaurant.config.domain.WishList.WishList;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private Long id;
    private String title;
    private String category;
    private String address;
    private String roadAddress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;

    public WishListDto(WishList wishList) {
        this.id = wishList.getId();
        this.title = wishList.getTitle();
        this.category = wishList.getCategory();
        this.address = wishList.getAddress();
        this.roadAddress = wishList.getRoadAddress();
        this.homePageLink = wishList.getHomePageLink();
        this.imageLink = wishList.getImageLink();
        this.isVisit = wishList.isVisit();
        this.visitCount = wishList.getVisitCount();
        this.lastVisitDate = wishList.getLastVisitDate();
    }

}
