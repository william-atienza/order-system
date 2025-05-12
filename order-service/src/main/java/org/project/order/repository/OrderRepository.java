package org.project.order.repository;

import org.project.order.entity.Account;
import org.project.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    //@Query("SELECT FROM Order WHERE account = :account AND ")
    public Order findOrderByIdAndAccount(String id, Account account);
    public List<Order> findOrdersByAccount(Account account, Pageable pageable);
}
