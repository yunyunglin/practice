package org.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tutorial.model.DeptDO;

@Repository
public interface DeptRepository extends JpaRepository<DeptDO, Integer>{

	//拿到的型別是DeptDO
	@Query(value = "SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno")
	DeptDO getOneDeptWithEmps(@Param("deptno") Integer deptno);
}
