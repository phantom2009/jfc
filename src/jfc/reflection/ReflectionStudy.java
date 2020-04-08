package jfc.reflection;

import org.junit.Test;

import jfc.entities.UserInfo;

public class ReflectionStudy {

	/**
	 * 在没有使用反射之前我们一般如下方式使用类：设置属性、获取值等操作
	 */
	@Test
	public void test01() {
		UserInfo userInfo=new UserInfo();
		userInfo.setName("admin");
		System.out.println(userInfo.getName());
	}
	
	/**
	 * 获取对象或者类名的Class类型
	 */
	public void test02() {
		
	}
}
