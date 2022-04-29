package dJpql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.javabasic.domain.Board;

public class Main2parameter {

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
		//[1]위치인자 바인딩 
//		String jpql = "SELECT seq,writer,title From Board WHERE seq=?1 and title=?2"; //******Board : 엔티티명(클래스명) / 클래스명은 대소문자를 구분함
//		Query query = em.createQuery(jpql);
//		query.setParameter(1, 1); //(jpql의 ?위치 , db의 seq값)
//		query.setParameter(2, "이것이 자바다");	//(jpql의 ?위치 , db의 title값)
//		
//		List<Object[]> list = query.getResultList();
//		for(Object[] b : list) {
//			System.out.println(Arrays.toString(b));
//		}
		
		//--------------------------
		//[1]키워드인자 바인딩 
		String jpql = "SELECT seq,writer,title From Board WHERE seq=:seqkw and title=:titlekw"; //******Board : 엔티티명(클래스명) / 클래스명은 대소문자를 구분함
		Query query = em.createQuery(jpql);
		query.setParameter("seqkw", 1); //(키워드명 , db의 seq값)
		query.setParameter("titlekw", "이것이 자바다"); //(키워드명 , db의 title값)
				
		List<Object[]> list = query.getResultList();
		for(Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
		
	
	}

}
