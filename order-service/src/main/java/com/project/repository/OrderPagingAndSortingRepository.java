package com.project.repository;

import com.project.entity.Account;
import com.project.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPagingAndSortingRepository extends PagingAndSortingRepository<Order, String> {
    public List<Order> findOrdersByAccount(Account account, Pageable pageable);
}
