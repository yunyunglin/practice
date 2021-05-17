package org.tutorial.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="emp2")
public class EmpDO implements Serializable {

	@Id
	@Column(name="empno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empSeq")
    @SequenceGenerator(name = "empSeq", sequenceName = "emp2_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
	
	@Column(name="ename")
    private String ename;
	
	@Column(name="job")
    private String job;
	
	@Column(name="hiredate")
    private LocalDate hiredate;
	
	@Column(name="sal")
    private Double sal;
	
	@Column(name="comm")
    private Double comm;
	
	@ManyToOne
	@JoinColumn(name="deptno")
    private DeptDO deptDO;
	
}
