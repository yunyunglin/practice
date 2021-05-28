package org.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

public interface DeptService {
	
	DeptDO update(DeptDO deptDO);
	
	void deleteDept(Integer deptno);
	
	Optional<DeptDO> getOneDept(Integer deptno);

	List<DeptDO> getAll();

    List<EmpDO> getEmpsByDeptno(Integer deptno);
}
