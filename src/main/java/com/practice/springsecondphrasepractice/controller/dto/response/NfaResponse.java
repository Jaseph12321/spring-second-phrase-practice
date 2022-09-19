package com.practice.springsecondphrasepractice.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NfaResponse {
    private String nfaUuid;
    private String nfaSubject;
    private String nfaContent;
    private String nfaEnable;
    private String nfaUTime;
}
