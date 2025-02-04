package com.kh.jdbc.day04.pstmt.employee.service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeService {
//	private JDBCTemplate jdbcTemplate;
	private EmployeeDAO eDao;
	
	public EmployeeService() {
//		jdbcTemplate = new JDBCTemplate();
		eDao = new EmployeeDAO();
	}
	
	public List<Employee> selectList() {
		Connection conn = null;
		List<Employee> eList = null;
		try {
			conn = JDBCTemplate.getConnection();
			eList = eDao.selectList(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}
	
	public int insertEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.insertEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmployee(String empId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.deleteEmployee(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public Employee selectOne(String empId) {
		Connection conn = null;
		Employee rset = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			rset = eDao.selectOne(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rset;
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.updateEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}



























