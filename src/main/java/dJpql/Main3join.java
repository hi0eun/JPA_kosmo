package dJpql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.javabasic.domain.Department;

public class Main3join {

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
		// 조인쿼리 (INNER JOIN)
		//SELECT e.*, d.* FROM emp_t INNER JOIN dept_t d //sql쿼리
//		String jpql = "SELECT e, d FROM Employee e INNER JOIN e.dept d"; //jpql : 객체명(엔티티명)을 가지고 하는 것 
//		Query query = em.createQuery(jpql);
//		
//		List<Object[]> list = query.getResultList();
//		for(Object[] result : list) {
//			//System.out.println(result[0]);
//			//System.out.println(result[1]);
//			Employee emp = (Employee)result[0];
//			Department dept = (Department)result[1];
//			System.out.println(emp.getEname() + "/" + dept.getDname());
//		}
		
		//--------------------------
		// 조인쿼리 (LEFT OUTER JOIN)
		//SELECT e.*, d.* FROM emp_t inner join dept_t d //sql쿼리
//		String jpql = "SELECT e, d FROM Employee e LEFT OUTER JOIN e.dept d"; //jpql : 객체명(엔티티명)을 가지고 하는 것 
//		Query query = em.createQuery(jpql);
//				
//		List<Object[]> list = query.getResultList();
//		for(Object[] result : list) {
//			//System.out.println(result[0]);
//			//System.out.println(result[1]);
//			Employee emp = (Employee)result[0];
//			Department dept = (Department)result[1];
//			if(dept != null)
//				System.out.println(emp.getEname() + "/" + dept.getDname());
//			else
//				System.out.println(emp.getEname() + "/ 부서대기중" );
//		}
		
		//----------------------------
		// 부서명으로 정렬하고 월급순으로 정렬
//		String jpql = "SELECT e, d FROM Employee e LEFT OUTER JOIN e.dept d ORDER BY d.dname, e.sal desc"; //jpql : 객체명(엔티티명)을 가지고 하는 것 
//		Query query = em.createQuery(jpql);
//				
//		List<Object[]> list = query.getResultList();
//		for(Object[] result : list) {
//			//System.out.println(result[0]);
//			//System.out.println(result[1]);
//			Employee emp = (Employee)result[0];
//			Department dept = (Department)result[1];
//			if(dept != null)
//				System.out.println(emp.getEname() + "/" + emp.getSal() + "/" + dept.getDname());
//			else
//				System.out.println(emp.getEname() + "/ 부서대기중" );
//		}
		
		//-------------------------
		// [1] 특정부서에 속하지 않은 직원들 출력
//		String jpql = "SELECT e FROM Employee e WHERE deptno IS NULL"; //jpql : 객체명(엔티티명)을 가지고 하는 것 
//		TypedQuery<Employee> query = em.createQuery(jpql, Employee.class); //jpql로 쿼리를 받을 건데 그걸 Board 자료형으로 받겠다
//		
//		List<Employee> list = query.getResultList();
//		for(Employee b : list) {
//			System.out.println(b.toString());
//		}
		
		//-------------------------
		// [2] 평균급여가 2000 이상인 부서정보 출력
//		String jpql = "SELECT d  " + 
//				"FROM Department d " +
//				"WHERE d.deptno in (select e.dept.deptno " + 
//				"from Employee e " + 
//				"group by e.dept.deptno " + 
//				"having AVG(e.sal) >= 2000)" ; //jpql : 객체명(엔티티명)을 가지고 하는 것 
//		
//		TypedQuery<Department> query = em.createQuery(jpql, Department.class); //위에서 전체를 받는게 아니기 때문에 TypedQuery가 아닌 Query로 받아야함
//		
//		List<Department> list = query.getResultList();
//		for(Department b : list) {
//			System.out.println(b.toString());
//		}
		
		//-------------------------
		// [3] 직원이 없는 부서 정보 출력
		String jpql = "SELECT d  " + 
				"FROM Department d  " + 
				"WHERE d.deptno NOT in (SELECT e.dept.deptno FROM Employee e WHERE e.dept.deptno IS NOT NULL GROUP BY e.dept.deptno)";
		//https://i5i5.tistory.com/712 참고 외래키일 경우 엔티티의 멤버변수를 한번 더 참조해야함 ex) e.deptno => e.dept.deptno
		
		TypedQuery<Department> query = em.createQuery(jpql, Department.class); //위에서 전체를 받는게 아니기 때문에 TypedQuery가 아닌 Query로 받아야함
		
		List<Department> list = query.getResultList();
		for(Department b : list) {
			System.out.println(b.toString());
		}
	}

}
