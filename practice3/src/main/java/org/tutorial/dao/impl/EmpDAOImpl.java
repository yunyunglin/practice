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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

@Repository
public class EmpDAOImpl implements EmpDAO {
   
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Override
    public void insert(EmpDO empDO) {
    	entityManager.persist(empDO);
    }

    @Override
    public void update(EmpDO empDO) {
    	entityManager.merge(empDO);
    }

    @Override
    public void delete(Integer empno) {
    	EmpDO empDO = entityManager.find(EmpDO.class, empno);
    	entityManager.remove(empDO);
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
    	EmpDO empDO = entityManager.find(EmpDO.class, empno);
        return empDO;
    }

    @Override
    public List<EmpDO> getAll() {
        Query query = entityManager.createQuery("FROM EmpDO order by empno");
        List<EmpDO> list = query.getResultList();
        return list;
    }

}
