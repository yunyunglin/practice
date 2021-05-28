package org.tutorial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptno;
	
	@Column(name="dname")
    private String dname;
	
	@Column(name="loc")
    private String loc;
	
	@OneToMany(mappedBy="deptDO", cascade=CascadeType.REMOVE)
	private List<EmpDO> empDOs;
}
