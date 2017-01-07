package org.bb.cbs.soap;

import org.bb.cbs.core.service.IEmployeeService;
import org.bb.cbs.core.vo.EmployeeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collections;
import java.util.List;

/**
 * Created by newton on 1/7/17.
 */

@Service("employeeWSService")
@WebService(serviceName = "employeeWebService")
public class EmployeeWebService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeWebService.class);

    @Autowired
    private IEmployeeService employeeService;

    @WebMethod(operationName = "listEmployees")
    public List<EmployeeData> getEmployeeList() {
        List<EmployeeData> employees = Collections.EMPTY_LIST;
        try {
            employees =  this.employeeService.getEmployees();
        } catch (Exception e) {
            log.error("Error on getting the list of employees", e);
        }
        return employees;
    }

    @WebMethod(operationName = "updateEmployee")
    public EmployeeData updateEmployee(@WebParam EmployeeData employee) {
        EmployeeData empResponse = null;
        try {
            empResponse = this.employeeService.updateEmployee(employee);
        } catch (Exception e) {
            log.error("Error on creating employee record", e);
        }
        return empResponse;
    }

}
