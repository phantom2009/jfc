package jfc.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * description：自定义类加载器
 * 		由于ApplicationClassLoader能加载在.classpath文件中指定目录下的类，而其他两个类加载的是系统类加载器，
 * 		如果要加载不在项目中（.classpath管理了的类）的任意目录下的编译好的.class文件我们最好使用自定义类加载器
 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader{
	
	//"d:\\temp\\UserInfo.class"
	private String classPath;
	
	//不提供无参构造函数
	public MyClassLoader(String classPath) { 
		this.classPath = classPath;
	}
	/**
	 * 将字节码文件.class读到字节数组中
	 * @return 返回字节流数组
	 * @throws IOException
	 */
	private byte[] loadByte() throws IOException {
		InputStream inputStream = new FileInputStream(this.classPath);
		int len = inputStream.available();
		byte[] bytes = new byte[len];
		inputStream.read(bytes);
		inputStream.close();
		return bytes;
	}
	/**
	 * 这个方法命令重复了父类的方法，这种写法没有必要，可以换一个名字，网友可能就想覆盖一下；
	 * 		自定义类加载器可以获取任意数据源的类，只要.class的字节码文件能够提供出来即可，如果我们查看源代码
	 *   	private native Class<?> defineClass1(String name, byte[] b, int off, int len,ProtectionDomain pd, String source);
	 *   	defineClass内部继续调用defineClass1是native方法，这种方法并不写在java代码内，一般是原生C++方法
	 */
	@Override
	protected Class<?> findClass(String qualifiedName) throws ClassNotFoundException{
		try {
			byte[] data = loadByte();
			return defineClass(qualifiedName, data, 0, data.length);
		} catch (Exception e) {		
			throw new ClassNotFoundException();
		}
	}

}
