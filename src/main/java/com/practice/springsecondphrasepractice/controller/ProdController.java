package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.AddProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.PutPeRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.PutProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.Prodentity.Prod;
import com.practice.springsecondphrasepractice.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;


@RestController
@RequestMapping("/api/v1/prod")
public class ProdController {

    @Autowired
    ProdService prodService;

    @GetMapping()
    public List<ProdResponse> getAllProd(
            @RequestParam(required = false) @Pattern(regexp = "^[?=A-Z]{3}", message = " 格式錯誤") String kind,
            @RequestParam(required = false) @Pattern(regexp = "^[?=A-Z]{3}", message = " 格式錯誤") String ccy) throws DataNotFoundException {
        if (null != kind) {
            return this.prodService.getByKind(kind);
        } else if (null != ccy) {
            return this.prodService.getByCcy(ccy);
        }
        return this.prodService.getAll();
    }

    @GetMapping("/{prodId}")
    public ProdResponse getProdById(
            @PathVariable @Pattern(regexp="^[?=A-Z]{3}^[?=_]{1}^[?=A-Z]{3}", message = "格式錯誤") String prodId) {
        return this.prodService.getOneProd(prodId);
    }

    @PostMapping()
    public StatusResponse addProd(@RequestBody @Valid AddProdRequest request) {
        return this.prodService.addProd(request);
    }

    @PutMapping("/{prodId}")
    public StatusResponse updateProd(@PathVariable String prodId, @RequestBody @Valid PutProdRequest request) {
        return this.prodService.updateProd(prodId, request);
    }

    @PostMapping("/{prodId}")
    public StatusResponse cancelProd(@PathVariable String prodId, @RequestBody @Valid PutPeRequest request) {
        return this.prodService.cancelProd(prodId, request);
    }
}
