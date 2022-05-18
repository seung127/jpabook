package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        /**
         * 1.영속성 컨텍스트 : 엔티티를 영구 저장하는 환경
         *
         * 2.영속성 컨텍스트의 생명주기
         *  1)비영속
         *  2)영속 : em.persist()
         *  3)준영속 : em.detach
         *  4)삭제 : em.remove
         *
         *  3.영속성 컨텍스트의 이점
         *   1)1차 캐시
         *   2)동일성 보장
         *   3)트랜잭션을 지원하는 쓰기 지연
         *   4)변경감지
         *   5)지연 로딩
         *
         *  4.1차 캐시에서 조회(영속성 컨텍스트에 있으면 거기서 조회 없으면 데이터 베이스로 가서 조회)
         *   Member findMember=em.find(Member.class,"member1")
         *
         *   5.수정
         *    findMember.setUsername("hi");
         *
         *   6.플러쉬(영속성 컨텍스트를 비우지 않음)
         *    1)em.flush() : 직접 호출
         *    2)트랜잭션 커밋 : 플러시 자동 호출
         *    3)jpql 쿼리 실행 : 플러시 자동 호출
         *
         *
         * ====================================================================
         * <프록시>
         * em.find() vs em. getReference()
         * 실제 엔티티 조회 vs 데이터베이스 조회를 미루는 가짜(프록시)엔티티 객체 조회
         *
         * -실제 클래스를 상속 받아서 만들어짐
         * -프록시 객체를 호출하면 프록시 객체는 실제 객체의 메소드 호출
         * -초기화
         *  1)getName()
         *  2)초기화 요청
         *  3)DB 조회
         *  4)실제 Entity 생성
         *  5)target.getName()
         *
         * -비교 대신 instance of 사용할 것
         * -영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReferene()를 호출해도 실지 엔티티 반환
         *
         *
         */




        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티 매니저 팩토리

        EntityManager em = emf.createEntityManager(); //엔티티 매니저  ->  매니저를 통해서 영속성 컨텍스트에 접근

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member=new Member();
            member.setName("hello");

            em.persist(member); //영속성 컨텍스트로 넣는 구문(영속)

            em.flush();
            em.clear();

            tx.commit(); //커밋하는 순간 데이터베이스에 insert sql을 보낸다
            
            //
            em.find(Member.class,member.getId());
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getName() = " + member.getName());
            
        }
        catch(Exception e){ tx.rollback();}
        finally{em.close();}
        emf.close();

    }
}



