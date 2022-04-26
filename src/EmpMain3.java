import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.javabasic.domain.EmpVO3;

public class EmpMain3 {

	public static void main(String[] args) {
		
		//Emp 테이블에 레코드를 입력하기
		
		//1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aBasic"); //persistence.xml에서 지정한 이름
		
		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//5. 엔티티 트랜잭션 생성 = 커밋
		EntityTransaction tx =em.getTransaction();
		
		try {
		//3. 엔티티('=' 테이블) 생성
		EmpVO3 vo = new EmpVO3();
		//3-2.입력
		//vo.setEmpno(2);
		//사번 입력하지않고 자동증가수 
		
		vo.setEname("테스트");
		vo.setJob("테스트직업");
		vo.setHiredate(new Date());
		vo.setSal(500);
		vo.setComm(200);
		vo.setMgr(20);
		vo.setDeptno(88);
		
		
		//4.등록(실행)
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
