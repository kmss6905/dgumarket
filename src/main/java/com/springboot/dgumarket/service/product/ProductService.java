package com.springboot.dgumarket.service.product;

import com.springboot.dgumarket.dto.product.ProductCreateDto;
import com.springboot.dgumarket.payload.response.ProductListIndex;
import com.springboot.dgumarket.service.UserDetailsImpl;

import java.util.List;

/**
 * Created by TK YOUN (2020-12-22 오후 10:08)
 * Github : https://github.com/dgumarket/dgumarket.git
 * Description :
 */
public interface ProductService {

    // 상품 등록
    String enrollProduct(ProductCreateDto productCreateDto);

    // 비로그인 상태 -> /api/product/index 요청, lastCategoryId -> 비로그인 상황에서 무한스크롤 감지 했을 때 인기카테고리가 계속 반환되는 문제 해결
    List<ProductListIndex>  indexNotLoggedIn(int lastCategoryId);

    // 로그인 상태 -> /api/product/index 요청, userDetails : '유저'의 관심 카테고리를 출력하기 위함, lastCategoryId -> 페이징 시 가장 마지막으로 응답했던 카테고리 id
    List<ProductListIndex> indexLoggedIn(UserDetailsImpl userDetails, int lastCategoryId);

}