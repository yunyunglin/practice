package org.tutorial.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeptDO implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
}
