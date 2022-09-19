package com.practice.springsecondphrasepractice.controller;


import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.AddNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.PutNERequest;
import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.PutNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.Nfaentity.Nfa;
import com.practice.springsecondphrasepractice.service.NfaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api/v1/nfa")
@Validated
public class NfaController {

    @Autowired
    NfaService nfaService;

    @GetMapping()
    public List<Nfa> getAllNfa(
            @RequestParam(required = false) @Pattern(regexp = "^[?=\\u4E00-\\u9FA5]", message = "格式錯誤") String subject,
            @RequestParam(required = false) @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤") String startDate,
            @RequestParam(required = false) @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤") String endDate
    ) throws DataNotFoundException {
        if (null != subject || null != startDate || null != endDate) {
            return this.nfaService.getByCondition(subject, startDate, endDate);
        }
        return this.nfaService.getAll();
    }

    @PostMapping()
    public StatusResponse addNfa(@RequestBody @Valid AddNfaRequest request) {
        return this.nfaService.addNfa(request);
    }

    @PutMapping("/{nfaUuid}")
    public StatusResponse updateNfa(
            @PathVariable @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤") String nfaUuid,
            @RequestBody @Valid PutNfaRequest request) {
        return this.nfaService.updateNfa(nfaUuid, request);
    }

    @PostMapping("/{nfaUuid}")
    public StatusResponse cancelNfa(
            @PathVariable @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤") String nfaUuid,
            @RequestBody @Valid PutNERequest request) {
        return this.nfaService.cancelNfa(nfaUuid, request);
    }
}
