package com.order.api.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.OrderInfo;

public interface OrderInfoRepo extends CrudRepository<OrderInfo, Integer> {
    @Query(value = "SELECT* FROM order_info s WHERE s.user_id = :solve ", nativeQuery = true)
    public List<OrderInfo> findAllByUserid(Integer solve);

    public Optional<OrderInfo> findByUseridAndOrderid(Integer userid,Integer orderid);
}
