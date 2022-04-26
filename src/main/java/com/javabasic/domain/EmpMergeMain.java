package com.javabasic.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpMergeMain {

	public static void main(String[] args) {
			//1. 엔티티 매니저 팩토리 생성
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
				
				//2. 엔티티 매니저 생성
				EntityManager em = emf.createEntityManager(); //persistence context를 관리하는게 EntityManager이 관리
				
				//5. 엔티티 트랜잭션 생성 = 커밋
				EntityTransaction tx =em.getTransaction();
				
				
				try {
					 EmpVO vo = new EmpVO();			
					 vo.setEmpno(6677);			
					 vo.setEname("맹길자2");									
					 tx.begin();			
					 //em.persist(vo);	//	insert기반 / pk 중복될 경우 에러
					 em.merge(vo); // insert기반 / pk가 중복될 경우 덮어쓰기 (update)	
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
