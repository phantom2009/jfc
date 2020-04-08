package jfc.reflection;

import java.lang.reflect.Method;

public class ClassLoaderStudy {

	public static void main(String[] args) {
		
		MyClassLoader classLoader = new MyClassLoader("d:\\temp\\UserInfo.class");
		try {
			Class clazz=classLoader.findClass("jfc.entities.UserInfo");
			
			//���ﲻӦ����Object,Ӧ��ʹ�ýӿ�����������ֱ������
			Object object=clazz.newInstance();
			
			Method method=clazz.getDeclaredMethod("setName", String.class);
			
			method.invoke(object, "admin");
			
			System.out.println(object);
			
		} catch (Exception ex) {		
			ex.printStackTrace();
		}
		
	}

}
