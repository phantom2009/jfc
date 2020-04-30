package jfc.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericStudy {

	/**
	 * ��û��ʹ�÷���֮ǰ����һ��д����Ĵ��룬jdk1.5������������Ҫдһ��ArrayList����Integer����
	 */
	@Test
	@SuppressWarnings("all")
	public void test01(){
		List list=new ArrayList();
		list.add(22);
		list.add(23);
		list.add(24);
		list.add("aa");		
		for (int i = 0; i < list.size(); i++) {
			Integer score=(Integer) list.get(i);
			System.out.println(score);
		}		
	}
	/**
	 * ���û��@SuppressWarnings("unchecked")����Ĵ��뾯�治�ϣ���Щ�������඼�Ƿ��͵ģ��������������Ͳ�����������Ĵ�����Ϊû��ʹ�÷��ͼ��
	 * 		������ͨ��������ʱ�ر���
	 */
	
	@Test
	public void test02() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(22);
		list.add(23);
		list.add(24);
		//list.add("aa");   //�����ӵķ�Integer���ͱ���ʱ�ͻᱨ��
	}
	
	/**
	 * ������̳У�B��A���࣬����List<B>����List<A>�����࣬����Ƿ��͵ļ̳У�������Ϊ���Ͳ��ɼ̳�
	 */
	public void test03() {
		Object object1=null;
		String str1="hello generic";
		object1=str1;
		
		Object[] objects=null;
		String[] strs=new String[]{"AA","BB","CC"};	
		objects=strs;
		
		//���������ת�Ͷ�û���κ����⣬������Ĵ���		
		List<Object> list1=null;
		List<String> list2=new ArrayList<String>();						
		//list1=list2;        //������ֵ���У�������ſ�����淴֤��֤���������
		
		//�ȼ�������ô��ֵ,��ô����list1ָ�����list2�ĵ�ַ������List1��ӵ���Object,���Ե���list1.add(123);   
		//Ȼ��list1ָ��ĵ�ַ�ռ���list2���������ģ���Ҫ�������String���ͣ���java��123!="123",˫��������Ƕȶ���Ϊ�Լ�����ȷ��ȴ�ó�һ������Ľ��
		//��˵�������Ǵ����		
	}
	
	@Test
	public void test05() {
		List<?> list=null;
		List<String> list1=new ArrayList<>();
		List<Integer> list2=new ArrayList<>();
		
		list=list1;
		//����
		list=list2;
		
		//����listָ����list1��list2�ĵ�ַ�ռ䣬���ǿ��������������������޷�ʹ��listֱ��������ݣ����������������ļ̳л����е�����
		//list.add(123);
		//list.add("123");	
		
		list2.add(111);
		list2.add(222);
		list2.add(333);
		list2.add(444);	
		//for(Integer i : list) {
		//
		//}
		for(int i=0;i<list.size();i++) {
			System.out.println((Integer)list.get(i));
		}
	}
	
	/**
	 * test05��չʾ�ļ̳�û�н��ʲô���⣬���˵������ôд���ѣ�ʹ��list1��list2��list��ֵ�����ǲ�����list�����ǿ��ַ�ռ䣬����
	 * 		�����ļ̳л���java ���ô��ݶ����в��ġ�
	 * 
	 * ʵ����ͨ���������������������ô��ݻ��߼̳�����ģ����������һ�ֲ�������ʱ������η�����
	 */
	
	
	public void test07(){
		List<?> list=null;
		List<String> list1=new ArrayList<String>();
		List<Integer> list2=new ArrayList<Integer>();		
		list=list1;
		list=list2;		
		
		list2.add(111);
		list2.add(222);
		list2.add(333);
		list2.add(444);	
		
		//show01(list2);   //���ܲ���List<Object>����List<String>���������ݣ�List<String>����List<Object>������	
		show02(list2);
	}
	
	public void show01(List<Object> list){
		/**
		 * ������������Ȳ�����ʵ�֣������ôд��ֻ��List<Object>���͵ı���(����)������Ϊ�������룬
		 *		�в�����Ϊ�ܴ���List<String>��List<UserInfo>�������ǲ���List<Object>�����࣬���Բ��ܴ��룬����String UserInfo��Object������
		 *		����취����ʹ�÷���ͨ�����д������ĸ�ʽ��
		 */
	}		
	public void show02(List<?> list){
		/**
		 * ���ʹ��ͨ����ž��з��͵�����
		 */
	}
	/**
	 * ���Ƿ���ͨ������������ʹ�ó���������û��ʲô��
	 */
	
	/**
	 * ? extends A : ���Դ��A��������,�൱��<=,����List<? extends exception>����list<RuntimeExecption>������ԣ�
	 * 		����List<UserInfo>���У���Ϊ�㶨���ʵ���಻��exception�����ࡣ
	 * 
	 * ? super A:���Դ��A���丸��,�൱��>=,�������������extends�ķ�����̡�
	 */
	  
			

}
