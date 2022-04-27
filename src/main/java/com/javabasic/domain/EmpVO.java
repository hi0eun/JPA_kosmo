package com.javabasic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//EMP_D 테이블 매핑

@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity 
@Table(name="EMP_D") //데이터베이스 name 테이블과 연결하겠다. /이름 안쓰면 클래스명과 똑같은 테이블이 만들어짐
public class EmpVO {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)//자동증가수
	private int empno;
	
	private String ename;
	private String job;
	private Date hiredate;
	private int sal;
	private int comm;
	private int mgr;
	private int deptno;
	
	

}
