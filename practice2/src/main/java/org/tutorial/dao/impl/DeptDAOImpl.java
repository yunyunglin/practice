package org.tutorial.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

import jpa.util.JPAUtil;

public class DeptDAOImpl implements DeptDAO {
    
    private static final String GET_ALL_STMT = "FROM DeptDO";
    private static final String GET_Emps_ByDeptno_STMT = "FROM EmpDO where deptno = :deptno";
    
    @Override
    public void insert(DeptDO deptDO) {

        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();
        
        try {

            eTransaction.begin();
            eManager.persist(deptDO);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (Exception e) {
        	
        	eTransaction.rollback();
        	e.printStackTrace();
        	
        } 

    }

    @Override
    public void update(DeptDO deptDO) {

        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {
        	
        	eTransaction.begin();
        	eManager.merge(deptDO);
        	eTransaction.commit();
        	eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {
        	
        	eTransaction.rollback();
        	re.printStackTrace();
            
        }

    }

    @Override
    public void delete(Integer deptno) {

    	EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    	EntityTransaction eTransaction = eManager.getTransaction();
    	
        try {

            eTransaction.begin();
            DeptDO deptDO = eManager.find(DeptDO.class, deptno);
            eManager.remove(deptDO);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {
            
        	eTransaction.rollback();
        	re.printStackTrace();
        	
        } 

    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {

        DeptDO deptDO = null;
        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();
        
        try {

            eTransaction.begin();
            deptDO = eManager.find(DeptDO.class, deptno);
            System.out.println(deptDO.getDname());

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        	
        } 
        return deptDO;
    }

    @Override
    public List<DeptDO> getAll() {
        List<DeptDO> list = new ArrayList<>();

        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            Query query = eManager.createQuery(GET_ALL_STMT ,DeptDO.class);
            list = query.getResultList();
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        	
        } 
        return list;
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        List<EmpDO> list = new ArrayList<>();
        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            Query query = eManager.createQuery(GET_Emps_ByDeptno_STMT);
            list = query.setParameter("deptno", deptno).getResultList();
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        	
        } 
        return list;
    }
    
    public static void main(String[] args) {

        DeptDAO dao = new DeptDAOImpl();

        DeptDO deptDO = new DeptDO();
        deptDO.setDname("製造部");
        deptDO.setLoc("美國洛杉磯");
        dao.insert(deptDO);
        // 靽格
//		DeptDO deptDO2 = new DeptDO();
//		deptDO2.setDeptno(10);
//		deptDO2.setDname("鞎∪�2");
//		deptDO2.setLoc("������2");
//		dao.update(deptDO2);

        // ��
//		dao.delete(30);

        // �閰�
//		DeptDO deptDO3 = dao.findByPrimaryKey(2);
//		System.out.print(deptDO3.getDeptno() + ",");
//		System.out.print(deptDO3.getDname() + ",");
//		System.out.println(deptDO3.getLoc());
//		System.out.println("---------------------");

        // �閰ａ��
//        List<DeptDO> list = dao.getAll();
//        for (DeptDO deptDO : list) {
//            System.out.print(deptDO.getDeptno() + ",");
//            System.out.print(deptDO.getDname() + ",");
//            System.out.print(deptDO.getLoc());
//            System.out.println();
//        }

        // �閰Ｘ�����撌�
//		List<EmpDO> list = dao.getEmpsByDeptno(10);
//		for (EmpDO empDO : list) {
//			System.out.print(empDO.getEmpno() + ",");
//			System.out.print(empDO.getEname() + ",");
//			System.out.print(empDO.getJob() + ",");
//			System.out.print(empDO.getHiredate() + ",");
//			System.out.print(empDO.getSal() + ",");
//			System.out.print(empDO.getComm() + ",");
//			System.out.print(empDO.getDeptno());
//			System.out.println();
//		}
    }

}
