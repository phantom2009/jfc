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
		
		//read()����֮һ��һ�ζ�һ���Լ�������ֽ�ֵ��int���أ���Ϊһ���ֽڣ���ֵֻ����0-255
		int bytte=inputStream.read();
		
		while(bytte!=-1) {
			System.out.println((char)bytte);
			bytte=inputStream.read();
		}
		
		inputStream.close();
	}
	
	/**
	 * 	����Ĵ������unicode�ر���utf-8��������ģ���Ϊô���ַ�ռһ���ֽڣ�ת����charû�����⣬�����ر���utf-8�ǲ���������
	 * 		����ȡ����һ���ֽ�ת����char�����п���ʲôҲ����
	 * 
	 * 	ʵ���϶����ı��ļ�����ʹ��FileReader��FileWriter����������Ҫ����Ĳ���api��
	 * 	
	 * 	�����ȼ���ѧϰ��FileInputStream���Ӧ��FileOutputStream
	 * @throws IOException 
	 */
	@Test
	public void test02() throws IOException {
		File source=new File("Chrysanthemum.jpg");
		InputStream inputStream=new FileInputStream(source);
		byte[] bytes=new byte[(int) source.length()];
		//read(byte[])����֮�������������ݶ�����һ���������ֽ������У�����û�иı��ļ������ݣ�ֻ����ʽ�Ϸ������룬inputStream����Ҳ���������ֽ���
		//	ΪʲôҪ��һ�������أ�
		//	������Ϊ�����Ӧ��OutputStream��write()�����õþ������������������Ͷ�Ӧ����
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
	 * 	��������˼��һ�����⣬�������Ѿ�˵���ˣ���һ��byte[]����ר����Ϊin��outͨ���������Ӧ����ǳ�������⣬�����и��ô����ǿ��Ի������
	 * 		�������Ǵ�����byte[] bytes=new byte[(int) source.length()];�������ļ��ܴ��м����ף���ô����ֽ���������Ҫ�����ף�
	 * 		��Ȼ������ô�棬��������ͼ����
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
	 * test02����ʾ�ķ�����һ�ζ������ļ����ֽ����飬һ��д���е��ֽ����鵽�ļ�������У����ı׶���һ����Ҫ̫�����������
	 * 		test03һ��ʹ��1m���ֽڣ�Ȼ������������Ļ��ڽ�С��������Ҳ����ɣ���һ��û��ʹ�û������Ļ�����ơ�
	 * 		�ڴ������ļ�ϵͳ�У�test03��չʾ�Ļ���������ܸ���ǿ��
	 * 		
	 * 	�����ǳ�������һ��ר�ŵĻ��������ƣ�����java�ṩ�ģ�����������ʵ�飬�ڱ����ļ�����ʱ�򻺳��������ܵ��������Ǻ�����
	 * 		����������Ԥ�����ƣ��ڴ���������ļ��������ļ���ʱ�򻺳������Ƶ��������ƾ����ֳ����ˡ�
	 * 		������Ƶ��Ѹ������......ʹ�õĶ��ǻ��������ƣ��������أ�
	 * 
	 * test03���ԶԸ��󲿷������������Ȼ�Դ�Ϊ�����û�����дһ�¡�
	 * 		������������ֱ�ӵ��ֽ���������Ҫ����ԭʼ���������������Ԥ���Ի����������ڴ��У��ļ���һ������������ⲿ������
	 * 		��������ܵķ�ʽ���Ǽ���io������������ô�������
	 * @throws Exception 
	 * 
	 */
	@Test
	public void test05() throws Exception {
		InputStream inputStream=new FileInputStream("Chrysanthemum.jpg");       //���캯�����أ�ֱ�Ӵ��ļ���
		OutputStream outputStream=new FileOutputStream("d:/temp4/flower2.jpg"); //���캯������
		
		BufferedInputStream bis=new BufferedInputStream(inputStream, 1024);     
		//���캯�����أ��ڶ���������Ĭ��ֵ�����������Ϊ1024��Ҳ����1m������С�˵㣬Դ�룺 private static int DEFAULT_BUFFER_SIZE = 8192;
		BufferedOutputStream bos=new BufferedOutputStream(outputStream);
		
		int byt;
		byt=bis.read();   //ʹ���˻�����һ�ζ�һ���ֽ�Ҳ����ν����ȻҲ���Խ��һ�ζ�ָ�����ֽ���
		while(-1!=byt) {			
			bos.write(byt);
			byt=bis.read();
		}
		
		bos.flush();
		bos.close();
		bis.close();
	}
	
	//[start] + ʹ��Buffer*ʵ���ı��ļ��ĸ��ƣ���ͬ�������ڷ��ı��ļ�
	@Test
	public void test06() throws IOException{
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		
		File inFile=new File("inFile.txt");
		File outFile=new File("outFile.txt");
		
		FileInputStream fis = new FileInputStream(inFile);                
		//���ڿ������ֳ���Ҳ�в��õĵط����ǲ���ֱ�Ӵ���������
		FileOutputStream fos = new FileOutputStream(outFile);
		//3.�������Ľڵ����Ķ�����Ϊ�βδ��ݸ��������Ĺ�������
		bis = new BufferedInputStream(fis);
		bos = new BufferedOutputStream(fos);
		
		//���϶಻api��Ϥ��һ���Լ�д
		//BufferedInputStream bis2=new BufferedInputStream(new FileInputStream("in.txt"));		
		//BufferedOutputStream bos2=new BufferedOutputStream(new FileOutputStream("out.txt"));
		
		byte[] bytes=new byte[1024];
		int len=0;
		while((len=bis.read(bytes))!=-1){
			bos.write(bytes, 0, len);                                     
			//Writes len bytes from the specified byte array starting at offset off to this buffered output stream.																		
			//���ֽ������л�ȡ�ֽ�����offָ���Ǵ����������￪ʼ��lenָ���Ǵӿ�ʼλд��೤�����������					
		}
		bos.flush();    //������һ�Σ�����Ĵ��뻺�������˻��Զ�ˢ��                                              
		bis.close();
		bos.close();
	}
	//[end]
}
