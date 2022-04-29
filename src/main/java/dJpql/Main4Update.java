package dJpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.javabasic.domain.Department;

public class Main4Update {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dJpql");
		try {
			//deleteData(emf);
			//updateData(emf);
			updateData2(emf);
			
		}catch(Exception ex) {
			System.out.println("예외발생: " + ex.getMessage());
		}finally {
			emf.close();
		}
		
	}
	
	static void updateData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		// 7788 사원의 월급을 2배로 올리기
		
		String jpql = "UPDATE Employee e SET sal = sal*2 WHERE empno=:empno";
		Query query = em.createQuery(jpql);
		query.setParameter("empno", 7788);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// SELECT : query.getListResultList()
		// UPDATE, DELETE, INSERT : query.excuteUpdate()
		int result = query.executeUpdate();
		System.out.println(result + "행 수행");
		tx.commit();
		em.close();
		
		}
		
	static void deleteData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
			
		//7369 사원 정보를 삭제
		// jpql (엔티티 : Employee) !== SQL ( 테이블:EMP_T )
		//SQL : "DELETE FROM emp_t WHERE empno=?";
		//String jpql = "DELETE FROM Employee e WHERE empno=?1"; //위치인자
		String jpql = "DELETE FROM Employee e WHERE empno=:empno"; //키워드인자 //from 안써도 됨
		Query query = em.createQuery(jpql);
		query.setParameter("empno", 7369);
			
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// SELECT : query.getListResultList()
		// UPDATE, DELETE, INSERT : query.excuteUpdate()
		int result = query.executeUpdate();
		System.out.println(result + "행 수행");
		tx.commit();
		em.close();
		
	}
	
	static void updateData2(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		// 7788 사원의 부서를 30번부서로 변경
		
		//String jpql = "UPDATE Employee e SET deptno=30 WHERE empno=:empno";
		String jpql = "UPDATE Employee e SET e.dept=:dept WHERE empno=:empno";		
		Query query = em.createQuery(jpql);
		query.setParameter("empno", 7788);
		Department dp = em.find(Department.class, 30); // Persistence Context 영속컨택스트 사용하기 
		query.setParameter("dept", dp);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// SELECT : query.getListResultList()
		// UPDATE, DELETE, INSERT : query.excuteUpdate()
		int result = query.executeUpdate();
		System.out.println(result + "행 수행");
		tx.commit();
		em.close();
		
		}

}
