package jfc.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * description���Զ����������
 * 		����ApplicationClassLoader�ܼ�����.classpath�ļ���ָ��Ŀ¼�µ��࣬��������������ص���ϵͳ���������
 * 		���Ҫ���ز�����Ŀ�У�.classpath�����˵��ࣩ������Ŀ¼�µı���õ�.class�ļ��������ʹ���Զ����������
 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader{
	
	//"d:\\temp\\UserInfo.class"
	private String classPath;
	
	//���ṩ�޲ι��캯��
	public MyClassLoader(String classPath) { 
		this.classPath = classPath;
	}
	/**
	 * ���ֽ����ļ�.class�����ֽ�������
	 * @return �����ֽ�������
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
	 * ������������ظ��˸���ķ���������д��û�б�Ҫ�����Ի�һ�����֣����ѿ��ܾ��븲��һ�£�
	 * 		�Զ�������������Ի�ȡ��������Դ���ֻ࣬Ҫ.class���ֽ����ļ��ܹ��ṩ�������ɣ�������ǲ鿴Դ����
	 *   	private native Class<?> defineClass1(String name, byte[] b, int off, int len,ProtectionDomain pd, String source);
	 *   	defineClass�ڲ���������defineClass1��native���������ַ�������д��java�����ڣ�һ����ԭ��C++����
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
