package com.direction.core.modules.sys.employee.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.direction.common.result.ResultJson;
import com.direction.core.inf.UserProfileUtils;
import com.direction.core.inf.sys.user.IUserInfo;
import com.direction.core.modules.sys.employee.entity.Employee;
import com.direction.core.modules.sys.employee.entity.Employee.EmployeeStatus;
import com.direction.core.modules.sys.employee.service.EmployeeService;
import com.direction.core.modules.sys.user.entity.User;
import com.direction.core.modules.sys.user.service.UserService;
import com.direction.spring.mvc.controller.BaseController;
import com.direction.spring.mvc.page.VuePage;

// ~ File Information
// ====================================================================================================================

/**
 * 员工信息控制层.
 * 
 * <pre>
 * 	员工信息控制层
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
@RestController
@RequestMapping("/core/sys/emp")
public class EmployeeController extends BaseController {

	// ~ Static Fields
	// ==================================================================================================================

	// ~ Fields
	// ==================================================================================================================
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;

	// ~ Constructors
	// ==================================================================================================================

	// ~ Methods
	// ==================================================================================================================

	/**
	 * 分页查询.
	 * 
	 * @param employee
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public VuePage<Employee> list(Employee employee, VuePage<Employee> page) {
		
		if (employee != null && StringUtils.isBlank(employee.getTenantId())) {
			employee.setTenantId(UserProfileUtils.getTenantId());
		}

		return this.employeeService.findPage(employee, page);
	}

	/**
	 * 保存、修改.
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping("/save")
	public ResultJson save(Employee employee) {
		employeeService.save(employee);
		return success();
	}
	
	/**
	 * 开通账号.
	 * 
	 * @param openAccount
	 * @param empId
	 * @param user
	 * @return
	 */
	@RequestMapping("/openAccount")
	public ResultJson openAccount(boolean openAccount, User user) {
		
		this.employeeService.openAccount(openAccount, user);
		
		return success();
	}
	
	/**
	 * 获取员工信息.
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping("/get")
	public Employee get(String empId) {
		
		Employee emp = null;
		
		if (StringUtils.isNotBlank(empId)) {
			emp = this.employeeService.get(empId);
		}
		
		if (emp == null) {
			emp = new Employee();
			emp.setStatus(EmployeeStatus.IN_SERVICE);
			emp.setSex("M");
			emp.setOpenAccount(false);
		}
		
		return emp;
	}
	
	/**
	 * 获取账号信息.
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping("/getAccount")
	public User getAccount(String empId) {
		
		User user = null;
		
		if (StringUtils.isNotBlank(empId)) {
			user = this.userService.getUserByEmp(empId);
		}
		
		// 获取用户
		if (user == null) {
			user = new User();
			user.setAllowPC(true);
			user.setStatus(IUserInfo.UserStatus.ENABLE);
			
			// 获取员工信息
			Employee emp = this.employeeService.get(empId);
			if (emp != null) {
				user.setUserName(emp.getTelephone());
			}
		}
		
		return user;
	}
	
	/**
	 * 检核属性是否重复.
	 * 
	 * @param propName
	 * @param propValue
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/onlyOne")
	public ResultJson validateOnlyOne(String propName, String propValue, String empId) {
		return this.employeeService.validateOnlyOne(propName, propValue, empId);
	}

	/**
	 * 删除员工信息.
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultJson delete(String empId) {

		employeeService.delete(empId);
		
		return success();
	}
}
