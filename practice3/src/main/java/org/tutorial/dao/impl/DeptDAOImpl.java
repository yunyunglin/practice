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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

@Repository
public class DeptDAOImpl implements DeptDAO {
    
	@PersistenceContext
    protected EntityManager entityManager;
    
    @Override
    public void insert(DeptDO deptDO) {
    	entityManager.persist(deptDO);
    }

    @Override
    public void update(DeptDO deptDO) {	//要再看一下!
    	entityManager.merge(deptDO);
    }

    @Override
    public void delete(Integer deptno) {
    	DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
    	entityManager.remove(deptDO);
    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {
    	DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
        return deptDO;
    }

    @Override
    public List<DeptDO> getAll() {
        Query query = entityManager.createQuery("FROM DeptDO", DeptDO.class);
        List<DeptDO> list = query.getResultList();
        return list;
    }

    @Override	//要再看一下
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
    	TypedQuery<DeptDO> query = entityManager.createQuery("SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno", DeptDO.class);
        query.setParameter("deptno", deptno);
        DeptDO deptDO = query.getSingleResult();
        return deptDO.getEmpDOs();
    }
    

}
