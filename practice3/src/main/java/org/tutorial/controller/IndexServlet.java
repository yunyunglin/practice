package org.tutorial.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;
import org.tutorial.service.impl.DeptServiceImpl;
import org.tutorial.service.impl.EmpServiceImpl;

@Controller	
public class IndexServlet {
	
	@Autowired
	private DeptService deptSvc;
	
	@Autowired
	private EmpService empSvc;
	
	@RequestMapping(method = RequestMethod.GET, value={"/", "/index"})
    public String getEmpDOsAndDeptDOs(Model model){
        List<DeptDO> deptDOs = deptSvc.getAll();
        model.addAttribute("deptDOs", deptDOs);
        System.out.println(deptDOs.get(0).getDname());

        List<EmpDO> empDOs = empSvc.getAll();
        model.addAttribute("empDOs", empDOs);

        return "index";
    }

}
