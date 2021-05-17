package org.tutorial.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name="dept2")
public class DeptDO implements Serializable {

	@Id
	@Column(name="deptno")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSeq")
    @SequenceGenerator(name = "deptSeq", sequenceName = "dept2_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptno;
	
	@Column(name="dname")
    private String dname;
	
	@Column(name="loc")
    private String loc;
	
	@OneToMany(mappedBy="deptDO", cascade=CascadeType.REMOVE)
//	@OrderBy("empno")
	private List<EmpDO> empDOs;
}
