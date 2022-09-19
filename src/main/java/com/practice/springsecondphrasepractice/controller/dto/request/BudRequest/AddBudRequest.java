package com.practice.springsecondphrasepractice.controller.dto.request.BudRequest;


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
public class AddBudRequest {

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤")
    private String budYmd;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[?=Y|N]", message = " 格式錯誤")
    private String budType;
}
