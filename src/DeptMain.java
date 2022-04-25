import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.javabasic.domain.DeptVO;

public class DeptMain {

	public static void main(String[] args) {
		
		//1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aBasic"); //persistence.xml에서 지정한 이름
		
		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//5. 엔티티 트랜잭션 생성 = 커밋
		EntityTransaction tx =em.getTransaction();
		
		try {
		//3. 엔티티('=' 테이블) 생성
		DeptVO vo = new DeptVO();
		//3-2.입력
		vo.setDeptno(13);
		vo.setDname("총무");
		vo.setLoc("판교");
		
		//4.등록(실행)
		tx.begin();
		em.persist(vo);
		tx.commit();
		
		em.persist(vo);
		}catch( Exception ex) {
			System.out.println("실패: " + ex.getMessage());
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}

}
