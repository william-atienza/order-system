package org.project.repository;

import org.project.entity.Account;
import org.project.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    //@Query("SELECT FROM Order WHERE account = :account AND ")
    public Order findOrderByIdAndAccount(UUID id, Account account);
    public List<Order> findOrdersByIdAndAccount(UUID id, Account account, Pageable pageable);
}
