package org.tutorial.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

import jpa.util.JPAUtil;

public class EmpDAOImpl implements EmpDAO {
   

    private static final String INSERT_STMT = "INSERT INTO emp2 (ename,job,hiredate,sal,comm,deptno) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "FROM EmpDO order by empno";
    private static final String GET_ONE_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where empno = ?";
    private static final String DELETE = "DELETE FROM emp2 where empno = ?";
    private static final String UPDATE = "UPDATE emp2 set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

    @Override
    public void insert(EmpDO empDO) {

        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            eManager.persist(empDO);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        } 

    }

    @Override
    public void update(EmpDO empDO) {

    	EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

        	eTransaction.begin();
            eManager.merge(empDO);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        } 

    }

    @Override
    public void delete(Integer empno) {

    	EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            EmpDO empDO = eManager.find(EmpDO.class, empno);
            eManager.remove(empDO);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        } 

    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {

        EmpDO empDO = null;
        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

            eTransaction.begin();
            eManager.find(EmpDO.class, empno);
            eTransaction.commit();
            eManager.close();

            // Handle any driver errors
        } catch (RuntimeException re) {

        	eTransaction.rollback();
        	re.printStackTrace();
        } 
        return empDO;
    }

    @Override
    public List<EmpDO> getAll() {
        List<EmpDO> list = new ArrayList<>();
        EntityManager eManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction eTransaction = eManager.getTransaction();

        try {

        	eTransaction.begin();
            Query query = eManager.createQuery(GET_ALL_STMT);
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
    
    public static void main(String[] args) {

//    	EmpDAO dao = new EmpDAOImpl();
//    	
//    	EmpDO empDO = new EmpDO();
//        empDO.setEname("王小明1");
//        empDO.setJob("manager");
//        empDO.setHiredate(LocalDate.parse("2020-04-01"));
//        empDO.setSal(new Double(50000));
//        empDO.setComm(new Double(500));
//        DeptDO deptDO = new DeptDO();
//        deptDO.setDeptno(10);
//        empDO.setDeptDO(deptDO);
//        dao.insert(empDO);
        
//
//        // 靽格
//        EmpDO empDO2 = new EmpDO();
//        empDO2.setEmpno(7002);
//        empDO2.setEname("�����2");
//        empDO2.setJob("manager2");
//        empDO2.setHiredate(LocalDate.parse(("2020-04-01")));
//        empDO2.setSal(new Double(20000));
//        empDO2.setComm(new Double(200));
//        empDO2.setDeptno(20);
//        dao.update(empDO2);
//
//        // ��
//        dao.delete(7014);
//
//        // �閰�
//        EmpDO empDO3 = dao.findByPrimaryKey(7001);
//        System.out.print(empDO3.getEmpno() + ",");
//        System.out.print(empDO3.getEname() + ",");
//        System.out.print(empDO3.getJob() + ",");
//        System.out.print(empDO3.getHiredate() + ",");
//        System.out.print(empDO3.getSal() + ",");
//        System.out.print(empDO3.getComm() + ",");
//        System.out.println(empDO3.getDeptno());
//        System.out.println("---------------------");

        // �閰�
//        List<EmpDO> list = dao.getAll();
//        for (EmpDO empDO : list) {
//            System.out.print(empDO.getEmpno() + ",");
//            System.out.print(empDO.getEname() + ",");
//            System.out.print(empDO.getJob() + ",");
//            System.out.print(empDO.getHiredate() + ",");
//            System.out.print(empDO.getSal() + ",");
//            System.out.print(empDO.getComm() + ",");
//            System.out.print(empDO.getDeptno());
//            System.out.println();
//        }
    }

}
