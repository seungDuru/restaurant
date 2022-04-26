package com.example.restaurant.domain.WishList;

import com.example.restaurant.config.domain.WishList.WishList;
import com.example.restaurant.config.domain.WishList.WishListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishList create(){
        WishList wishList = WishList.builder()
                .title("title")
                .category("category")
                .address("address")
                .roadAddress("roadAddress")
                .homePageLink("")
                .isVisit(false)
                .visitCount(0)
                .lastVisitDate(null)
                .build();

        return wishList;
    }

    @Test
    public void saveTest(){
        WishList wishList = create();
        WishList expected = wishListRepository.save(wishList);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getId());
    }

    @Test
    public void updateTest(){
        WishList wishList = create();
        wishListRepository.save(wishList);

        WishList expected = wishListRepository.findById(wishList.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다"));

        WishList update = WishList.builder()
                        .title("changeTitle")
                        .category("changeCategory")
                        .build();

        expected.update(update);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, wishList.getId());
        Assertions.assertEquals(1, wishListRepository.findAll().size());
        Assertions.assertEquals("title", wishList.getTitle());
        Assertions.assertEquals("category", wishList.getCategory());
        Assertions.assertEquals(1, expected.getId());
        Assertions.assertEquals(1, wishListRepository.findAll().size());
        Assertions.assertEquals("changeTitle", expected.getTitle());
        Assertions.assertEquals("changeCategory", expected.getCategory());
    }

    @Test
    public void findByIdTest(){
        WishList wishList = create();
        wishListRepository.save(wishList);

        List<WishList> wishListList = wishListRepository.findAll();
        WishList expected = wishListList.get(0);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getId());
        Assertions.assertEquals("title", expected.getTitle());
    }

    @Test
    public void deleteTest(){
        WishList wishList = create();
        wishListRepository.save(wishList);

        wishListRepository.deleteById(wishList.getId());

        int count = wishListRepository.findAll().size();

        Assertions.assertEquals(0, count);

    }

    @Test
    public void listAllTest(){
        WishList wishList1 = create();
        wishListRepository.save(wishList1);

        WishList wishList2 = create();
        wishListRepository.save(wishList2);

        int count = wishListRepository.findAll().size();
        Assertions.assertEquals(2, count);
    }
}