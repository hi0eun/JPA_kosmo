import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.javabasic.domain.EmpVO;

public class EmpMain {

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
		EmpVO vo = new EmpVO();
		//3-2.입력
		vo.setEmpno(5);
		vo.setEname("ts");
		vo.setJob("개발자");
		vo.setHiredate(new Date()); //new Date("2022/04/25")이렇게 쓰면 날짜 지정도 됨
		vo.setSal(500);
		vo.setComm(200);
		vo.setMgr(20);
		vo.setDeptno(10);
		
		
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
