package jfc.reflection;

import java.lang.reflect.Method;
import org.junit.Test;
import jfc.commons.ReflectUtils;
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
	 * 	��ȡ�������������Class����
	 */
	public void test02() {
		
	}
	
	/**
	 * String getName()���ʵ�ڿ�������ʲô�ã���������˵���������ȫ���������������ȫ�������Ҵ��ݽ�ȥ�ġ�
	 */
	@Test
	public void test03() {
		MyClassLoader classLoader = new MyClassLoader("d:\\temp\\UserInfo.class");
		try {
			Class clazz=classLoader.findClass("jfc.entities.UserInfo");
			
			//���ﲻӦ����Object,Ӧ��ʹ�ýӿ�����������ֱ������
			Object object=clazz.newInstance();
			
			System.out.println(clazz.getName());
			
		} catch (Exception ex) {		
			ex.printStackTrace();
		}
		
	} 
	
	@Test
	public void test05() {
		System.out.println(Object.class.getClassLoader());
		System.out.println(ReflectionStudy.class.getClassLoader());
	}
	
	@Test
	public void test06() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz=ClassLoader.getSystemClassLoader().loadClass("jfc.entities.UserInfo");
		Object object=clazz.newInstance();
		
		//BeanUtils.setProperty(object, "name", "admin");
		//BeanUtils.setProperty(object, "password", "123456");		
		
		ReflectUtils.setFieldValue(object, "name", "admin");
		ReflectUtils.setFieldValue(object, "password", "admin");
		
		System.out.println(object);
	}
}
