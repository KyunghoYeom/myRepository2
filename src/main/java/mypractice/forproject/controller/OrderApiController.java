package mypractice.forproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mypractice.forproject.domain.Order;
import mypractice.forproject.dto.CancelOrderResponseDto;
import mypractice.forproject.dto.CreateOrderRequestDto;
import mypractice.forproject.dto.CreateOrderResponseDto;
import mypractice.forproject.dto.OrderDto;
import mypractice.forproject.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("api/orders")
    public CreateOrderResponseDto order(@RequestBody @Valid CreateOrderRequestDto dto){
        Long orderId = orderService.order(dto.getMemberId(), dto.getItemId(), dto.getCount());
        return new CreateOrderResponseDto(orderId);

    }

    @PutMapping("api/orders/{id}")
    public CancelOrderResponseDto cancelOrder(@PathVariable("id")Long id){
        orderService.cancelOrder(id);
        return new CancelOrderResponseDto(id);
    }

    @GetMapping("api/orders/paging")
    public Page<OrderDto> findOrdersWithPaging(Pageable pageable){
        return orderService.findOrdersWithPaging(pageable)
                .map(o->new OrderDto(o));

    }

    @GetMapping("api/orders/details")
    public List<OrderDto> findOrdersWithDetails(){
        return orderService.findOrdersWithDetails().stream()
                .map(o->new OrderDto(o)).collect(Collectors.toList());
    }
}
