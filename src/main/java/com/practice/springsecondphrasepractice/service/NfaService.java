package com.practice.springsecondphrasepractice.service;


import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.AddNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.PutNERequest;
import com.practice.springsecondphrasepractice.controller.dto.request.NfaRequest.PutNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.Nfaentity.Nfa;
import com.practice.springsecondphrasepractice.model.Nfaentity.NfaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NfaService {

    @Autowired
    NfaRepository repo;

    public List<Nfa> getAll() throws DataNotFoundException {
        List<Nfa> list = this.repo.findAll();
        if(list.size() == 0) throw new DataNotFoundException("資料不存在");
        return list;
    }

    public List<Nfa> getByCondition(String keyword, String startDate, String endDate) throws DataNotFoundException {
        List<Nfa> list = this.repo.findByStartDateAndEndDate(startDate, endDate);
        List<Nfa> chosenList = new ArrayList<>();
        for(Nfa n : list) {
            int flag=0;
            if (null != keyword) {
                  if(n.getNfaSubject().contains(keyword)) {
                      chosenList.add(n);
                      flag=1;
                  }
            }
            if (null != startDate) {
                  if(!startDate.equals(n.getStartDate()) && 1==flag){
                      chosenList.remove(n);
                      flag =0;
                  }else if(startDate.equals(n.getStartDate()) && 0==flag){
                      chosenList.add(n);
                  }
            }
            if (null != endDate) {
                if(!endDate.equals(n.getEndDate()) && 1==flag){
                    chosenList.remove(n);
                }else if(endDate.equals(n.getEndDate()) && 0==flag){
                    chosenList.add(n);
                }
            }
        }
        if(chosenList.size() == 0) throw new DataNotFoundException("資料不存在");
        return chosenList;
    }

    public StatusResponse addNfa(AddNfaRequest request) {
        Nfa newNfa = new Nfa();
        LocalDateTime dateTime = LocalDateTime.now();

        newNfa.setNfaUuid(createNfaId(dateTime));
        newNfa.setNfaSubject(request.getSubject());
        newNfa.setNfaContent(request.getContent());
        newNfa.setNfaEnable(request.getEnable());
        newNfa.setStartDate(request.getStartDate());
        newNfa.setEndDate(request.getEndDate());
        newNfa.setNfaUTime(dateTime);

        this.repo.save(newNfa);
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateNfa(String nfaUuid, PutNfaRequest request) {
        this.repo.updateNfaByUuid(request.getSubject(), request.getContent(), request.getEnable(),
                request.getStartDate(), request.getEndDate(), nfaUuid);
        return new StatusResponse("異動成功");
    }

    public StatusResponse cancelNfa(String nfaUuid, PutNERequest request) {
        this.repo.updateEnableByUuid(request.getEnable(),nfaUuid);
        return new StatusResponse("撤告成功");
    }


    //==========================================
    // below are methods

    public String createNfaId(LocalDateTime t) {
        String s = t.toString();
        String date = s.substring(0, 10);
        String time = s.substring(11, 23);
        String[] split1 = date.split("-");
        String[] split2 = time.split(":");
        System.out.println(split2[0]);
        String s3 = split2[2];
        String a = s3.substring(0, 2);
        String b = s3.substring(3);

        String combine = "";
        for (String i : split1) combine += i;
        for (int i = 0; i < split2.length - 1; i++) combine += split2[i];
        combine = combine + a + b;

        return combine;
    }

}
