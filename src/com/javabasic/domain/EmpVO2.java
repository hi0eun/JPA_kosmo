package com.javabasic.domain;

/*******************************
 * 	@Column 속성 (=제약조건)
 * 											[기본값 default]
 * 	` name		매핑될 컬럼명이 다른 경우			변수명과 동일
 * 	` unique	unique 제약조건 ex)주민번호 		false -> unique지정하려면 true로 지정하면됨 +unique는 null값허용하면서 유일값
 * 	` nullable 	NULL허용						false -> null값 들어갈 수 있게하려면 true로 지정
 * 	` length	문자열 길이					255
 * 	` precision	숫자타입의 전체 자리수
 * 	` scale		숫자타입의 소숫점 자리수
 * 	` columnDefinition		이 컬럼에 대한 ddl 문장을 직접 기술 / 가급적이면 안쓸것임
 * */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

//EMP_A 테이블 매핑

@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity 
@Table(name="EMP_B") //데이터베이스 name 테이블과 연결하겠다. 
public class EmpVO2 {
	@Id
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
	
	private String temp;
	//------------------------------------------------------------
	
	//********************************
	@Transient //테이블(컬럼)로 안만들어짐 자바에서만 사용할경우 ex)검색기능
	private String searchCondition;
	@Transient
	private String searchWord;

}
