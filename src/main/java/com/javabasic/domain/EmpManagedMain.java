package com.javabasic.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpManagedMain {

	public static void main(String[] args) {
		//1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
		
		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//5. 엔티티 트랜잭션 생성 = 커밋
		EntityTransaction tx =em.getTransaction();
		
		try {
		//db에서 값 가져와서 출력하기
		EmpVO emp0 = em.find(EmpVO.class, 8888); //바로 db까지가서 객체가 db로 날라감
		System.out.println("검색 결과" + emp0);
		
		
		
		//3. 엔티티('=' 테이블) 생성
		EmpVO vo = new EmpVO(); //바로 db까지가서 객체가 db로 날라감
		//3-2.입력
		vo.setEmpno(9999); //setter은 context영역까지 가있고 아직 db에는 가지 않았음 db까지 가려면 commit해줘야함
		vo.setEname("홍길자");
		
		//4.등록(실행) //이 과정을 해줘야 setter의 정보가 db까지 감
		tx.begin();
		em.persist(vo);
		tx.commit();
			
			//9999를 입력한 후 9999불러오기 
			//=> 이 땐 select문장으로 가져오지 않고 바로 가져옴 왜? 위에서 9999를 올릴때 이미 영속 컨테스트(Persistence Context) 올려두었기 때문에 
			EmpVO emp1 = em.find(EmpVO.class, 9999); //바로 db까지가서 객체가 db로 날라감
			System.out.println("검색 결과" + emp1);
			
			EmpVO emp2 = em.find(EmpVO.class, 8888); //바로 db까지가서 객체가 db로 날라감
			System.out.println("검색 결과" + emp2);
		
		
		
		}catch( Exception ex) {
			System.out.println("실패: " + ex.getMessage());
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	
	}

}
