package com.practice.springsecondphrasepractice.service;


import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.AddProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.PutPeRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.PutProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.Prodentity.Prod;
import com.practice.springsecondphrasepractice.model.Prodentity.ProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdService {

    @Autowired
    ProdRepository repo;

    //1.
    public List<ProdResponse> getAll() throws DataNotFoundException {
        List<ProdResponse> list = setListResponse(this.repo.findAll());
        if(list.size() == 0) throw new DataNotFoundException("資料不存在");
        return list;
    }

    //2.
    public List<ProdResponse> getByKind(String kind) throws DataNotFoundException {
        List<ProdResponse> list = setListResponse(this.repo.findByProdKind(kind));
        if(list.size() == 0) throw new DataNotFoundException("資料不存在");

        return list;

    }

    //3.
    public List<ProdResponse> getByCcy(String ccy) throws DataNotFoundException {
        List<ProdResponse> list = setListResponse(this.repo.findByProdCcy(ccy));
        if(list.size() == 0) throw new DataNotFoundException("資料不存在");
        return list;
    }

    //4.
    public ProdResponse getOneProd(String prodId) {
        ProdResponse p = setResponse(this.repo.findByProdId(prodId));
        return p;
    }

    //5.

    public StatusResponse addProd(AddProdRequest request){
        Prod newProd = new Prod();
        newProd.setProdId(request.getProdKind()+"_"+request.getProdCcy());
        newProd.setProdKind(request.getProdKind());
        newProd.setProdName(request.getProdName());
        newProd.setProdEname(request.getProdEname());
        newProd.setProdCcy(request.getProdCcy());
        newProd.setProdEnable(request.getProdEnale());
        newProd.setProdITime(LocalDateTime.now());
        newProd.setProdUTime(LocalDateTime.now());
        repo.save(newProd);
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateProd(String prodId,PutProdRequest request){
        this.repo.updateProd(request.getProdName(), request.getProdEname(), request.getProdEnable(),prodId);
        return new StatusResponse("異動成功");
    }

    public StatusResponse cancelProd(String prodId, PutPeRequest request){
        this.repo.updateProdEnable(request.getProdEnable(), prodId);
        return new StatusResponse("註銷成功");
    }

    //============================================
    // below are methods

    public List<ProdResponse> setListResponse(List<Prod> list){
        List<ProdResponse> results = new ArrayList<>();
        for(Prod p:list)
        results.forEach(s->setResponse(p));
        return results;
    }
    public ProdResponse setResponse(Prod prod){
        ProdResponse response = new ProdResponse();
        response.setProdId(prod.getProdId());
        response.setProdKind(prod.getProdKind());
        response.setProdName(prod.getProdName());
        response.setProdCcy(prod.getProdCcy());
        response.setProdEnable(prod.getProdEnable());
        return response;
    }


}
