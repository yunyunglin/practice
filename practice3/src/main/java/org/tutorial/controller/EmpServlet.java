package org.tutorial.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;
import org.tutorial.service.impl.DeptServiceImpl;
import org.tutorial.service.impl.EmpServiceImpl;

@Controller
public class EmpServlet {
	
	@Autowired
	public EmpService empSvc;
	
	@Autowired
	public DeptService deptSvc;
	
	@RequestMapping(method = RequestMethod.GET, value = "/emp/addEmp")
	public String addEmp(Model model) {
		setDeptDOsRequestAttribute(model);
		EmpDO empDO = new EmpDO();
		model.addAttribute("empDO", empDO);
		return "emp/add";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/emp/listAllEmp")
	public String listEmp(Model model) {
		setEmpDOsRequestAttribute(model);
		return "emp/listAll";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/listOneEmp")
	public String listOneEmp(Integer empno, Model model) {
		EmpDO empDO = empSvc.getOneEmp(empno);
		model.addAttribute("empDO", empDO);
		return "emp/listOne";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/insert")
	public String insert(@Valid EmpDO empDO , BindingResult result, Model model) {

		/*接收請求參數 - 輸入格式的錯誤處理*/
		if (result.hasErrors()) {
			return "emp/add";
		}
		empSvc.addEmp(empDO);
		setEmpDOsRequestAttribute(model);
		return "emp/listAll"; // 新增成功後轉交listAllEmp.jsp
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/getOne_For_Update")
	public String getOne_For_Update(@RequestParam("empno") Integer empno1, Model model) {
		EmpDO empDO = empSvc.getOneEmp(empno1);
		setDeptDOsRequestAttribute(model);
		
		model.addAttribute("empDO", empDO);
		return "emp/update"; 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/update")
	public String update(EmpDO empDO, Model model) {

		empSvc.updateEmp(empDO);
		model.addAttribute(empDO);
		return "emp/listOne";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/delete")
	public String delete(Integer empno, Model model) {
		empSvc.deleteEmp(empno);
		setEmpDOsRequestAttribute(model);
		return "emp/listAll";
	}
	
	
	private void setEmpDOsRequestAttribute(Model model) {
        List<EmpDO> empDOs = empSvc.getAll();
        System.out.println(empDOs.get(0).getEname());
        model.addAttribute("empDOs", empDOs);
    }
	
	 // 查出所有部門存入req，供 /emp/add.jsp 或 /emp/update.jsp 畫面顯示使用
    private void setDeptDOsRequestAttribute(Model model) {
        List<DeptDO> deptDOs = deptSvc.getAll();
        model.addAttribute("deptDOs", deptDOs);
    }

    
}
    