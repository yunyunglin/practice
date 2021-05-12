package org.tutorial.dao.impl;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

public class DeptDAOImplTest {
	
	public static DeptDAO dao;
	
	@BeforeClass
	public static void init() {
		dao = new DeptDAOImpl();
	}
	
	@Test
	public void insert() {
		DeptDO deptDO = new DeptDO();
		deptDO.setDname("製造部");
		deptDO.setLoc("美國洛杉磯");
		dao.insert(deptDO);
	}
	
	@Test
	public void update() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        deptDO.setDname("財務部2");
        deptDO.setLoc("臺灣台北2");
        dao.update(deptDO);
    }
	
	@Test
	public void delete() {
		DeptDO deptDO = new DeptDO();
		dao.delete(30);
	}
	
	@Test
	public void findByPrimaryKey() {
		DeptDO deptDO = dao.findByPrimaryKey(20);
		System.out.println(deptDO.getDeptno());
		System.out.print(deptDO.getDname() + ",");
		System.out.println(deptDO.getLoc());
		System.out.println("---------------------");
	}
	
	@Test
	public void listAll() {
		List<DeptDO> list = dao.getAll();
		for (DeptDO deptDO : list) {
			System.out.print(deptDO.getDeptno() + ",");
			System.out.print(deptDO.getDname() + ",");
			System.out.print(deptDO.getLoc());
			System.out.println();
		}
		
	}
	
	 @Test
	    public void getEmpsByDeptno() {
	        List<EmpDO> list = dao.getEmpsByDeptno(10);
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
