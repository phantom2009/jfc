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
		File file1=new File("D:\\temp\\demo.txt");     //也可以建立非文本类单独文件 ，C#操作目录用的是Directory类型   
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}					
		File file2=new File("D:/temp/demo.jpg");        //建立一个空的图片文件,这种空图片文件是没有内容的，由于在window下能识别做下划线，这种代码在linux下可能不行 
		if(!file2.exists()){
			try {
				file2.createNewFile();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * linux没有盘符的概念，其路径使用的是/user/local/java/hadoop-2.8.1.tar,在javaEE开发中本地环境调试与发布到linux上
	 * 		是不一样的上面的写法路径的开头部分写死了，在javaEE中我们通过servlet.getRealPath()获取项目路径然后我们使用
	 * 		realPath+"/temp/demo.txt"，但是linux希望写成realPath+"\temp\demo.txt",那就要先判断操作系统然后条件分支决定了；
	 * 		File类提供静态字段File.separator，运行时自动决定是"/"还是"\"，后面我们都使用这种写法学习。
	 * 
	 *	 有时候我们获取了一File,这破东西可能是最终的file文件，也可能是以目录，java这种处理方式有优点，也有不好的地方我们不能通过类型直接判断是目录或者文件
	 *		于是java提供了方法用来判断是目录又或者文件
	 */
	@Test
	public void test02() {
		
		File file1=new File("d:"+File.separator+"temp"+File.separator);
		
		System.out.println(file1.isDirectory());
		System.out.println(file1.isFile());
		
	}
	
	/**
	 * 	我们测试删除
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
	 * 	当操作的是文件的时候会抛出异常，因为全路径表示的文件可能不存在，但是目录不抛出异常，
	 * 		如果目录不存在并且盘符或者文件系统无效，则建立失败,mkdir的返回值为false
	 * 		如果目录已经存在则以已经存在的为准，我们并不操作已经存在的文件中的内容
	 * 		如果目录不存在，并且盘符或者文件系统有效，mkdir()建立成功返回true
	 */
	
	/**
	 * 	修改目录名称或者文件名修改api是一样的，由传入的参数决定
	 * @throws IOException 
	 */
	@Test
	public void test04() throws IOException {
		File directory=new File("d:/temp");
		directory.renameTo(new File("d:/temp4"));
		
		File file=new File("D:\\temp\\demo20190216.txt");
		if(file.exists()){
			file.renameTo(new File("D:\\temp\\demo20190216.png"));
			System.out.println("重命名成功");
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());			
		}else{
			file.createNewFile();
		}	
	}
	
	/**
	 * 	 文件类型、长度
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
