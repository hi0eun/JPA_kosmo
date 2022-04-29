package dJpql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.javabasic.domain.Board;

public class Main1basic {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dJpql");
		try {
			selectData(emf);
			
		}catch(Exception ex) {
			System.out.println("예외발생: " + ex.getMessage());
		}finally {
			emf.close();
		}
		
	}
	
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		//--------------------------
		//[1]Board 객체의 모든 항목을 출력 (전체 정보를 받을 땐 TypedQuery에 Board 자료형으로 받겠다.)
		// 비교 -> SELECT * FROM board; => board : 테이블명
//		String jpql = "SELECT b FROM Board as b"; //******Board : 엔티티명(클래스명) / 클래스명은 대소문자를 구분함
//		TypedQuery<Board> query = em.createQuery(jpql, Board.class); //jpql로 쿼리를 받을 건데 그걸 Board 자료형으로 받겠다
		
//		List<Board> list = query.getResultList();
//		for(Board b : list) {
//			System.out.println(b.toString());
//		}
		
		//---------------------------
		//[2] Board 객체에서 선택한 컬럼(변수)만 출력 (특정 컬럼만 받으려면 Query에 받겠으며 Object[]형식으로 받아야 출력 가능 )
		String jpql = "SELECT seq, writer, title FROM Board as b"; //******Board : 엔티티명(클래스명) / 클래스명은 대소문자를 구분함
		Query query = em.createQuery(jpql); //위에서 전체를 받는게 아니기 때문에 TypedQuery가 아닌 Query로 받아야함
		
		List<Object[]> list = query.getResultList();
		for(Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}

}
