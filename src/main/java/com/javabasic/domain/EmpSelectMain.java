package com.javabasic.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpSelectMain {

	public static void main(String[] args) {
		//1. 엔티티 매니저 팩토리 생성
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState"); //persistence.xml에서 지정한 이름
				
				//2. 엔티티 매니저 생성
				EntityManager em = emf.createEntityManager();
				
				//5. 엔티티 트랜잭션 생성 = 커밋
				EntityTransaction tx =em.getTransaction();
				
				try {
					//[1] find() : 사용하든 안하든 select로 값 가져옴 / 없는 값 가져오라하면 null값 가져옴 
					//EmpVO emp = em.find(EmpVO.class , 8888);
					//System.out.println("검색결과" + emp);
					
					//[2] getReference()  : 사용안하면 안가져옴 사용할 때 그때 가져옴 / 없는 값 가져오라하면 아예 오류뜸 
					//EmpVO emp = em.getReference(EmpVO.class , 8888);
					//System.out.println("검색결과" + emp);
					
					//-------------------------------------------------
					//**************jpal********************
					//		SQL이 아닙니다
					
					//-------jpql : 내가 쿼리문장을 직접 쓸 경우를 jpql이라함 (서브 쿼리, 복잡한 쿼리 등 등)
					
					//String jpql = "SELECT e.* FORM EMP_D e ORDER BY e.empno DESC"; //내가 sql만들거야
					//**********************위 문장은 sql입니다.
					
					String jpql = "SELECT e FROM EmpVO e ORDER BY e.empno DESC"; //데이터베이스테이블 명이 아닌 객체명을 써야함 (=EempVO)
					List<EmpVO> list = em.createQuery(jpql, EmpVO.class).getResultList(); //sql문장을 보낼건데 그걸 리스트 형식으로 담을거야
					for(EmpVO vo : list) { //받은 리스트를 하나씩 뽑아낼거야
						System.out.println(vo);
					}
				
				
				}catch( Exception ex) {
					System.out.println("실패: " + ex.getMessage());
					tx.rollback();
				}finally {
					em.close();
					emf.close();
				}
			}
	

}
