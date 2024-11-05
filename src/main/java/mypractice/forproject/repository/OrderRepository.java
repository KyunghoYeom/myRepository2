package mypractice.forproject.repository;

import mypractice.forproject.domain.Member;
import mypractice.forproject.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"member", "orderItems", "orderItems.item"})
    @Query("select distinct o from Order o")
    List<Order> findOrdersWithDetails();


    @EntityGraph(attributePaths = {"member"})
    @Query("select o from Order o")
    Page<Order> findOrdersWithPaging(Pageable pageable);





}
