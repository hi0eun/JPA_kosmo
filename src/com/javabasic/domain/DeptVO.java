package com.javabasic.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//[lombok]

//@Setter
//@Getter
//@NoArgsConstructor //기본 생성자
//@AllArgsConstructor //인자있는 생성자 
//@ToString
@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity 
@Table(name="DEPT_A") //데이터베이스 name 테이블과 연결하겠다.
public class DeptVO {
	@Id //deptno를 pk로 지정하겠다
	private int deptno;
	private String dname;
	private String loc;
	
	
	
}
