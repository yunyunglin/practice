<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
    <title>員工資料修改</title>
</head>
<body>
    <h3>員工資料修改</h3>
    
    <form:form method="POST" action="${pageContext.request.contextPath}/emp/update" modelAttribute="empDO">
        <table>
            <tr>
                <td>員工編號:<font color=red><b>*</b></font></td>
                <td>${empDO.empno}</td>
            </tr>
            <tr>
                <td>員工姓名:</td>
                <td>
                    <form:input type="TEXT" path="ename" size="45" value="${empDO.ename}" />
                </td>
            </tr>
            <tr>
                <td>職位:</td>
                <td>
                    <form:input type="TEXT" path="job" size="45"	value="${empDO.job}" />
                </td>
            </tr>
            <tr>
                <td>雇用日期:</td>
                <td>
                    <form:input type="date" path="hiredate" value="${empDO.hiredate}" />
                </td>
            </tr>
            <tr>
                <td>薪水:</td>
                <td>
                    <form:input type="TEXT" path="sal" size="45" value="${empDO.sal}" />
                </td>
            </tr>
            <tr>
                <td>獎金:</td>
                <td>
                    <form:input type="TEXT" path="comm" size="45" value="${empDO.comm}" />
                </td>
            </tr>
            <tr>
                <td>部門:<font color=red><b>*</b></font></td>
                <td>
                    <form:select path="deptDO.deptno" id="deptDO.deptno" items="${deptDOs}" itemValue="deptno" itemLabel="dname" />
                        
                    
                </td>
            </tr>
        </table>
        <br />
        <input type="hidden" name="empno" value="${empDO.empno}">
        <a href="${pageContext.request.contextPath}/index">回首頁</a>
        <input type="submit" value="送出修改">
    </form:form>
</body>
</html>
