package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Mappedsuperclass 를 상속받는 클래스
 */
@Entity
public class Category extends  BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child= new ArrayList<>();


    /**
     * 다대다 매핑
     * 이쪽이 연관의 주인이다
     *
     * 조인테이블을 생성하는 것이 좋다
     * 일대다 일대다 관계를 만들어 주는것
     */
    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
            joinColumns = @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID"))
    private List<Item> items = new ArrayList<>();


}
