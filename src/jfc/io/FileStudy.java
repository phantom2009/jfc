package jfc.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileStudy {

	
	@Test
	public void test01() {
		File directory=new File("z:/temp");
		if(!directory.exists()) {
			System.out.println(directory.mkdir());
		}	
		File file1=new File("D:\\temp\\demo.txt");     //Ҳ���Խ������ı��൥���ļ� ��C#����Ŀ¼�õ���Directory����   
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}					
		File file2=new File("D:/temp/demo.jpg");        //����һ���յ�ͼƬ�ļ�,���ֿ�ͼƬ�ļ���û�����ݵģ�������window����ʶ�����»��ߣ����ִ�����linux�¿��ܲ��� 
		if(!file2.exists()){
			try {
				file2.createNewFile();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * linuxû���̷��ĸ����·��ʹ�õ���/user/local/java/hadoop-2.8.1.tar,��javaEE�����б��ػ��������뷢����linux��
	 * 		�ǲ�һ���������д��·���Ŀ�ͷ����д���ˣ���javaEE������ͨ��servlet.getRealPath()��ȡ��Ŀ·��Ȼ������ʹ��
	 * 		realPath+"/temp/demo.txt"������linuxϣ��д��realPath+"\temp\demo.txt",�Ǿ�Ҫ���жϲ���ϵͳȻ��������֧�����ˣ�
	 * 		File���ṩ��̬�ֶ�File.separator������ʱ�Զ�������"/"����"\"���������Ƕ�ʹ������д��ѧϰ��
	 * 
	 *	 ��ʱ�����ǻ�ȡ��һFile,���ƶ������������յ�file�ļ���Ҳ��������Ŀ¼��java���ִ���ʽ���ŵ㣬Ҳ�в��õĵط����ǲ���ͨ������ֱ���ж���Ŀ¼�����ļ�
	 *		����java�ṩ�˷��������ж���Ŀ¼�ֻ����ļ�
	 */
	@Test
	public void test02() {
		
		File file1=new File("d:"+File.separator+"temp"+File.separator);
		
		System.out.println(file1.isDirectory());
		System.out.println(file1.isFile());
		
	}
	
	/**
	 * 	���ǲ���ɾ��
	 * @throws IOException 
	 */
	@Test
	public void test03() {
		File file1=new File("d:"+File.separator+"temp"+File.separator+"demo1.txt");
		if(!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (IOException e) {			
				e.printStackTrace();
			}
			
			boolean result=file1.delete();
			
			System.out.println(result);
		}
		
		File file2=new File("d:"+File.separator+"temp2"+File.separator);
		//file2.mkdir();
		file2.delete();
	}
	/**
	 * 	�����������ļ���ʱ����׳��쳣����Ϊȫ·����ʾ���ļ����ܲ����ڣ�����Ŀ¼���׳��쳣��
	 * 		���Ŀ¼�����ڲ����̷������ļ�ϵͳ��Ч������ʧ��,mkdir�ķ���ֵΪfalse
	 * 		���Ŀ¼�Ѿ����������Ѿ����ڵ�Ϊ׼�����ǲ��������Ѿ����ڵ��ļ��е�����
	 * 		���Ŀ¼�����ڣ������̷������ļ�ϵͳ��Ч��mkdir()�����ɹ�����true
	 */
	
	/**
	 * 	�޸�Ŀ¼���ƻ����ļ����޸�api��һ���ģ��ɴ���Ĳ�������
	 * @throws IOException 
	 */
	@Test
	public void test04() throws IOException {
		File directory=new File("d:/temp");
		directory.renameTo(new File("d:/temp4"));
		
		File file=new File("D:\\temp\\demo20190216.txt");
		if(file.exists()){
			file.renameTo(new File("D:\\temp\\demo20190216.png"));
			System.out.println("�������ɹ�");
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());			
		}else{
			file.createNewFile();
		}	
	}
	
	/**
	 * 	 �ļ����͡�����
	 */
	@Test
	public void test05() {
		File file = new File("d:/temp4/demo.txt");
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(file.getName().substring(file.getName().lastIndexOf(".")+1));
		System.out.println(file.getAbsolutePath());
	}
	



}
