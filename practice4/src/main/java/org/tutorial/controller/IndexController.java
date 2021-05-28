package org.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;

@Controller
public class IndexController {
	
	@Autowired
	private DeptService deptSvc;
	
	@Autowired
	private EmpService empSvc;
	
	@RequestMapping(method = RequestMethod.GET, value={"/", "/index"})
    public String getEmpDOsAndDeptDOs(Model model){
        List<DeptDO> deptDOs = deptSvc.getAll();
        List<EmpDO> empDOs = empSvc.getAll();
        model.addAttribute("deptDOs", deptDOs);
        model.addAttribute("empDOs", empDOs);

        return "index";
    }

}
