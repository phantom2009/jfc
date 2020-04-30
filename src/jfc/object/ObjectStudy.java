package jfc.object;

import java.util.List;

import org.junit.Test;

import jfc.commons.DateUtil;
import jfc.entities.UserInfo;

public class ObjectStudy {

	@Test
	public void test01() throws CloneNotSupportedException {
		UserInfo userInfo1=new UserInfo("admin", "123456", 
				DateUtil.str2date("2020-04-29", "yyyy-MM-dd"), DateUtil.str2date("1980-05-05", "yyyy-MM-dd"));
		
		UserInfo userInfo2=(UserInfo) userInfo1.clone();
				
		System.out.println(userInfo1==userInfo2);
		System.out.println(userInfo1.equals(userInfo2));
	}
	/**
	 * clone俗称对象深拷贝，由于java引用类型传递传递的是地址值，普通的赋值操作不会调用new构造函数，也就不会再堆上新开辟地址空间，这导致
	 * 		新建的对象和原来的对象其实是一个对象，如果业务需求对新对象修改而不改变原对象的值这种默认赋值操作显然不能胜任。
	 * 
	 * javascript的extend（）方法基本都是各家重写的深拷贝方法，其原理是遍历集合的属性，赋值给new出来的新对象从而完成深度拷贝，这种方法看着没毛病，
	 * 		然而如果看Object源代码,clone()方法有native关键字，这就是说他是C++写的，再往你不就看不到C++的源代码啦，这就是有一个问题为什么？
	 * 		
	 * 		原因是遍历对象是一件非常耗性能的事情，主要是时间影响，如果能直接操作内容地址，将堆上地址复制一份然后将首地址给某个指针不就完成赋值啦，
	 * 		java设计者希望java能达到C++一样的性能。
	 * 		
	 */
	
	/**
	 * equals(Object)是Object类的方法，这就是说所有类都有这个方法，他比较两个对象是否为同一对象时候比较的是指针地址是否相同，如果相同则为同一对象，否则认为不是。
	 * 		这就是说如果使用clone()方法克隆出来的相当于new出来的那么结果肯定不是一个对象，这点不用怀疑。
	 * 
	 * 	==与equals的区别
	 * 	"=="是判断两个变量或实例是不是指向同一个内存空间。
	 *  "equals"是判断两个变量或实例所指向的内存空间的值是不是相同。
	 *  	对于大部分引用类型的比较，我实在看不出这两个有什么区别，如果非要说区别==是运算符，他不仅适合引用类型，更主要的作用人家是给基本类型用的，
	 *  	比较两个对象是否为同一对象，比较的也是地址值，这点不要怀疑，网上有说侧重比较内容纯粹是扯淡。
	 *   public boolean equals(Object obj) {
     *   	return (this == obj);
     *	 }
     *	从源码上看内部还是==.
	 */
	@Test
	public void test02() {
		UserInfo userInfo1=new UserInfo("admin", "123456", 
				DateUtil.str2date("2020-04-29", "yyyy-MM-dd"), DateUtil.str2date("1980-05-05", "yyyy-MM-dd"));
	
		UserInfo userInfo2=userInfo1;
		
		boolean b=userInfo1.equals(userInfo2);
		System.out.println(b);
		
		//这也说明如果没有特殊业务场景UserInfo userInfo2=userInfo1;毫无编程意义，他就是给userInfo1增加一个名字而已
	}
	
	
}
