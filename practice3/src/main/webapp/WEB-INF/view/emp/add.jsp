<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
    <title>員工資料新增</title>
</head>
<body>
    <h3>員工資料</h3>
    
    <form:form action="insert" method="POST" modelAttribute="empDO">
		<table>

			<tr>
				<td>員工姓名:</td>
				<td>
					<form:input type="text" path="ename" size="45"/>
					<form:errors path="ename"/>
					
				</td>
			</tr>
			<tr>
				<td>職位:</td>
				<td><form:input type="text" path="job" size="45"/></td>
			</tr>
			<tr>
				
				<td>雇用日期:</td>
				<td><form:input type="date" path="hiredate" size="40" /> 
					<form:errors path="hiredate"/> </td>
				    
			</tr>
			<tr>
				<td>薪水:</td>
				<td><form:input type="text" path="sal" id="sal" size="45"/>
					<form:errors path="sal"/>
				
				</td>
			</tr>
			<tr>
				<td>獎金:</td>
				<td>
				<form:input type="text" path="comm" id="comm" size="45"/>
				<form:errors path="comm"/>
				</td>
			</tr>

			<tr>
				<td>部門:</td>
				<td><form:select path="deptDO.deptno" id="deptDO.deptno" items="${deptDOs}" itemValue="deptno" itemLabel="dname" /></td>
			</tr>

		</table><br>
		
		<input type="submit" value="送出新增">
	</form:form>
</body>
</html>
