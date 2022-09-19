package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.BudRequest.AddBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.BudRequest.PutBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.Budentity.Bud;
import com.practice.springsecondphrasepractice.model.Budentity.BudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BudService {

    @Autowired
    BudRepository repo;

    //1.
    public List<Bud> getAll() throws Exception {
        try {
            List<Bud> list = this.repo.findAll();
            if (list.size() == 0) throw new DataNotFoundException("資料不存在");
            return list;
            // return setStandard(list);
        } catch (Exception e) {
            System.out.println(e);
        }
        throw new Exception();
    }

    //2.
    public Bud getOneDate(String ymd) {
        return this.repo.findByBudYmd(ymd);
    }

    //3.
    public List<Bud> getBusinessDay(String startDate, String endDate) {
        List<Bud> dates = this.repo.findByBudYmdBetween(startDate, endDate);
        return dates;
    }

    //4.
    public List<Bud> getBusinessByYear(String year) {
        List<Bud> buds = this.repo.findByBudYmdStartingWith(year);
        return buds;
    }

    //5.
    public Map getPrevAndPost(String date) {
        String prev_date = this.repo.findPreviousBusiness(date);
        String post_date = this.repo.findPostBusiness(date);
        Map<String, String> dates = new LinkedHashMap<>();
        dates.put("budYmd", date);
        dates.put("budPrevYmd", prev_date);
        dates.put("nudNextYmd", post_date);
        return dates;
    }

    public StatusResponse addBud(AddBudRequest request) {
        Bud newBud = new Bud();
        newBud.setBudYmd(request.getBudYmd());
        newBud.setBudType(request.getBudType());
        newBud.setBudUTime(LocalDateTime.now());
        repo.save(newBud);
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateBud(String date, PutBudRequest request) {
        this.repo.updateBud(request.getBudType(), date);
        return new StatusResponse("異動成功");
    }

//    String s = b.getBudUTime().toString().substring(0,19)+".000+0800";
}
