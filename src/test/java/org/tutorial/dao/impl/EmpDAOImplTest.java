package org.tutorial.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;

public class EmpDAOImplTest {
	
	public static EmpDAO dao;
	
	@BeforeClass
	public static void init() {
		dao = new EmpDAOImpl();
	}
	
	@Test
	public void insert() {
		EmpDO empDO = new EmpDO();
		empDO.setEname("王小明1");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse(("2020-04-01")));
        empDO.setSal(new Double(50000));
        empDO.setComm(new Double(500));
        empDO.setDeptno(10);
        dao.insert(empDO);
	}
	
	@Test
	public void update() {
		EmpDO empDO = new EmpDO();
		empDO.setEmpno(2);
		empDO.setEname("王小明2");
		empDO.setJob("manager2");
		empDO.setHiredate(LocalDate.parse(("2020-04-01")));
		empDO.setSal(new Double(20000));
		empDO.setComm(new Double(200));
		empDO.setDeptno(20);
		dao.update(empDO);
	}
	
	@Test
	public void delete() {
		dao.delete(14);
	}
	
	@Test
	public void findByPrimaryKey() {
		EmpDO empDO = dao.findByPrimaryKey(1);
		System.out.print(empDO.getEmpno() + ",");
		System.out.print(empDO.getEname() + ",");
		System.out.print(empDO.getJob() + ",");
		System.out.print(empDO.getHiredate() + ",");
      	System.out.print(empDO.getSal() + ",");
      	System.out.print(empDO.getComm() + ",");
      	System.out.println(empDO.getDeptno());
      	System.out.println("---------------------");
	}
	
	@Test
	public void getAll() {
		 List<EmpDO> list = dao.getAll();
	        for (EmpDO empDO : list) {
	            System.out.print(empDO.getEmpno() + ",");
	            System.out.print(empDO.getEname() + ",");
	            System.out.print(empDO.getJob() + ",");
	            System.out.print(empDO.getHiredate() + ",");
	            System.out.print(empDO.getSal() + ",");
	            System.out.print(empDO.getComm() + ",");
	            System.out.print(empDO.getDeptno());
	            System.out.println();
	        }
	}

}
