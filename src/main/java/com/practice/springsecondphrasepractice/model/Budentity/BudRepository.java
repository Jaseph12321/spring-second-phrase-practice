package com.practice.springsecondphrasepractice.model.Budentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BudRepository extends JpaRepository<Bud, Integer> {


    @Query(value = "SELECT * FROM bud WHERE bud_ymd=?1", nativeQuery = true)
    Bud findByBusinessday(String date);


    Bud findByBudYmd(String year);

    List<Bud> findByBudYmdBetween(String startDate, String endDate);

//    @Query(value="SELECT * FROM bud WHERE SUBSTR(bud_ymd,1,4)=?1",nativeQuery = true)
//    public List<Bud> findByBusinessInYear(String year);

     List<Bud> findByBudYmdStartingWith(String year);


    @Query(value="SELECT bud_ymd FROM bud WHERE bud_ymd <?1 AND bud_type='Y' ORDER BY bud_ymd DESC LIMIT 1",nativeQuery = true)
     String findPreviousBusiness(String date);

    @Query(value="SELECT bud_ymd FROM bud WHERE bud_ymd >?1 AND bud_type='Y' ORDER BY bud_ymd ASC LIMIT 1",nativeQuery = true)
    String findPostBusiness(String date);

    @Transactional
    @Query(value = "UPDATE bud SET bud_type=?1 where bud_ymd=?2", nativeQuery = true)
     void updateBud(String budType, String date);

    @Query(value="SELECT bud_ymd from bud", nativeQuery = true)
    List<String> getYmd();


}
