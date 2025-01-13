package com.example.deliveryApp.store.repository;

import com.example.deliveryApp.entity.Store;
import com.example.deliveryApp.entity.StoreStatus;
import com.example.deliveryApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 유저 아이디당 데이터에 저장된 가게 개수 세기
    int countByUser(User user);
    //
    List<Store> findAllByStoreStatus(StoreStatus storeStatus);


}
