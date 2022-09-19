package com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddNfaRequest {

    @Pattern(regexp = "^[?=\\u4E00-\\u9FA5]", message = "格式錯誤")
    private String subject;

    @Pattern(regexp = "^[?=\\u4E00-\\u9FA5]", message = "格式錯誤")
    private String content;

    @Pattern(regexp = "^[?=Y|N]", message = " 格式錯誤")
    private String enable;

    @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤")
    private String startDate;

    @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤")
    private String endDate;
}
