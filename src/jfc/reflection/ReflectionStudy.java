package jfc.reflection;

import java.lang.reflect.Method;
import org.junit.Test;
import jfc.commons.ReflectUtils;
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
	 * 	获取对象或者类名的Class类型
	 */
	public void test02() {
		
	}
	
	/**
	 * String getName()这个实在看不出有什么用，对于类来说他输出的是全类名，问题是这个全类名是我传递进去的。
	 */
	@Test
	public void test03() {
		MyClassLoader classLoader = new MyClassLoader("d:\\temp\\UserInfo.class");
		try {
			Class clazz=classLoader.findClass("jfc.entities.UserInfo");
			
			//这里不应该用Object,应该使用接口声明，可以直接依赖
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
