package com.javabasic.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpNewMain {

	public static void main(String[] args) {
		//1. 엔티티 매니저 팩토리 생성
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
				
				//2. 엔티티 매니저 생성
				EntityManager em = emf.createEntityManager();
				
				//5. 엔티티 트랜잭션 생성 = 커밋
				EntityTransaction tx =em.getTransaction();
				
				try {
				//3. 엔티티('=' 테이블) 생성
				EmpVO vo = new EmpVO(); //바로 db까지가서 객체가 db로 날라감
				//3-2.입력
				vo.setEmpno(8888); //setter은 context영역까지 가있고 아직 db에는 가지 않았음 db까지 가려면 commit해줘야함
				vo.setEname("홍길자");
				
				//4.등록(실행) //이 과정을 해줘야 setter의 정보가 db까지 감
				tx.begin();
				em.persist(vo);
				tx.commit();
				
				
				}catch( Exception ex) {
					System.out.println("실패: " + ex.getMessage());
					tx.rollback();
				}finally {
					em.close();
					emf.close();
				}
			}
	

}
