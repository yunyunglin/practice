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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
	
	@Column(name="ename")
    @NotBlank(message = "員工姓名: 請勿空白")
	@Pattern(regexp = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$", message="員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間")
	private String ename;
	
	@Column(name="job")
    private String job;
	
	@Column(name="hiredate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
//    @NotBlank(message = "請輸入日期")
	private LocalDate hiredate;
	
	@Column(name="sal")
	@NotNull(message = "薪水請填數字")
    private Double sal;
	
	@Column(name="comm")
	@NotNull(message = "獎金請填數字")
    private Double comm;
	
	@ManyToOne
	@JoinColumn(name="deptno")
    private DeptDO deptDO;
	
}
