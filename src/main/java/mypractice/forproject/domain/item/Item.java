package mypractice.forproject.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mypractice.forproject.exception.NotEnoughStockException;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public void changeItemName(String name){
        this.name = name;
    }

    public void addStock(int quantity){
        stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int stock = stockQuantity - quantity;
        if(stock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity-=quantity;
    }
}
