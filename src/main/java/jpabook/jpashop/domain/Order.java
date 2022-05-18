package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

  //  @Column(name="MEMBER_ID")
  //  private Long memberId;

    /**
     * 다대일 매핑
     * '다'쪽이므로 관계의 주인
     *
     * "다대일 양방향 매핑이 좋다"
     *
     * ----------------------------
     *
     * <지연로딩>
     * em.find() 등 직접 필요로 할 때 로딩 될 수 있도록 하는 것이 지연로딩
     * fetch=fetchType.LAZY
     *
     * 같이 사용하는 것이 많다면 EAGER
     * 하지만 실제로는 LAZY를 추천
     * @ManyToOne / @OnetoOne 은 기본이 즉시 로딩이므로 LAZY로 바꿔주어야 한다
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")  //'일'쪽의 주요키를 가지고 온다
    private Member member;

    /**
     * 영속성 전이
     * cascade=CascadeType.ALL
     */

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIvERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    private LocalDateTime orderDate;


    /**
     * enum타입을 매핑할 때 사용
     * EnumType.ORDINAL은 사용하지 말것 : 순서를 데이터베이스에 저장한다
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
