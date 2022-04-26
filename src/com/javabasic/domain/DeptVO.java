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
//jpa만쓰는 것이 아니라 일반 spring에서 사용가능함
//setter/getter , 생성자함수를 알아서 내부적으로 지정해줌 
//@Setter
//@Getter
//@NoArgsConstructor //기본 생성자
//@AllArgsConstructor //인자있는 생성자 
//@ToString
@Data //@Getter + @Setter + @ToString + @NoArgsConstructor //인자있는 생성자 함수는 따로 추가해주는것 같음!

//[JPA]
@Entity //exrd에서 엔티티 만든다는 것을 테이블 만든다한 것 처럼 테이블과 같은 맥락으로 보면됨
@Table(name="DEPT_A") //데이터베이스 (name=" ")인 테이블과 연결하겠다.
public class DeptVO {
	@Id //deptno를 pk로 지정하겠다
	// 테이블이라면 속에 있는 변수들은 테이블의 속성들. 꼭 pk를 지정해줘야함으로 @Id 붙이기
	private int deptno;
	private String dname;
	private String loc;
	
	
	
}
