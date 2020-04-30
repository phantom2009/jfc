package jfc.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 刚哥写的反射的助手类，我这里已经用不到了
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class ReflectUtils {

	
	/**
	 * 通过反射获取定义Class是声明的父类的泛型参数类型
	 * ��: public EmployeeDao extends BaseDao<Employee, String>
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class getSuperClassGenricType(Class clazz, int index){
		
		//TODO:获取clazz的父类的Type类型，不是Class类型，区别这点非常重要
		Type type = clazz.getGenericSuperclass();
		
		//TODO:泛型参数必须能转换为ParameterizedType,这样表示可能的泛型
		if(!(type instanceof ParameterizedType)){
			return Object.class;
		}
		
		//TODO:上面判断通过表示能转成功，将父类Type类型传化成ParameterizedType
		ParameterizedType parameterizedType=(ParameterizedType) type;
		
		//TODO:通过父类的ParameterizedType类型获取父类实际被继承时候的参数的Type类型，由于泛型参数可能有多个所以是数组
		Type [] params = parameterizedType.getActualTypeArguments();
		
		//TODO:如果没有或者无法获取Class类型都返回Object的Class类型
		if(index >= params.length || index < 0){
			return Object.class;
		}		
		if(!(params[index] instanceof Class)){
			return Object.class;
		}
		
		//TODO:返回用户指定的父类的第几个参数
		return (Class) params[index];
	}
	
	/**
	 * 根据传递的clazz获取继承父类时候第一个泛型参数的类型，感觉这个方法有点多余，他只适合父类有一个泛型参数的情况，如果有两个还是要用上面的自己指定index 
	 *  public EmployeeDao extends BaseDao<Employee, String>
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static<T> Class<T> getSuperGenericType(Class clazz){
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 循环向上转型, 获取对象的 DeclaredMethod
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){
		
		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
			try {
				//superClass.getMethod(methodName, parameterTypes);
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				//Method ���ڵ�ǰ�ඨ��, ��������ת��
			}
			//..
		}
		
		return null;
	}
	
	/**
	 * ʹ filed 使 filed 变为可访问
	 * @param field
	 */
	public static void makeAccessible(Field field){
		if(!Modifier.isPublic(field.getModifiers())){
			field.setAccessible(true);
		}
	}
	
	/**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * @param object
	 * @param filedName
	 * @return
	 */
	public static Field getDeclaredField(Object object, String filedName){
		
		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException e) {
				//Field ���ڵ�ǰ�ඨ��, ��������ת��
			}
		}
		return null;
	}
	
	/**
	 * 直接调用对象方法, 而忽略修饰符(private, protected)
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,
			Object [] parameters) throws InvocationTargetException{
		
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		
		if(method == null){
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		
		method.setAccessible(true);
		
		try {
			return method.invoke(object, parameters);
		} catch(IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		} 
		
		return null;
	}
	
	/**
	 * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
	 * @param object
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(Object object, String fieldName, Object value){
		Field field = getDeclaredField(object, fieldName);
		
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field);
		
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		}
	}
	
	/**
	 * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object object, String fieldName){
		Field field = getDeclaredField(object, fieldName);
		
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field);
		
		Object result = null;
		
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		}
		
		return result;
	}
}
