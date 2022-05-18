package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.@Entity
 *  >JPA가 관리
 *  >테이블과 매핑할 클래스는 @Entity필수
 *  >기본 생성자 필수(파라미터가 없는 public또는 protect)
 *
 */
@Entity
public class Member extends BaseEntity{

    /**
     * 1.기본 키 매핑 방법
     *  1)직접 할당 : @Id만 사용
     *  2)자동 생성 : @GeneratedValue
     *
     * 2.@GeneratedValue(strategy=GenerationType.---)
     *  1)IDENTITY : 데이터베이스에 위임
     *  2)SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용(@SequenceGenerator 필요)
     *  3)TABLE : 키 생성용 테이블 사용(@TableGenerator 필요)
     *  4)AUTO : 방언에 따라 자동 지정
     *
     */
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;


    private String name;

    @Embedded
    private Address address;

    /**
     * 일대다 매핑
     * '일'쪽이므로 mappedBy필수
     */
    @OneToMany(mappedBy = "member") //상대에서 member라는 변수에 의해서 지정된다는 것
    private List<Order> orders=new ArrayList<>();

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


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
