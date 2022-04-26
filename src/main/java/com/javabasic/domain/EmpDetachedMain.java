package com.javabasic.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpDetachedMain {

	public static void main(String[] args) {
		//1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
		
		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//5. 엔티티 트랜잭션 생성 = 커밋
		EntityTransaction tx =em.getTransaction();
		
		try {
		//##############[1] 엔티티 컨테이너 (persistence context)에 Managed 상태 
		EmpVO emp0 = em.find(EmpVO.class, 8888); //바로 db까지가서 객체가 db로 날라감
		System.out.println("검색 결과" + emp0);
		
		
		
		//4.등록(실행) //이 과정을 해줘야 setter의 정보가 db까지 감
		tx.begin();
		//##############
		em.detach(emp0); 
		// 준영속 : 영속성 컨텍스트가 관리하던 영속상태의 엔티티를 영속성 컨텍스트가 관리하지 않은 상태
		//.detach() : 분리 
		emp0.setEname("홍홍이3"); //db 업데이트함 //위에서 .detach를 했기 때문에 emp0은 영속컨텐스에 분리됨 그러므로 update도 commit도 되지않음 
		tx.commit();
		
		//영속 컨테스트에 있는 값이기 때문에 select 안하고 가져올 수 있음 
		//=db값과 영속 컨테스트에 있는 값이 같으면 가지고올 필요가 없기 때문에
		EmpVO emp1 = em.find(EmpVO.class, 8888); //바로 db까지가서 객체가 db로 날라감
		System.out.println("검색 결과" + emp1);
		
		
		
		
		}catch( Exception ex) {
			System.out.println("실패: " + ex.getMessage());
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	
	}

}
