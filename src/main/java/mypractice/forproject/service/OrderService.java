package mypractice.forproject.service;

import lombok.RequiredArgsConstructor;
import mypractice.forproject.domain.Member;
import mypractice.forproject.domain.Order;
import mypractice.forproject.domain.OrderItem;
import mypractice.forproject.domain.item.Item;
import mypractice.forproject.dto.OrderDto;
import mypractice.forproject.repository.ItemRepository;
import mypractice.forproject.repository.MemberRepository;
import mypractice.forproject.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member findMember = memberRepository.findById(memberId).get();
        Item findItem = itemRepository.findById(itemId).get();

        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
        Order order = Order.createOrder(findMember, orderItem);
        orderRepository.save(order);
        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    public Page<Order> findOrdersWithPaging(Pageable pageable) {
        return orderRepository.findOrdersWithPaging(pageable);
    }
    public List<Order> findOrdersWithDetails() {
        return orderRepository.findOrdersWithDetails();
    }







}
