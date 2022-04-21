package com.example.restaurant.service;

import com.example.restaurant.config.domain.WishList.WishList;
import com.example.restaurant.config.domain.WishList.WishListRepository;
import com.example.restaurant.config.domain.dto.WishListDto;
import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListSerivce {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){

        //지역검색
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            SearchLocalRes.SearchLocalItem localItem = searchLocalRes.getItems().stream().findFirst().get();

            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                SearchImageRes.SearchImageItem imageItem = searchImageRes.getItems().stream().findFirst().get();

                //결과리턴
                WishListDto wishListDto = new WishListDto();
                wishListDto.setTitle(localItem.getTitle());
                wishListDto.setCategory(localItem.getCategory());
                wishListDto.setAddress(localItem.getAddress());
                wishListDto.setRoadAddress(localItem.getRoadAddress());
                wishListDto.setHomePageLink(localItem.getLink());
                wishListDto.setImageLink(imageItem.getLink());

                return wishListDto;
            }
        }

        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) {
        WishList wishList = new WishList(wishListDto);
        WishListDto result = new WishListDto(wishListRepository.save(wishList));
        return result;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(c -> new WishListDto(c))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        var wishItem = wishListRepository.findById(id);
        if(wishItem.isPresent()){
            WishList item = wishItem.get();
            item = WishList.builder()
                    .isVisit(true)
                    .visitCount(item.getVisitCount()+1)
                    .build();
        }
    }
}
