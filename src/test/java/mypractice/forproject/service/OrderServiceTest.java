package mypractice.forproject.service;

import jakarta.persistence.EntityManager;
import mypractice.forproject.domain.Address;
import mypractice.forproject.domain.Member;
import mypractice.forproject.domain.Order;
import mypractice.forproject.domain.OrderStatus;
import mypractice.forproject.domain.item.Book;
import mypractice.forproject.exception.NotEnoughStockException;
import mypractice.forproject.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired EntityManager em;
    @Test
    void 상품_주문(){
        Address address = new Address("ilsan", "gangsun", "123");
        Member member = new Member("nameA", address);
        em.persist(member);

        Book book = new Book("jpabook", 10000, 15, "kyung", "12");
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        Order order = orderRepository.findById(orderId).get();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(13);



    }

    @Test
    void 상품_재고_초과(){
        Address address = new Address("ilsan", "gangsun", "123");
        Member member = new Member("nameA", address);
        em.persist(member);

        Book book = new Book("jpabook", 10000, 2, "kyung", "12");
        em.persist(book);

        assertThatThrownBy(()->orderService.order(member.getId(), book.getId(), 3))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessageContaining("need more stock");
    }

    @Test
    void 상품_취소(){
        Address address = new Address("ilsan", "gangsun", "123");
        Member member = new Member("nameA", address);
        em.persist(member);

        Book book = new Book("jpabook", 10000, 15, "kyung", "12");
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        orderService.cancelOrder(orderId);

        Order order = orderRepository.findById(orderId).get();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(15);
    }

}