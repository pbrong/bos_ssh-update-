package com.iteason.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.iteason.dao.UserDao;
import com.iteason.domain.User;
/**
 * 
 * @author 阿荣
 * @Description: 认证
 * @date: 2018年7月18日 下午3:13:06
 */
public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	
	/**
	 *认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证方法已经执行");
		//根据用户名查询数据库中的密码，框架负责比对数据库中的密码和页面输入的密码是否一致
		UsernamePasswordToken tk = (UsernamePasswordToken) token;
		//获得用户名
		String username = tk.getUsername();
		//根据用户名查询数据库
		User user = userDao.findUserByUsername(username);
		if(user == null){
			//用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}
	
	/**
	 * 授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection args){
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//添加授权口令
		//TODO 后期查询数据库
		info.addStringPermission("staff-list");
		info.addStringPermission("staff-delete");
		return info;
	}

}
