package com.practice.springsecondphrasepractice.controller;


import com.practice.springsecondphrasepractice.controller.dto.request.CryptRequest.CodeRequest;
import com.practice.springsecondphrasepractice.service.CryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/encode/aes")
public class EncodeController {

    @Autowired
    CryptService cryptService;

    @PostMapping("/ecb/{key}")
    public String getEcbEncrypt(@RequestBody CodeRequest request,@PathVariable String key) throws Exception {
        return cryptService.aesEbsEncrypt(request.getRequest(),key);
    }

    @PostMapping("/cbc/{key}")
    public String getCbcEncrypt(@RequestBody CodeRequest request,@PathVariable String key) throws Exception {
        return cryptService.aesCbcEncrypt(request.getRequest(),key);
    }

}
