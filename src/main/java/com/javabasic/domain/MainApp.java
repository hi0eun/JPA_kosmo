package com.javabasic.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;

public class MainApp {

	public static void main(String[] args) {
		//1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cReference"); //persistence.xml에서 지정한 이름
		
		
		try {
		//1.연관관계를 이용한 입력
		//insertData(emf);
			
		//2.연관관계 객체를 검색
		//selectData(emf);
		
		//3.연관관계에서 객체를 수정
		//updateData(emf);
		
		//4.연관관계에서 객체를 삭제
		//deleteData(emf);
			
		//5.추가확인
		testData(emf);
		
		}catch( Exception ex) {
			System.out.println("실패: " + ex.getMessage());
		}finally {
			emf.close();
		}
		
	}
	
	//------------------------testData(emf);---------------------------------
	private static void testData(EntityManagerFactory emf) {
		//[1] 7900 사원의 부서명과 위치를 출력
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 7900);
		System.out.println("소속 부서명 : " + e1.getDept().getDname()); //연관관계를 맺고 select할 때 join해서 오기 때문에 부서의 부서명도 가져올 수 있음 
		System.out.println("부서 위치 : " + e1.getDept().getLoc());
		
		// [2] 30번 부서의 소속된 모든 사원들의 정보를 출력
	      Department dept = em.find(Department.class, 30);
	       for(Employee e : dept.getEmployeeList() ) {
	           System.out.println(e.getEname());
	        }
		
		
	}
	//=========================================================================
	
	
	//------------------------deleteData(emf);---------------------------------
	private static void deleteData(EntityManagerFactory emf) {
		//44 번 부서를 삭제
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction(); //insert/update/delete때만 필요함
		
		//트랜잭션 시작
		tx.begin();
		
		//[1]7788 사원의 부서번호를 NULL로 지정하고 난 후에 부서를 삭제 가능
		//Employee emp2 = em.find(Employee.class,7788);
		//emp2.setDept(null);
		
		//부서테이블의 44번 부서 삭제
		//Department dept = em.find(Department.class,44); //EmpVO를 클래스형식으로 가져오겠다
		//em.remove(dept);
		
		//[2] 부서를 삭제하기 위해 사원테이블의 사원정보를 삭제 
		//Depatment.java에 cascade 해줘야함
		//*********위험한 방식임***********
		//왜? 부서 삭제시 사원정보다 다 삭제 되기 때문
		//부서테이블의 41번 부서 삭제
		Department dept = em.find(Department.class,41); //EmpVO를 클래스형식으로 가져오겠다
		em.remove(dept);
		
	
		tx.commit();
		em.close();
	}
	//=========================================================================
	//+++++++++++Employee.java에서 @ManyToOne(optional = false, fetch=FetchType.EAGER)로 지정되어있을 경우
	//수정 or 삭제 후 커밋이 안되는 오류 발생 inner join 특성 때문에 안되는 것 같음 @ManyToOne만 줬더니 해결됨 
	
	//------------------------updateData(emf);---------------------------------
	
	private static void updateData(EntityManagerFactory emf) {
		// 인천의 총무 부서를 추가하고 7788 기존 사원의 부서를 총무로 수정하기
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction(); //insert/update/delete때만 필요함
		
		//트랜잭션 시작
		tx.begin();
		
		//(1) 인천의 총무 부서를 추가
		Department dept1 = new Department();
		dept1.setDname("총무");
		dept1.setLoc("인천");
		em.persist(dept1);
				
		//(2) 7788 기존 사원의 부서를 총무로 수정하기
		Employee emp2 = em.find(Employee.class, 7788);
		emp2.setDept(dept1);
		em.persist(emp2);
		
		//커밋
		tx.commit();
		em.close();
	}
	
	//=========================================================================
	
	//------------------------selectData(emf);---------------------------------
	/* [left outer join] 부서번호가 없어도 = (null)이어도 사원번호를 통해서 가져올 수 있음
	 * 연관관계맺은 곳 (Employee.java)에서 @ManyToOne만 줬을 경우  
	 * select employee0_.empno as empno1_1_0_, 
	 * 			employee0_.comm as comm2_1_0_, 
	 * 			employee0_.deptno as deptno8_1_0_, 
	 * 			employee0_.ename as ename3_1_0_, 
	 * 			employee0_.hiredate as hiredate4_1_0_, 
	 * 			employee0_.job as job5_1_0_, 
	 * 			employee0_.mgr as mgr6_1_0_, 
	 * 			employee0_.sal as sal7_1_0_, 
	 * 			department1_.deptno as deptno1_0_1_, 
	 * 			department1_.dname as dname2_0_1_, 
	 * 			department1_.loc as loc3_0_1_ 
	 * from EMP_T employee0_ left outer join DEPT_T department1_  
	 * on employee0_.deptno=department1_.deptno 
	 * where employee0_.empno=?
	 * */
	
	/*	[inner join] 없는 부서번호 입력시 null값 가져옴 => 정보를 못 가져옴
	 * 연관관계맺은 곳 (Employee.java)에서 @ManyToOne(optional = false)을 줬을 경우  
	 * select employee0_.empno as empno1_1_0_, 
	 * employee0_.comm as comm2_1_0_, 
	 * employee0_.deptno as deptno8_1_0_, 
	 * employee0_.ename as ename3_1_0_, 
	 * employee0_.hiredate as hiredate4_1_0_, 
	 * employee0_.job as job5_1_0_, 
	 * employee0_.mgr as mgr6_1_0_, 
	 * employee0_.sal as sal7_1_0_, 
	 * department1_.deptno as deptno1_0_1_, 
	 * department1_.dname as dname2_0_1_, 
	 * department1_.loc as loc3_0_1_ 
	 * from EMP_T employee0_ inner join DEPT_T department1_ 
	 * on employee0_.deptno=department1_.deptno 
	 * where employee0_.empno=?
	 * */

	
	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 7788);
		System.out.println("사원정보 : " + e1);
		//System.out.println("소속 부서명 : " + e1.getDept().getDname()); //연관관계를 맺고 select할 때 join해서 오기 때문에 부서의 부서명도 가져올 수 있음 
	}
	
	//====================================================================
	
	//------------------------insertData(emf);---------------------------------
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction(); //insert/update/delete때만 필요함
		
		tx.begin();
		
		//(1)부서번호 없이 사원정보 입력하기
//		Employee emp1 = new Employee();
//		emp1.setEname("홍길숙");
//		emp1.setJob("개발");
//		//emp1.setDeptno(40); 
//		//forign key는 null값 허용되는데 데이터형이 int일 때 비어있으면 0이 들어감 0은 db에 없기 때문에 에러가남 Interger(레퍼클래스)로 바꾸면 null들어감
//		em.persist(emp1);
		
		//(2)새로운 부서에 사원 정보 입력하기
		
		//(2-1)새로운 부서 생성
		Department dept1 = new Department();
		dept1.setDname("IT");
		dept1.setLoc("가산");
		em.persist(dept1);
		
		//(2-2)생성된 새로운 부서에 사원 정보 입력
		Employee emp2 = new Employee();
		emp2.setEname("홍순이");
		emp2.setDept(dept1);
		em.persist(emp2);
		
		tx.commit();
		em.close();
		
	}
	//====================================================================
}
