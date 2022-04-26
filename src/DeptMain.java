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
		//JPA를 사용하기위해선 entitymanger을 만들어주는 공장 만들기 
		
		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		//테이블 담당해주는 매니저 만들기
		
		//5. 엔티티 트랜잭션 생성 = 커밋처리해주는 것 //트랜잭션이란? 하려면 모두 다하거나 하지않으려면 전부 다 롤백해주는 단위
		EntityTransaction tx =em.getTransaction();
		
		try { //에러가 안남 이미 처리되어있다는 뜻이지만 혹시 모르니 try catch
		//3. 엔티티('=' 테이블) 생성
		DeptVO vo = new DeptVO();
		//3-2.입력
		vo.setDeptno(13);
		vo.setDname("총무");
		vo.setLoc("판교");
		
		//4.등록(실행)
		tx.begin(); //persist 실행 전에 트랜잭션 시작
		em.persist(vo);//내가만든 객체 vo를 persist 등록하겠다
		tx.commit(); //트랜잭션을 통해서 commit을 날려줘야함 
		

		}catch( Exception ex) {
			System.out.println("실패: " + ex.getMessage());
			tx.rollback(); //실패했을 경우 롤백
		}finally {
			em.close();
			emf.close();
			//db 연결 했기 때문에 connection이라던가 안쪽에 스트림을 주고 받을 것이기 때문에 닫아줘야함 
		}
	}

}
