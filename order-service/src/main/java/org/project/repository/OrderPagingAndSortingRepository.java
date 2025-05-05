package org.project.repository;

import org.project.entity.Account;
import org.project.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderPagingAndSortingRepository extends PagingAndSortingRepository<Order, String> {
    public List<Order> findOrdersByAccount(Account account, Pageable pageable);
}
