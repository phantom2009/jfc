package jfc.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.junit.Test;

import jfc.commons.UnicodeReader;

/**
 * 	我之前在手册中说明了，这两个类重要的接口是Reader和Writer，我们能看得见、摸的着，与Stream有很大的区别
 * 	@author Administrator
 *
 */
public class Reader2WriterStudy {

	@Test
	public void test01() throws Exception {
		Reader reader=new FileReader("d:\\temp4\\demo02.txt");
		char[] chars=new char[10];              //这就是Reader最为强大的地方，我们一次读10个字符，对于abc ABC  123  中国等都是一样的
		int len=0;
		while(len!=-1) {
			System.out.println(new String(chars,0,len));			
			len=reader.read(chars);
		}
		reader.close();
	}
	
	/**
	 * 	纯FileReader没有什么用，java.io跟我看了一个玩笑，读取中文是要指定编码的，FileReader没有api能设定这个，test02实在默认ansi编码下读取成功的
	 * @throws IOException 
	 */
	@Test
	public void test02() throws IOException {
		File file=new File("d:\\temp4\\ddd.txt");
		Reader reader=new InputStreamReader(new FileInputStream(file),"utf-8");
		
		char[] chars=new char[(int)file.length()];
		
		reader.read(chars);
		reader.close();
		
		String content=new String(chars);
		
		System.out.println("char方式一全部读取：\r\n"+content);
	}
	
	/**
	 * test02出现一个奇特的结果，因为demo.txt我手动用记事本写，默认用ansi编码，我们直接用window操作系统打开操作，然后读取，这样输出结果默认多了一个
	 * 		不可见的字符，在控制台以?开头输出文本。
	 * 
	 * 	java.io，就是我们后面马上要学习的OutputStreamWriter也具有写入文件的能力，通过他写入的文件用test02读我现在先说出结果是没有问题的
	 * 		那这两个utf-8文件有声明区别呢？
	 *  	通过上面的几篇文章应该可以想到是Java读取BOM（Byte Order　Mark）的问题，在使用UTF-8时，可以在文件的开始使用3个字节的"EF BB BF"来标识文件使用了UTF-8的编码，
	 * 		当然也可以不用这个3个字节。上面的问题应该就是因为对开头3个字节的读取导致的。开始不太相信这个是JDK的Bug，后来在多次试验后，问题依然存在，
	 * 		就又狗狗了一下，果然找到一个如下的Bug:不过在我关掉的一些页面中记得有篇文件说这个bug只在jdk1.5及之前的版本才有，说是1.6已经解决了，
	 * 		从目前来看1.6只是解决了读取带有BOM文件失败的问题，还是不能区别处理有BOM和无BOM的UTF-8编码的文件，从Bug ID:4508058里的描述可以看出，
	 * 		这个问题将作为一个不会修改的问题关闭，对于BOM编码的识别将由应用程序自己来处理，原因可从另处一个bug处查看到，因为Unicode对于BOM的编码的规定可能发生变化。
	 * 		也就是说对于一个UTF-8的文件，应用程序需要知道这个文件有没有写BOM，然后自己决定处理BOM的方式。
	 * 
	 * 	解决办法1：使用工具转换文本文件，比如notepad++可以将记事本文件转换问“无 BOM”格式，这种解决办法好像不太好；
	 * 	解决办法2：封装一个工具类处理；
	 */
	@Test
	public void test04() throws IOException {
		File file=new File("d:\\temp4\\demo03.txt");		
		BufferedReader br = new BufferedReader(new UnicodeReader(new FileInputStream(file), Charset.defaultCharset().name())); 
		
		String line=br.readLine();
		while(line != null){
				System.out.println(line);
				line=br.readLine();
		}
		br.close();
	}
	
	/**
	 * 	日了狗了，test04执行之后，test02的问题不可重现了，难道是Charset.defaultCharset().name()的原因改了虚拟机设置？
	 * 		我们继续学习吧，缓冲读取
	 * @throws IOException 
	 */
	@Test
	public void test05() throws IOException {
		File file=new File("d:\\temp4\\sky.txt");
		Reader reader=new InputStreamReader(new FileInputStream(file),"utf-8");		
		char[] chars=new char[10];
		int len=0;
		while(len!=-1) {
			reader.read(chars);
			System.out.println(new String(chars));
			len=reader.read(chars);
		}
		reader.close();
	}
	
	
	/**
	 * test05的缓冲读取和字节流操作时候的缓冲流读取时一样的，对本地文件或者提交小的文件是一样的甚至性能更高。而超大文件、网络文件等等还是要用到
	 * 		专业的缓冲流，而缓冲流提高性能的机制之前已经说过啦，下面我们综合测试一下字符流操作+缓冲操作。
	 * @throws IOException 
	 */
	@Test
	public void test07() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("d:\\temp4\\ddd.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("d:\\temp4\\ddd2.txt"));
		String str=bufferedReader.readLine();
		while(str!=null) {
			//System.out.println(str);
			bufferedWriter.write(str);
			bufferedWriter.write("\n");
			str=bufferedReader.readLine();
		}
		bufferedWriter.flush();
		bufferedWriter.close();
		bufferedReader.close();
	}
		
	@Test
	public void test08() throws Exception, FileNotFoundException {
		File file=new File("d:\\temp4\\demo.txt");
		InputStreamReader inrr = new InputStreamReader(new FileInputStream(file),"UTF-8");
		char bb [] = new char [(int) file.length()];
		int count =0;
		int temp=0;
		String content;
		while((temp=inrr.read())!=(-1)){
			bb[count++]=(char)temp;
		}
		inrr.close();
		content = new String(bb);
		System.out.println("char方式二全部读取：\r\n"+content);
	}
	
	/**
	 * test08网友写的读取，他说是逐行读取，其实不是。他调用read()无参数重载一次读一个字符，那个循环导致有多少个字符就循环多少次，反而更加影响性能，只是他能解决字符编码问题
	 * 		我始终想着用缓冲流 并且用utf-8格式操作文本文件
	 * @throws IOException 
	 */
	@Test
	public void test09() throws IOException {
		OutputStreamWriter outt=new OutputStreamWriter(new FileOutputStream("d:/temp4/demo.txt",true), "utf-8");		
		BufferedWriter writer=new BufferedWriter(outt);
		int i=0;
		while(i<10) {
			writer.write("\r\n我是buffer流文件操作"+i);
			i++;
		}
		writer.flush();
		writer.close();		
	}
	
	/**
	 * 	看一下buffer流读取的过程
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void test10() throws UnsupportedEncodingException, Exception {
		InputStreamReader ittr=new InputStreamReader(new FileInputStream("d:/temp4/demo.txt"), "utf-8");
		BufferedReader reader=new BufferedReader(ittr);
		String content="";
		String str=null;
		while((str=reader.readLine()) !=null) {
			content += "\r\n"+str;			
		}
		System.out.println(content);
		reader.close();
	}	
	//test09、test10是最有意义的两个方法，合起来就是我们以后想买中会用到的真正的文件操作
}
