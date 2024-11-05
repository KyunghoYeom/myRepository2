package mypractice.forproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mypractice.forproject.domain.item.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;
    private int count;

    public void addOrder(Order order){
        this.order = order;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;

    }

    public void cancel(){
        getItem().addStock(count);
    }

    public int totalCount(){
        return getOrderPrice() * getCount();
    }



    private void setItem(Item item) {
        this.item = item;
    }

    private void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    private void setCount(int count) {
        this.count = count;
    }
}
