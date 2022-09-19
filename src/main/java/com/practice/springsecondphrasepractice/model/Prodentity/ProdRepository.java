package com.practice.springsecondphrasepractice.model.Prodentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Prod, Integer> {


     List<Prod> findByProdKind(String kind);


     List<Prod> findByProdCcy(String ccy);


    Prod findByProdId(String prodId);

    @Transactional
    @Query(value = "UPDATE prod SET prod_name=?1, prod_ename=?2, prod_enable=?3,prod_u_time=NOW() where prod_id=?4", nativeQuery = true)
     void updateProd(String prodName, String prodEname,String prodEnable, String prodId);

    @Transactional
    @Query(value = "UPDATE prod SETprod_enable=?1,prod_u_time=NOW() where prod_id=?2", nativeQuery = true)
    void updateProdEnable(String prodEnable,String prodId);
}
