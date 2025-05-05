package org.project.repository;

import org.project.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    @Query(value = "SELECT * FROM shipping WHERE order_id = :order_id ORDER BY created_on DESC LIMIT 1", nativeQuery = true)
    Optional<Shipping> findLatestShippingByOrderId(@Param("order_id") String orderId);
}
