package jfc.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MapStudy {
	
	
	Map<String,Integer> map=new HashMap<>();
	
	@Before
	public void before() {
		map.put("first",1001);
		map.put("second",1002);
		map.put("third",1003);
		map.put("forth",1004);	
	}
	
	
	/**
	 * 遍历集合1
	 * 	集合最常见的用法是put和get，现在我们尝试遍历集合中的值
	 */
	@Test
	public void test06() {
		
		Map<String,Integer> map=new HashMap<>();
		
		map.put("first",1001);
		map.put("second",1002);
		map.put("third",1003);
		map.put("forth",1004);	
		
		//遍历集合的键，通过这个键其实也是能获取值的
		Set<String> keys=map.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()) {
			String key=iterator.next();
			Integer value=map.get(key);			
			System.out.println("键是："+key+",值是："+value);
		}
		
	}
	
	/**
	 * 遍历集合2
	 * 	test06展示的遍历集合的方法，首先得到键迭代器，然后通过hasNext()、next()方法获取其中的值，我感觉蛮好的，但是作者说这种方式效率低
	 * 	我想效率低的原因可能是需要两步，第一步获取键集合不慢，第二步还是要通过键来查找值，这个可能影响性能，尽管集合可以通过hash值快速查找但还是查找
	 * 	作者推荐下面的方法
	 */
	@Test
	public void test07() {
		//Ctrl+2+l:快速生成变量、变量名，先按ctrl+2,释放后快速按L
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();	
			String key=entry.getKey();
			Integer value=entry.getValue();
			System.out.println("键是："+key+",值是："+value);
		}		
	}
	/**
	 * Entry<String,value>是Map内部的一个接口，只能在Map内部使用，实现了getKey和getValue，是java自己提供的专门用于遍历map自身的接口
	 * 		这就是说这个接口在其他地方没有什么用。
	 */
	
	/**
	 * 遍历集合3、4,尽管下面两种方法看着更容易懂，问题是牛逼人都不这么写而写成test07的形式
	 */
	@Test
	public void test08() {
		for (String key : map.keySet()) {
			//map.get(key);
			Integer value=map.get(key);
			System.out.println("键是："+key+",值是："+value);
		}
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("键是："+key+",值是："+value);
		}
	}
}
