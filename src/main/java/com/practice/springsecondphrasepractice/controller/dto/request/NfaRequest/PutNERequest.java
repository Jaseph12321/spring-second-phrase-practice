package com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutNERequest {

    @NotEmpty
    @Pattern(regexp = "^[?=Y|N]", message = " 格式錯誤")
    private String enable;
}
