package org.tutorial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.repository.DeptRepository;
import org.tutorial.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptRepository deptRepository;

	@Override
	@Transactional
	public DeptDO update(DeptDO deptDO) {
		return deptRepository.save(deptDO);
	}
	
	@Override
	@Transactional
	public void deleteDept(Integer deptno) {
		deptRepository.deleteById(deptno);
	}
	
	@Override
	public Optional<DeptDO> getOneDept(Integer deptno) {
		return deptRepository.findById(deptno);
	}
	
	@Override
	public List<DeptDO> getAll() {
		return deptRepository.findAll();
	}


	@Override
	public List<EmpDO> getEmpsByDeptno(Integer deptno) {
		DeptDO deptDO = deptRepository.getOneDeptWithEmps(deptno);
		return deptDO.getEmpDOs();
	}
}
