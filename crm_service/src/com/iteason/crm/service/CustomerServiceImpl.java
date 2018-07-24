package com.iteason.crm.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import comiteason.crm.domain.Customer;
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

	//查询未关联到定区的客户
		public List<Customer> findListNotAssociation() {
			String sql = "select * from t_customer where decidedzone_id is null";
			List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
				public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
					int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
					String name = rs.getString("name");
					String station = rs.getString("station");
					String telephone = rs.getString("telephone");
					String address = rs.getString("address");
					String decidedzone_id = rs.getString("decidedzone_id");
					return new Customer(id, name, station, telephone, address, decidedzone_id);
				}
			});
			return list;
		}
		
		//查询已经关联到指定定区的客户
		public List<Customer> findListHasAssociation(String decidedzoneId) {
			String sql = "select * from t_customer where decidedzone_id = ?";
			List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
				public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
					int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
					String name = rs.getString("name");
					String station = rs.getString("station");
					String telephone = rs.getString("telephone");
					String address = rs.getString("address");
					String decidedzone_id = rs.getString("decidedzone_id");
					return new Customer(id, name, station, telephone, address, decidedzone_id);
				}
			},decidedzoneId);
			return list;
		}

		//定区关联客户
		public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds) {
			//先将已经和定区关联的用户置空
			String sql = "update t_customer set decidedzone_id = null where decidedzone_id = ?";
			jdbcTemplate.update(sql, decidedzoneId);
			//根据所选项，重新给用户添加定区
			sql = "update t_customer set decidedzone_id = ? where id = ?";
			for (Integer id : customerIds) {
				jdbcTemplate.update(sql, decidedzoneId,id);
			}
		}

}
