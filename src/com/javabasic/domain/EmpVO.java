package com.javabasic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//EMP_A 테이블 매핑

@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity 
@Table(name="EMP_A") //데이터베이스 name 테이블과 연결하겠다. 
public class EmpVO {
	@Id
	private int empno;
	private String ename;
	private String job;
	private Date hiredate;
	private int sal;
	private int comm;
	private int mgr;
	private int deptno;
	
	

}
