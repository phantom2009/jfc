package jfc.reflection;

import org.junit.Test;

import jfc.entities.UserInfo;

public class ReflectionStudy {

	/**
	 * ��û��ʹ�÷���֮ǰ����һ�����·�ʽʹ���ࣺ�������ԡ���ȡֵ�Ȳ���
	 */
	@Test
	public void test01() {
		UserInfo userInfo=new UserInfo();
		userInfo.setName("admin");
		System.out.println(userInfo.getName());
	}
	
	/**
	 * ��ȡ�������������Class����
	 */
	public void test02() {
		
	}
}
