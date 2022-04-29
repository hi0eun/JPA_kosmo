package dJpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.javabasic.domain.Employee;

public class Main5NativeQuery { //Native = 원본

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
		
		//**********************************
		// 네이티브 쿼리 : SQL (테이블명)
		String sql = "SELECT * FROM emp_t WHERE deptno=:dept_no";
		//em.createQuery(sql); ->jqpl 이용시
		Query query = em.createNativeQuery(sql, Employee.class); //Employee자료형으로 가져오겠다
		query.setParameter("dept_no", 20);
		List<Employee> list = query.getResultList(); //전체 컬럼을 가져온다면 클래스로 가져올 수 있는데 전체가 아닐땐 object로 가져와야함
		for(Employee e : list) {
			System.out.println(e);
		}
	}

}
