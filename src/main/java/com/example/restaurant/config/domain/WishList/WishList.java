package com.example.restaurant.config.domain.WishList;

import com.example.restaurant.config.domain.dto.WishListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private String address;

    @Column
    private String roadAddress;

    @Column
    private String homePageLink;

    @Column
    private String imageLink;

    @Column
    private boolean isVisit;

    @Column
    private int visitCount;

    @Column
    private LocalDateTime lastVisitDate;

    @Builder
    public WishList(Long id, String title, String category, String address, String roadAddress, String homePageLink, String imageLink, boolean isVisit, int visitCount, LocalDateTime lastVisitDate) {
        this.title = title;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
        this.homePageLink = homePageLink;
        this.imageLink = imageLink;
        this.isVisit = isVisit;
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
    }

    public WishList(WishListDto wishListDto) {
        this.title = wishListDto.getTitle();
        this.category = wishListDto.getCategory();
        this.address = wishListDto.getAddress();
        this.roadAddress = wishListDto.getRoadAddress();
        this.homePageLink = wishListDto.getHomePageLink();
        this.imageLink = wishListDto.getImageLink();
        this.isVisit = wishListDto.isVisit();
        this.visitCount = wishListDto.getVisitCount();
        this.lastVisitDate = wishListDto.getLastVisitDate();
    }

    public void update(WishList wishList){
        this.title = wishList.getTitle();
        this.category = wishList.getCategory();
        this.address = wishList.getAddress();
        this.roadAddress = wishList.getRoadAddress();
        this.homePageLink = wishList.getHomePageLink();
        this.imageLink = wishList.getImageLink();
        this.visitCount = wishList.getVisitCount();
        this.lastVisitDate = wishList.getLastVisitDate();
    }
}
