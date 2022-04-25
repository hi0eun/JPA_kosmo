package com.javabasic.domain;

/*******************************
 * 	@Column 속성
 * 											[기본값 default]
 * 	` name		매핑될 컬럼명이 다른 경우			변수명
 * 	` unique	unique 제약조건 ex)주민번호		false -> unique지정하려면 true로 지정하면됨
 * 	` nullable 	NULL허용						false -> null값 들어갈 수 있게하려면 true로 지정
 * 	` length	문자열 길이					255
 * 	` precision	숫자타입의 전체 자리수
 * 	` scale		숫자타입의 소숫점 자리수
 * 	` columnDefinition		이 컬럼에 대한 ddl 문장을 직접 기술 
 * */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

//EMP_A 테이블 매핑

@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity 
@Table(name="EMP_C") //데이터베이스 name 테이블과 연결하겠다. 

//오라클인경우(마리아디비나 mysql은 안써도됨/오라클인 경우에도 안만들면 자동으로 만들어줌 )
//@SequenceGenerator(name="EMP_C_SEQ", sequenceName = "SQL_EMP_EMPNO", initialValue = 1)
public class EmpVO3 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가수 //마리아디비 & mysql => IDENTITY / 오라클 => SEQUENCE
	private int empno;
	
	@Column(length=50)
	private String ename;
	
	@Column(length=30, nullable=true)
	private String job;
	
	@Column(name="hire_date")
	private Date hiredate;
	
	@Column(precision = 5)
	private int sal;
	
	@Column(precision = 10, scale = 3)
	private int comm;
	
	@Column(nullable = true)
	private int mgr;
	
	@Column(columnDefinition = "int check(deptno in (87,88,99))") //deptno에는 87,88,99만 들어올 수 있음  
	private int deptno;
	
	

}
