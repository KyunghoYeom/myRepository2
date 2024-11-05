package mypractice.forproject;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mypractice.forproject.domain.Address;
import mypractice.forproject.domain.Member;
import mypractice.forproject.domain.Order;
import mypractice.forproject.domain.OrderItem;
import mypractice.forproject.domain.item.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
//
//            Order order = Order.createOrder(member, orderItem1, orderItem2);
//            em.persist(order);

        }


        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
//
//
//            Order order = Order.createOrder(member, orderItem1, orderItem2);
//            em.persist(order);

        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member(name, new Address(city, street, zipcode));

            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book(name, price, stockQuantity, "kim", "123-123");
            //String name, int price, int stockQuantity, String author, String isbn
            return book;
        }
        //파라미터 뽑아내기 ctr alt m 이후 빈 메서드에 코드 적고 추출하고 싶은 name price stock에다가 ctr alt p누르기


    }



}

