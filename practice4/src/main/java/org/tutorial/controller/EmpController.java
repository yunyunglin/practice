package org.tutorial.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	public EmpService empSvc;
	
	@Autowired
	public DeptService deptSvc;
	
	@RequestMapping(method = RequestMethod.GET, value = "/emp/add")
	public String addEmpPage(Model model) {
		
		EmpDO empDO = new EmpDO();			
		//	要先給empDO以及預設值
		empDO.setEname("王小明");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse("2020-04-01"));
        empDO.setSal(10000.0);
        empDO.setComm(100.0);
		model.addAttribute("empDO", empDO);
		setDeptDOsRequestAttribute(model);	//方法內有在model.addAttribute(DeptDOs)
		return "emp/add";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/insert")
	//	錯誤處理的BindingResult一定要寫在@Valid後面
	public String insert(@Valid EmpDO empDO , BindingResult result, Model model) {
		if (result.hasErrors()) {
			setDeptDOsRequestAttribute(model);
			return "emp/add";
		}
		empSvc.addEmp(empDO);
		setEmpDOsRequestAttribute(model);
		return "emp/listAll"; // 為什麼範例是用redirect
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/getOne_For_Update")
	//	RequestParam括弧對應的是DO屬性，後面參數對應方法內使用名稱
	public String getOneUpdatePage(@RequestParam("empno") Integer empno1, Model model) {	
		Optional<EmpDO> optional = empSvc.getOneEmp(empno1);
		if(optional.isPresent()) {
			EmpDO empDO = optional.get();	//optional需要在get才會拿出原本的型別
			model.addAttribute("empDO", empDO);
		}
		setDeptDOsRequestAttribute(model);
		return "emp/update"; 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/update")
	public String update(EmpDO empDO, Model model) {
		EmpDO updatedEmp = empSvc.updateEmp(empDO);
		model.addAttribute("empDO", updatedEmp);
		return "emp/listOne";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/emp/delete")
	public String delete(Integer empno, Model model) {
		empSvc.deleteEmp(empno);
		setEmpDOsRequestAttribute(model);
		return "emp/listAll";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/emp/getOne_For_Display")
	public String listOneEmp(@RequestParam Integer empno, Model model) {
		Optional<EmpDO> optional = empSvc.getOneEmp(empno);
		if(optional.isPresent()) {
			EmpDO empDO = optional.get();
			model.addAttribute("empDO", empDO);
		}
		return "emp/listOne";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/emp/listAllEmp")
	public String listEmp(Model model) {
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
