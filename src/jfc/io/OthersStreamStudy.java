package jfc.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

/**
 * 	�ֽ���
 * 		FileInputStream FileOutStream
 * 	�ַ���
 * 		FileStreamReader FileStreamWriter
 * 	������
 * 		BufferInputStream BufferOutputStream
 * 		BufferRead	BufferWriter
 * 
 * 	���������ص�ѧϰ���Բ���ʵ���п����õ��ģ�������Щ�˽�һ�£�ʹ�ù��û��ô��
 * 
 * @author Administrator
 *
 */
public class OthersStreamStudy {

	
	/**
	 * 	��׼���������
	 * 		java����û����������api��ֻ�������˵�������Ҳ�ܽ��ܣ��󲿷���˵����˼ָ����System.in System.out System.err��
	 * 		������java.io.PrintStream����java.ion.inputStream,��׼һ������System������÷�Χ����ô��
	 * @throws IOException 
	 */
	@Test
	public void test01() throws IOException {
		
		
		InputStream inputStream=System.in;
		Scanner scanner=new Scanner(inputStream);
		String string=scanner.next();
		System.out.println(string);
		scanner.close();
		
		
		/**
		 * �ڶ��ֶ�ȡ��������ķ���
		 */
		//BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in,"utf-8"));   //����Ĭ�Ϸ���null
		//String line=bufferedReader.readLine();
		//System.out.println(line);
		
		/**
		 *	 ͨ���Ķ�Դ���룬System.in�����̬��Ա����������InputStream�ӿ�����,
		 * 
		 * 	��δ���ֱ��������ĵ�����̨�����룬Ӣ��û�����⣬���벻����Ҫ���ǵ�����
		 */		
				
		PrintStream out=System.out;
		out.print("��׼�����");
	}
	
}
