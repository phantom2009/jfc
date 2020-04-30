package jfc.string;

import org.junit.Test;

import jfc.commons.DateUtil;
import jfc.entities.UserInfo;

public class StringStudy {
	/**
	 * 我现在已经知道equals()方法不适用String类型，String是绝对的引用类型，她自己内部覆盖改写了equals()方法，从这个意义来说并不是“equals比较两个对象是否为同一对象”的论断有无
	 * 		只是String本身行为所致。
	 */
	@Test
	public void test03() {
		String str1="aa";
		String str2="aa";
		String str3=new String("aa");
		
		System.out.println(str1==str2);            //==比较地址，对于str1和str2的这种声明方式又用到字符串的另外一种机制“字符串常量池”，他跟java类的方法区在一起病区不可变，参见康哥教程
		System.out.println(str1.equals(str2));
		System.out.println(str1==str3);            //str1余str3内存地址不一样，结果false,因为String3是new出来的，他在堆上有空间，但是这个空间中存储的值还是方法区的AA
		System.out.println(str1.equals(str3));     //equals()方法内部默认使用的是==，这里的结果却为true,因为String类改写了equals()这里是一个难点
		
		
		UserInfo userInfo1=new UserInfo("admin", "123456", 
				DateUtil.str2date("2020-04-29", "yyyy-MM-dd"), DateUtil.str2date("1980-05-05", "yyyy-MM-dd"));
		UserInfo userInfo2=new UserInfo("admin", "admin123456", 
				DateUtil.str2date("2020-04-29", "yyyy-MM-dd"), DateUtil.str2date("1980-05-05", "yyyy-MM-dd"));
		
		System.out.println(userInfo1.getName()==userInfo2.getName());
		System.out.println(userInfo1.getName().equals(userInfo2.getName()));
	}
	/**
	 * 字符串常量池的设计有点难以理解，好好记住刚哥的分析，下面记录的是刚哥分析的结果。		
	 * 	字符串这种设计的目的为为了提高性能、节约空间，一定要理解这点。
	 */

	/**
	 * 字符串常量池不可变，图解参见笔记。
	 */
	@Test
	public void test04() {
		String str1="hello";
		System.out.println("str1的内存地址："+ System.identityHashCode(str1));
		String str2=" world!";
		System.out.println("str2的内存地址："+ System.identityHashCode(str2));
		
		str1+=str2;
		System.out.println("str1的新内存地址："+ System.identityHashCode(str1));
		System.out.println(str1);
	}
	
	/**
	 * 字符串拼接性能检测
	 */
	@Test
	public void test05() {
		
		//plus拼接字符串方式
		String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        System.out.println("Plus cost {"+( te - ts) +"} ms");
        
        //concat拼接字符串方式
        String s2 = "";
        long ts2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s2 = s2.concat(String.valueOf(i));
        }
        long te2 = System.currentTimeMillis();      
        System.out.println("concat cost {"+(te2 - ts2)+"} ms");
        
        //StringUtils.join拼接字符串方式
		//List<String> list = new ArrayList<String>();
		//long ts3 = System.currentTimeMillis();
		//for (int i = 0; i < 10000; i++) {
		//    list.add(String.valueOf(i));
		//}
		//StringUtils.join(list, "");
		//long te3 = System.currentTimeMillis();
		//System.out.println("StringUtils.join cost {"+(te3 - ts3)+"} ms");
	        
	    //StringBuffer拼接字符串方式
        StringBuffer sb = new StringBuffer();
        long ts4 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te4 = System.currentTimeMillis();
        System.out.println("StringBuffer cost {"+(te4 - ts4)+"} ms");
        
        //StringBuilder拼接字符串方式
        StringBuilder sb5 = new StringBuilder();
        long ts5 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb5.append(String.valueOf(i));
        }
        sb5.toString();
        long te5 = System.currentTimeMillis();
        System.out.println("StringBuilder cost {"+(te5 - ts5)+"} ms");
	        
	}
	
	/**
	 * 1 StringBuilder通常情况下最强，也是推荐的使用方式，StringBuffer次之；
	 * 2 StringUtils.join()需要common-lang包，他是apache的工具集，对性能的提高也有帮助
	 * 3 对于字符串操作少的时候使用concat(),除非只有一次是哟+=方法，这两种方式好处在于不用构造新对象
	 */
	
}
