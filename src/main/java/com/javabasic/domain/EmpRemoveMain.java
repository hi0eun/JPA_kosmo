package com.javabasic.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpRemoveMain {

	public static void main(String[] args) {
			//1. 엔티티 매니저 팩토리 생성
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
				
				//2. 엔티티 매니저 생성
				EntityManager em = emf.createEntityManager(); //persistence context를 관리하는게 EntityManager이 관리
				
				//5. 엔티티 트랜잭션 생성 = 커밋
				EntityTransaction tx =em.getTransaction();
				
				
				try {
					
					EmpVO emp = em.find(EmpVO.class, 8888); //EmpVO를 클래스형식으로 가져오겠다
					System.out.println(emp);
					
					tx.begin();
					em.remove(emp);
					tx.commit();
					
					EmpVO emp2 = em.find(EmpVO.class, 8888);
					System.out.println(emp2);
				
				}catch( Exception ex) {
					System.out.println("실패: " + ex.getMessage());
					tx.rollback();
				}finally {
					em.close();
					emf.close();
				}
			}
	

}
