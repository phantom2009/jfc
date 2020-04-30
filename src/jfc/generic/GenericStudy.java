package jfc.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericStudy {

	/**
	 * 在没有使用方向之前我们一般写下面的代码，jdk1.5，需求是我们要写一个ArrayList包含Integer类型
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
	 * 如果没有@SuppressWarnings("unchecked")上面的代码警告不断，这些方法、类都是泛型的，编译器建议类型参数化，上面的代码因为没有使用泛型检查
	 * 		编译器通过，运行时回报错
	 */
	
	@Test
	public void test02() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(22);
		list.add(23);
		list.add(24);
		//list.add("aa");   //如果添加的非Integer类型编译时就会报错
	}
	
	/**
	 * 泛型与继承，B是A子类，但是List<B>不是List<A>的子类，这就是泛型的继承，可以认为泛型不可继承
	 */
	public void test03() {
		Object object1=null;
		String str1="hello generic";
		object1=str1;
		
		Object[] objects=null;
		String[] strs=new String[]{"AA","BB","CC"};	
		objects=strs;
		
		//上面的向上转型都没有任何问题，看下面的代码		
		List<Object> list1=null;
		List<String> list2=new ArrayList<String>();						
		//list1=list2;        //这样赋值不行，下面跟着康哥后面反证法证明这个错误
		
		//先假设能这么赋值,那么现在list1指向的是list2的地址，由于List1添加的是Object,可以调用list1.add(123);   
		//然而list1指向的地址空间是list2最先声明的，他要求传入的是String类型，在java中123!="123",双方从自身角度都认为自己是正确的却得出一个错误的结果
		//这说明起点就是错误的		
	}
	
	@Test
	public void test05() {
		List<?> list=null;
		List<String> list1=new ArrayList<>();
		List<Integer> list2=new ArrayList<>();
		
		list=list1;
		//或者
		list=list2;
		
		//尽管list指向了list1或list2的地址空间，我们可以引用他，但是我们无法使用list直接添加内容，这与我们面向对象的继承还是有点区别
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
	 * test05所展示的继承没有解决什么问题，最多说明能这么写而已，使用list1或list2给list赋值后我们不能用list操作那块地址空间，这与
	 * 		真正的继承或者java 引用传递都是有差别的。
	 * 
	 * 实践中通配符不是真正用来解决引用传递或者继承问题的，他更多的是一种参数传递时候的修饰符问题
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
		
		//show01(list2);   //尽管参数List<Object>但是List<String>与他不兼容，List<String>不是List<Object>的子类	
		show02(list2);
	}
	
	public void show01(List<Object> list){
		/**
		 * 这个方法我们先不管其实现，如果这么写，只有List<Object>类型的变量(对象)才能作为参数传入，
		 *		切不可认为能传入List<String>、List<UserInfo>对象，他们不是List<Object>的子类，所以不能传入，尽管String UserInfo是Object的子类
		 *		解决办法就是使用泛型通配符，写成下面的格式。
		 */
	}		
	public void show02(List<?> list){
		/**
		 * 入参使用通配符才具有泛型的作用
		 */
	}
	/**
	 * 这是泛型通配符最有意义的使用场景，其他没有什么用
	 */
	
	/**
	 * ? extends A : 可以存放A及其子类,相当于<=,比如List<? extends exception>则传入list<RuntimeExecption>对象可以，
	 * 		但是List<UserInfo>不行，因为你定义的实体类不是exception的子类。
	 * 
	 * ? super A:可以存放A及其父类,相当于>=,这个过程是上面extends的反向过程。
	 */
	  
			

}
