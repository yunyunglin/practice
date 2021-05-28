package org.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorial.model.EmpDO;

@Repository	//參數是寫甚麼?
public interface EmpRepository extends JpaRepository<EmpDO, Integer>{
}
