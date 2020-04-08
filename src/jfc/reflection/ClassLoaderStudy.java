package jfc.reflection;

import java.lang.reflect.Method;

public class ClassLoaderStudy {

	public static void main(String[] args) {
		
		MyClassLoader classLoader = new MyClassLoader("d:\\temp\\UserInfo.class");
		try {
			Class clazz=classLoader.findClass("jfc.entities.UserInfo");
			
			//这里不应该用Object,应该使用接口声明，可以直接依赖
			Object object=clazz.newInstance();
			
			Method method=clazz.getDeclaredMethod("setName", String.class);
			
			method.invoke(object, "admin");
			
			System.out.println(object);
			
		} catch (Exception ex) {		
			ex.printStackTrace();
		}
		
	}

}
