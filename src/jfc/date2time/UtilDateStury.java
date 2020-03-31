package jfc.date2time;

import java.util.Date;

import org.junit.Test;

import jfc.commons.DateUtil;

@SuppressWarnings("all")
public class UtilDateStury {

	@Test
	public void test01() {
		Date date=new Date();
		System.out.println(date);
		System.out.println(date.toLocaleString());
	}
	
	@Test
	public void test02() {
		//Date date=new Date("1983-05-05");               //不支持
		Date date=new Date("1983/05/05");
		System.out.println(date);
		System.out.println(date.toLocaleString());
		
	}
	/**
	 * 几乎所有java.util.Date与字符串直接关联的方法都标记为deprecation，java现在的思路是用SimpleDateFormat(DateFormat的子类)
	 */
	
	/**
	 * 首先：涉及日期时间格式化我们通常说的就是java.text.DateFormat，该类本身是abstract类，因此我们主要使用其子类SimpleDateFormat,
	 * 		有两个问题，这里的date指的是java.util.date，有时间部分；
	 * 
	 * 		2 SimpleDateFormat是DateFormat的子类，也是唯一子类，在查看类结构图是，感觉java这种分层实在没有必要，因为他俩可以合并为一个类
	 * 
	 * 		3 有时候会见到另外一个类：java.time.DateTimerFormat,说是有线程安全，功能和SimpleDateFormat差不多，不考虑
	 * 
	 * 		4 直接测试自己封装的DateUtil工具 传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
	 */
	@Test
	public void test03() {
		//
		System.out.println(DateUtil.str2date("1983-05-05","yyyy-MM-dd"));
		
		System.out.println(DateUtil.str2timestamp("1983-05-05","yyyy-MM-dd"));
		
	}
	
}
