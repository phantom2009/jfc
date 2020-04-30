package jfc.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

/**
 * 	字节流
 * 		FileInputStream FileOutStream
 * 	字符流
 * 		FileStreamReader FileStreamWriter
 * 	缓冲流
 * 		BufferInputStream BufferOutputStream
 * 		BufferRead	BufferWriter
 * 
 * 	以上是我重点学习测试并且实践中可能用到的，下面这些了解一下，使用广度没那么多
 * 
 * @author Administrator
 *
 */
public class OthersStreamStudy {

	
	/**
	 * 	标准输入输出流
	 * 		java从来没有这种累与api，只是形象的说法，大家也能接受，大部分人说的意思指的是System.in System.out System.err，
	 * 		他们是java.io.PrintStream或者java.ion.inputStream,标准一定是在System类的作用范围才这么叫
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
		 * 第二种读取键盘输入的方法
		 */
		//BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in,"utf-8"));   //读到默认返回null
		//String line=bufferedReader.readLine();
		//System.out.println(line);
		
		/**
		 *	 通过阅读源代码，System.in这个静态成员变量类型是InputStream接口类型,
		 * 
		 * 	这段代码直接输出中文到控制台会乱码，英文没有问题，乱码不是流要考虑的问题
		 */		
				
		PrintStream out=System.out;
		out.print("标准输出流");
	}
	
}
