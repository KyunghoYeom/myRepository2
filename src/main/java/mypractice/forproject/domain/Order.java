package mypractice.forproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addMember(Member member){
        this.member = member;
        member.addOrder(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    public static Order createOrder(Member member, OrderItem... orderItems){
        Order order = new Order();
        order.addMember(member);
        order.setOrderDate();
        order.setOrderStatus();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;

    }

    public void cancel(){
        this.setCancelStatus();
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }

    }
    public int getTotalPrice(){
        int sum = 0;
        for (OrderItem orderItem : orderItems) {
            sum+= orderItem.totalCount();
        }
        return sum;
    }

    private void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    private void setOrderStatus() {
        this.status = OrderStatus.ORDER;
    }
    private void setCancelStatus() {
        this.status = OrderStatus.CANCEL;
    }
}
