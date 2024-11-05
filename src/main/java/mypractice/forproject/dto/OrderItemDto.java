package mypractice.forproject.dto;

import lombok.Data;
import mypractice.forproject.domain.OrderItem;

@Data
public class OrderItemDto {
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
