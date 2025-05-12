package org.project.order.repository;

import org.project.order.entity.Account;
import org.project.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderPagingAndSortingRepository extends PagingAndSortingRepository<Order, String> {
    public List<Order> findOrdersByAccount(Account account, Pageable pageable);
}
