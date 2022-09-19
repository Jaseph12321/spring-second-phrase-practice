package com.practice.springsecondphrasepractice.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdResponse {
    private String prodId;
    private String prodKind;
    private String prodName;
    private String prodCcy;
    private String prodEnable;
}
