package com.wxc.shiro.test.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SecondRealm] doGetAuthenticationInfo");
		//1. 把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//2. 从UsernamePasswordToken中来获取username
		String username=upToken.getUsername();
		//3. 调用数据库的方法，从数据库中查询username对应的用户记录
		System.out.println("从数据库中获取username: "+username+" 所对应的用户信息.");
		//4. 若用户不存在，则可以抛出UnknownAccountException异常
		if("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在！");
		}
		//5. 根据信息的情况，决定是否需要抛出其他的AuthenticationException
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		
		return null;
	}

}
