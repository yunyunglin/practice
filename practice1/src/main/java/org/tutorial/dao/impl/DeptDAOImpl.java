package org.tutorial.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

public class DeptDAOImpl implements DeptDAO {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:33060/practice";
    String userid = "root";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO dept2 (dname,loc) VALUES (?, ?)";
    private static final String GET_ALL_STMT = "SELECT deptno , dname, loc FROM dept2";
    private static final String GET_ONE_STMT = "SELECT deptno , dname, loc FROM dept2 where deptno = ?";
    private static final String GET_Emps_ByDeptno_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";

    private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
    private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";

    private static final String UPDATE = "UPDATE dept2 set dname=?, loc=? where deptno = ?";

    @Override
    public void insert(DeptDO deptDO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, deptDO.getDname());
            pstmt.setString(2, deptDO.getLoc());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void update(DeptDO deptDO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, deptDO.getDname());
            pstmt.setString(2, deptDO.getLoc());
            pstmt.setInt(3, deptDO.getDeptno());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void delete(Integer deptno) {
        int updateCount_EMPs = 0;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            // 1.閮剖� pstm.executeUpdate()銋��
            con.setAutoCommit(false);

            // ����撌�
            pstmt = con.prepareStatement(DELETE_EMPs);
            pstmt.setInt(1, deptno);
            updateCount_EMPs = pstmt.executeUpdate();
            // ������
            pstmt = con.prepareStatement(DELETE_DEPT);
            pstmt.setInt(1, deptno);
            pstmt.executeUpdate();

            // 2.閮剖� pstm.executeUpdate()銋��
            con.commit();
            con.setAutoCommit(true);
            System.out.println("�����蝺刻��" + deptno + "���,���撌�" + updateCount_EMPs
                    + "鈭箏��◤��");

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3.閮剖����xception�����atch��憛
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. "
                            + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {

        DeptDO deptDO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, deptno);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // deptDO 銋迂� Domain objects
                deptDO = new DeptDO();
                deptDO.setDeptno(rs.getInt("deptno"));
                deptDO.setDname(rs.getString("dname"));
                deptDO.setLoc(rs.getString("loc"));
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return deptDO;
    }

    @Override
    public List<DeptDO> getAll() {
        List<DeptDO> list = new ArrayList<>();
        DeptDO deptDO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                deptDO = new DeptDO();
                deptDO.setDeptno(rs.getInt("deptno"));
                deptDO.setDname(rs.getString("dname"));
                deptDO.setLoc(rs.getString("loc"));
                list.add(deptDO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        List<EmpDO> list = new ArrayList<>();
        EmpDO empDO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empDO = new EmpDO();
                empDO.setEmpno(rs.getInt("empno"));
                empDO.setEname(rs.getString("ename"));
                empDO.setJob(rs.getString("job"));
                empDO.setHiredate(rs.getDate("hiredate").toLocalDate());
                empDO.setSal(rs.getDouble("sal"));
                empDO.setComm(rs.getDouble("comm"));
                empDO.setDeptno(rs.getInt("deptno"));
                list.add(empDO); // Store the row in the vector
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }
    
    public static void main(String[] args) {

        DeptDAO dao = new DeptDAOImpl();

        // �憓�
//		DeptDO deptDO1 = new DeptDO();
//		deptDO1.setDname("鋆賡�");
//		deptDO1.setLoc("蝢���ㄞ");
//		dao.insert(deptDO1);

        // 靽格
//		DeptDO deptDO2 = new DeptDO();
//		deptDO2.setDeptno(10);
//		deptDO2.setDname("鞎∪�2");
//		deptDO2.setLoc("������2");
//		dao.update(deptDO2);

        // ��
//		dao.delete(30);

        // �閰�
		DeptDO deptDO3 = dao.findByPrimaryKey(2);
		System.out.print(deptDO3.getDeptno() + ",");
		System.out.print(deptDO3.getDname() + ",");
		System.out.println(deptDO3.getLoc());
		System.out.println("---------------------");

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