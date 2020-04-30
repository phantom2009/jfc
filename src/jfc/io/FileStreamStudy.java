package jfc.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;


public class FileStreamStudy {
	
	@Test
	public void test01() throws IOException {
		File file=new File("d:\\temp4\\demo.txt");
		InputStream inputStream=new FileInputStream(file);
		
		//read()重载之一，一次读一个自己，这个字节值以int返回，因为一个字节，其值只能是0-255
		int bytte=inputStream.read();
		
		while(bytte!=-1) {
			System.out.println((char)bytte);
			bytte=inputStream.read();
		}
		
		inputStream.close();
	}
	
	/**
	 * 	上面的代码对于unicode特别是utf-8是有问题的，因为么个字符占一个字节，转化成char没有问题，汉字特别是utf-8是不定长编码
	 * 		我们取其中一个字节转化成char他就有可能什么也不是
	 * 
	 * 	实际上对于文本文件尽量使用FileReader和FileWriter操作，这需要另外的操作api；
	 * 	
	 * 	我们先继续学习和FileInputStream相对应的FileOutputStream
	 * @throws IOException 
	 */
	@Test
	public void test02() throws IOException {
		File source=new File("Chrysanthemum.jpg");
		InputStream inputStream=new FileInputStream(source);
		byte[] bytes=new byte[(int) source.length()];
		//read(byte[])重载之二，将流的内容读进入一个二进制字节数组中，我们没有改变文件的内容，只是形式上发生编码，inputStream本质也就是这种字节流
		//	为什么要这一步操作呢？
		//	这是因为与其对应的OutputStream的write()方法用得就是这个参数，入与出就对应上了
		inputStream.read(bytes);
		
		File dest=new File("d:/temp4/flower.jpg");
		if(!dest.exists()) {
			dest.createNewFile();
		}
		
		OutputStream outputStream=new FileOutputStream(dest);
		outputStream.write(bytes);
		
		outputStream.close();
		inputStream.close();
	}
	
	
	/**
	 * 	现在再来思考一个问题，上面我已经说过了，搞一个byte[]做中专是因为in与out通过这个来对应，这非常容易理解，他还有个好处就是可以缓冲输出
	 * 		上面我们创建的byte[] bytes=new byte[(int) source.length()];如果这个文件很大，有几百兆，那么这个字节数组岂不是要几百兆；
	 * 		显然不能这么玩，真正的意图如下
	 */
	@Test
	public void test03() throws IOException {
		File source=new File("Chrysanthemum.jpg");
		InputStream inputStream=new FileInputStream(source);
		byte[] bytes=new byte[1024];
		int len=0;
		File dest=new File("d:/temp4/flower.jpg");
		if(!dest.exists()) {
			dest.createNewFile();
		}
		OutputStream outputStream=new FileOutputStream(dest);	
		while((len=inputStream.read(bytes))!=-1) {
			outputStream.write(bytes, 0, len);			
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
	
	/**
	 * test02所演示的方法是一次读整个文件到字节数组，一次写所有的字节数组到文件输出流中，他的弊端是一次需要太大的吞吐量；
	 * 		test03一次使用1m的字节，然后输出，这样的话在较小吞吐量下也能完成，是一种没有使用缓冲流的缓冲机制。
	 * 		在纯磁盘文件系统中，test03所展示的缓冲机制性能更加强大。
	 * 		
	 * 	但我们常常听到一种专门的缓冲流机制，他是java提供的，有作者做过实验，在本地文件操作时候缓冲流对性能的提升不是很明显
	 * 		但缓冲流有预读机制，在处理大数据文件、网络文件的时候缓冲流机制的性能优势就体现出来了。
	 * 		在线视频、迅雷下载......使用的都是缓冲流机制（网络下载）
	 * 
	 * test03可以对付大部分情况，我们仍然以此为例来用缓冲流写一下。
	 * 		缓冲流本身不是直接的字节流，他需要依赖原始的输入输出流，他预备以缓冲器，到内存中，文件到一定量才输出到外部磁盘上
	 * 		其提高性能的方式就是减少io次数，充分利用磁盘性能
	 * @throws Exception 
	 * 
	 */
	@Test
	public void test05() throws Exception {
		InputStream inputStream=new FileInputStream("Chrysanthemum.jpg");       //构造函数重载，直接传文件名
		OutputStream outputStream=new FileOutputStream("d:/temp4/flower2.jpg"); //构造函数重载
		
		BufferedInputStream bis=new BufferedInputStream(inputStream, 1024);     
		//构造函数重载，第二个参数有默认值，我们这里改为1024，也就是1m，好像小了点，源码： private static int DEFAULT_BUFFER_SIZE = 8192;
		BufferedOutputStream bos=new BufferedOutputStream(outputStream);
		
		int byt;
		byt=bis.read();   //使用了缓冲流一次读一个字节也无所谓，当然也可以结合一次读指定的字节数
		while(-1!=byt) {			
			bos.write(byt);
			byt=bis.read();
		}
		
		bos.flush();
		bos.close();
		bis.close();
	}
	
	//[start] + 使用Buffer*实现文本文件的复制，这同样适用于非文本文件
	@Test
	public void test06() throws IOException{
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		
		File inFile=new File("inFile.txt");
		File outFile=new File("outFile.txt");
		
		FileInputStream fis = new FileInputStream(inFile);                
		//现在看来这种程序也有不好的地方就是不能直接创建缓冲流
		FileOutputStream fos = new FileOutputStream(outFile);
		//3.将创建的节点流的对象作为形参传递给缓冲流的构造器中
		bis = new BufferedInputStream(fis);
		bos = new BufferedOutputStream(fos);
		
		//以上多不api熟悉后一可以简写
		//BufferedInputStream bis2=new BufferedInputStream(new FileInputStream("in.txt"));		
		//BufferedOutputStream bos2=new BufferedOutputStream(new FileOutputStream("out.txt"));
		
		byte[] bytes=new byte[1024];
		int len=0;
		while((len=bis.read(bytes))!=-1){
			bos.write(bytes, 0, len);                                     
			//Writes len bytes from the specified byte array starting at offset off to this buffered output stream.																		
			//从字节数据中获取字节流，off指的是从数据中哪里开始，len指的是从开始位写入多长到缓冲输出流					
		}
		bos.flush();    //最后调用一次，上面的代码缓冲区满了会自动刷出                                              
		bis.close();
		bos.close();
	}
	//[end]
}
