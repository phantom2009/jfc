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
		//Date date=new Date("1983-05-05");               //��֧��
		Date date=new Date("1983/05/05");
		System.out.println(date);
		System.out.println(date.toLocaleString());
		
	}
	/**
	 * ��������java.util.Date���ַ���ֱ�ӹ����ķ��������Ϊdeprecation��java���ڵ�˼·����SimpleDateFormat(DateFormat������)
	 */
	
	/**
	 * ���ȣ��漰����ʱ���ʽ������ͨ��˵�ľ���java.text.DateFormat�����౾����abstract�࣬���������Ҫʹ��������SimpleDateFormat,
	 * 		���������⣬�����dateָ����java.util.date����ʱ�䲿�֣�
	 * 
	 * 		2 SimpleDateFormat��DateFormat�����࣬Ҳ��Ψһ���࣬�ڲ鿴��ṹͼ�ǣ��о�java���ֲַ�ʵ��û�б�Ҫ����Ϊ�������Ժϲ�Ϊһ����
	 * 
	 * 		3 ��ʱ����������һ���ࣺjava.time.DateTimerFormat,˵�����̰߳�ȫ�����ܺ�SimpleDateFormat��࣬������
	 * 
	 * 		4 ֱ�Ӳ����Լ���װ��DateUtil���� �����ַ��������ڱ�ʾ��ʽ���磺"yyyy-MM-dd HH:mm:ss"��
	 */
	@Test
	public void test03() {
		//
		System.out.println(DateUtil.str2date("1983-05-05","yyyy-MM-dd"));
		
		System.out.println(DateUtil.str2timestamp("1983-05-05","yyyy-MM-dd"));
		
	}
	
}
