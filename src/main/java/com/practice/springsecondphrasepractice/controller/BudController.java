package com.practice.springsecondphrasepractice.controller;


import com.practice.springsecondphrasepractice.controller.dto.request.BudRequest.AddBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.BudRequest.PutBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.ProdRequest.PutPeRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.Budentity.Bud;
import com.practice.springsecondphrasepractice.service.BudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@RestController
@RequestMapping("/api/v1/bud")
@Validated
public class BudController {

    @Autowired
    BudService budService;

    @GetMapping()
    public List<Bud> getAllDate(
            @RequestParam(required = false)
            @Pattern(regexp = "^[(?=\\d)]{8}", message = "start date 格式錯誤")
            String startDate,
            @RequestParam(required = false)
            @Pattern(regexp = "^[(?=\\d)]{8}", message = "end date 格式錯誤")
            String endDate,
            @RequestParam(required = false)
            @Pattern(regexp = "^[(?=\\d)]{4}", message = "year 格式錯誤")
            String year) throws Exception {
        if (null != year) {
            return this.budService.getBusinessByYear(year);
        } else if (null != startDate || null != endDate) {
            assert startDate != null;
            if (Integer.parseInt(startDate) > Integer.parseInt(endDate)) {
                System.out.println(startDate.length());
                System.out.println(endDate.length());
                List<String> ergs = new ArrayList<>();
                ergs.add("起始日期 不能大於 結束日期");
                throw new ParamInvalidException(ergs);
            }
            return this.budService.getBusinessDay(startDate, endDate);
        }

        List<Bud> list = this.budService.getAll();
        return list;
    }

    @GetMapping("/{budYmd}")
    public Bud getOneDate(
            @PathVariable @Pattern(regexp = "^[(?=\\d)]{8}", message = "格式錯誤")
            String budYmd) {
        return this.budService.getOneDate(budYmd);
    }


    @GetMapping("/business/{budYmd}")
    public Map getBusiness(@PathVariable("budYmd")
                           @Pattern(regexp = "^[(?=\\d)]{8}", message = "日期 格式錯誤")
                           String budYmd) {
        return this.budService.getPrevAndPost(budYmd);
    }

    @PostMapping()
    public StatusResponse addBud(@RequestBody @Valid AddBudRequest request) {
        return this.budService.addBud(request);
    }

    @PutMapping("/{budYmd}")
    public StatusResponse updateBud(
            @PathVariable@Pattern(regexp = "^[(?=\\d)]{8}", message = "格式錯誤") String budYmd,
            @RequestBody @Valid PutBudRequest request) {
        return this.budService.updateBud(budYmd, request);
    }


}
