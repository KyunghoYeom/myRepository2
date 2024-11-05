package mypractice.forproject.dto;

import lombok.Data;
import mypractice.forproject.domain.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long id;
    private String memberName;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.memberName = order.getMember().getName();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
    }
}
