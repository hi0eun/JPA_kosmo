package com.javabasic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data

@Entity
@Table(name="EMP_T")
//@ToString(exclude = "dept")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private String hiredate;
	private Integer sal;
	private Integer comm;
	//private Integer deptno; //int인 경우 default 값이 0
							//integer인 경우 default 값이 null
							//이게 요즘 추세
	
	//******************************
	//연관관계 : 객체에서도 관계를 맺을 때
	@ManyToOne //하나의 부서에 여러명의 사원이 소속될 수 있음 N:1 매핑 -> main클래스에서 연관관계간 select문의 join이 left outer join
	//@ManyToOne(optional = false)
	//@ManyToOne(optional = false, fetch=FetchType.EAGER) 
		//--optional의 기본 값은 true -> main클래스에서 연관관계간 select문의 join이 inner join 
		//--EAGER은 처음부터 join으로 select가져옴
	//@ManyToOne(optional = false, fetch=FetchType.LAZY) 
		//--LAZY은 join해오는 시점이 사용할때임 그래서 select를 두번 함 처음에는 employee를 join없이 select해오고 연관관계를 이용할 때 그 때 join으로 한 번 더 select함
		//=이렇게 시점까지는 신경 안써도 될듯 inner join과 left outer join만 정하면 될듯
	@JoinColumn(name="deptno") 
	private Department dept; //department라고 하는 클래스가 아예 들어옴
	
	
}
