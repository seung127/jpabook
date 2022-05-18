package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 *<상속관계 매핑>
 * 1.조인 전략(각각의 테이블) : JOINED
 * 2.단일 테이블 전략(통합 테이블) : SINGLE_TABLE
 * 3.구현 클래스마다 테이블 전략(서브타입 테이블로 변환) : TABLE_PER_CLASS (비추천 방식)
 *
 * @DiscriminatiorColumn : 자식이 어디서 왔는지
 *
 * @DiscriminatorValue("xxx") : 어디서 왔다고 하는 자식의 이름
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    @Column(name= "ITEM_ID")
    private Long id;


    /**
     * 다대다 매핑에서 읽을 수만 있는 상태
     */
    @ManyToMany(mappedBy = "items")
    private List<Category> categories=new ArrayList<>();

    private String name;

    private int price;

    private int stockQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
