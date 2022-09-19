package com.practice.springsecondphrasepractice.model.Nfaentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NfaRepository extends JpaRepository<Nfa, Integer> {


    List<Nfa> findByStartDateAndEndDate(String startDate, String endDate);

    @Transactional
    @Query(value = " UPDATE nfa SET nfa_subject=?1,nfa_content=?2,nfa_enable=?3," +
            "nfa_s_time=?4,nfa_e_time=?5,nfa_u_time=NOW() WHERE nfa_uuid=?6", nativeQuery = true)
    void updateNfaByUuid(String subject, String content, String enable, String s_time, String e_time,String uuid);

    @Transactional
    @Query(value = " UPDATE nfa SET nfa_enable=?1,nfa_u_time=NOW() WHERE nfa_uuid=?2", nativeQuery = true)
    void updateEnableByUuid(String enable,String uuid);

}
