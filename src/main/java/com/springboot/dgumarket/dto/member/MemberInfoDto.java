package com.springboot.dgumarket.dto.member;

import com.springboot.dgumarket.dto.product.ProductCategoryDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TK YOUN (2021-01-02 오후 6:28)
 * Github : https://github.com/dgumarket/dgumarket.git
 * Description :
 */
@Getter
@Setter
public class MemberInfoDto {
    private String profileImageDir;

    @NotBlank
    private String nickName;

    @NotBlank
    private Set<ProductCategoryDto> productCategories = new HashSet<>();
}