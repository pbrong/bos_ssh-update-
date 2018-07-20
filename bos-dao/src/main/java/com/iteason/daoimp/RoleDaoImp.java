package com.iteason.daoimp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.iteason.dao.RoleDao;
import com.iteason.domain.Role;
import com.iteason.domain.User;
import com.iteason.utils.PageBean;
@Repository
public class RoleDaoImp extends BaseDaoImp<Role> implements RoleDao {


}
