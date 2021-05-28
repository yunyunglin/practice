package org.tutorial.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.model.EmpDO;
import org.tutorial.repository.EmpRepository;
import org.tutorial.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	private EmpRepository empRepository;

	@Override
	@Transactional
	public EmpDO addEmp(EmpDO empDO) {
		return empRepository.save(empDO);
	}

	@Override
	@Transactional
	public EmpDO updateEmp(EmpDO empDO) {
		return empRepository.save(empDO);
	}

	@Override
	@Transactional
	public void deleteEmp(Integer empno) {
		empRepository.deleteById(empno);
	}

	//findById以EAGER方式取得資料，會一同查出關聯表格資訊(return型別Optional)
	//getOne是延遲加載，不主動找尋關聯表格
	//findOne
	@Override
	public Optional<EmpDO> getOneEmp(Integer empno) {
		return empRepository.findById(empno);
	}

	@Override
	public List<EmpDO> getAll() {
		return empRepository.findAll();
	}

}
