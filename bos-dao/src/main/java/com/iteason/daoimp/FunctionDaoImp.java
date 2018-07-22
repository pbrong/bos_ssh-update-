package com.iteason.daoimp;




import java.util.List;

import org.springframework.stereotype.Repository;

import com.iteason.dao.FunctionDao;
import com.iteason.domain.Function;
@Repository
public class FunctionDaoImp extends BaseDaoImp<Function> implements FunctionDao {
  
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:返回combotree数据
	 * @date: 2018年7月20日 下午1:13:58
	 * @return
	 */
	public List<Function> findAll(){
	  String hql = "from Function f where f.parentFunction.id is null";
	  List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
	  return list;
  }

	@Override
	public List<Function> findAllMenu() {
		//查询出所有的权限
		String hql = "from Function f where f.parentFunction.id is null";
		return (List<Function>) getHibernateTemplate().find(hql);
	}


	@Override
	public List<Function> findMenuByUserId(String id) {
		// 通过id查询function
		return null;
	}
}
