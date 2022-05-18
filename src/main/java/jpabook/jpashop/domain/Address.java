package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * 엔티티  &  값 타입
 *
 * >엔티티
 *  @Entity로 정의하는 객체
 *
 * >값 타입
 *  int,integer,String 처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체
 *  1)기본값
 *    생명주기를 엔티티의 의존 -> 공유하면 안된다
 *
 *  2)임베디드 타입
 *     @Embeddable : 값 타입을 정의하는 곳에 표시
 *     @Embedded : 값 타입을 사용하는 곳에 표시
 *
 *  3)컬렉션 값 타입
 *     -참조를 전달하므로 수정할 수 없게 만들어야 부작용을 차단한다 -> 불변객체로 설계해야힌다(생성자만 만들고 수정자를 만들지 않아야한다)
 *     -integer와 string은 자바가 제공하는 대표적인 불변객체
 *     -값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야한다
 *
 *     -값 타입을 하나 이상 저장할 때 값타입 컬렉션을 사용한다
 *        >@ElementCollection, @CollectionTable 사용
 *        >값 타입은 엔티티와 다르게 식별자 개념이 없다
 */


@Embeddable
public class Address {

    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    public String fullAddress(){
        return getCity()+" "+getStreet()+" "+getZipcode();
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
