package com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutProdRequest {

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[?=\\u4E00-\\u9FA5]", message = "格式錯誤")
    private String prodName;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[?=A-Za-z]", message = " 格式錯誤")
    private String prodEname;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[?=Y|N]", message = " 格式錯誤")
    private String prodEnable;
}
