package org.tutorial.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.impl.DeptServiceImpl;

@Controller
public class DeptServlet {
	
	@Autowired
	public DeptService deptSvc;

	@RequestMapping(method = RequestMethod.GET ,value="/dept/listAll")
	public String listAllDept(Model model) {
		setDeptDOsRequestAttribute(model);
		return "dept/listAll";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/dept/listOne")
	public String listOneDept(Integer deptno, Model model) {
		DeptDO deptDO = deptSvc.getOneDept(deptno);
		model.addAttribute("deptDO", deptDO);
		return "dept/listOne";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/dept/listEmps_ByDeptno_A")
	public String listEmpByDeptnoA(Integer deptno, Model model) {
		List<EmpDO> list = deptSvc.getEmpsByDeptno(deptno);
		model.addAttribute("listEmps_ByDeptno", list);
		return "dept/listEmpsByDeptno";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/dept/listEmps_ByDeptno_B/{deptno}")
	public String listEmpByDeptnoB(@PathVariable Integer deptno, Model model) {
//		List<DeptDO> deptDOs = deptSvc.getAll();
		List<EmpDO> empDOs = deptSvc.getEmpsByDeptno(deptno);
		setDeptDOsRequestAttribute(model);
		model.addAttribute("listEmps_ByDeptno", empDOs);
		return "dept/listAll";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/dept/getOne_For_Update_Dept")
    public String getOneForUpdate(Model model, Integer deptno) {
        DeptDO deptDO = deptSvc.getOneDept(deptno);
        model.addAttribute("deptDO", deptDO);
        return "dept/update";
    }

	@RequestMapping(method = RequestMethod.POST, value="/dept/update")
    public String update(Model model, DeptDO deptDO) {
        deptSvc.update(deptDO);
        model.addAttribute("deptDO", deptDO);
        return "dept/listOne";
    }

	@RequestMapping(method = RequestMethod.POST, value="/dept/delete_Dept")
    private String deleteDept(Integer deptno, Model model) {
        deptSvc.deleteDept(deptno);
        setDeptDOsRequestAttribute(model);
        return "dept/listAll";
    }
	
	

    // 查出所有部門存入req，供/dept/listAll.jsp 或 /dept/listEmpsByDeptno.jsp 或 /dept/listAll.jsp 顯示使用
    // 但不推薦這種寫法，因為有 side effect 問題
    private void setDeptDOsRequestAttribute(Model model) {
        List<DeptDO> deptDOs = deptSvc.getAll();
        model.addAttribute("deptDOs", deptDOs);
    }

}