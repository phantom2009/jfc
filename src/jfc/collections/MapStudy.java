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
	 * ��������1
	 * 	����������÷���put��get���������ǳ��Ա��������е�ֵ
	 */
	@Test
	public void test06() {
		
		Map<String,Integer> map=new HashMap<>();
		
		map.put("first",1001);
		map.put("second",1002);
		map.put("third",1003);
		map.put("forth",1004);	
		
		//�������ϵļ���ͨ���������ʵҲ���ܻ�ȡֵ��
		Set<String> keys=map.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()) {
			String key=iterator.next();
			Integer value=map.get(key);			
			System.out.println("���ǣ�"+key+",ֵ�ǣ�"+value);
		}
		
	}
	
	/**
	 * ��������2
	 * 	test06չʾ�ı������ϵķ��������ȵõ�����������Ȼ��ͨ��hasNext()��next()������ȡ���е�ֵ���Ҹо����õģ���������˵���ַ�ʽЧ�ʵ�
	 * 	����Ч�ʵ͵�ԭ���������Ҫ��������һ����ȡ�����ϲ������ڶ�������Ҫͨ����������ֵ���������Ӱ�����ܣ����ܼ��Ͽ���ͨ��hashֵ���ٲ��ҵ����ǲ���
	 * 	�����Ƽ�����ķ���
	 */
	@Test
	public void test07() {
		//Ctrl+2+l:�������ɱ��������������Ȱ�ctrl+2,�ͷź���ٰ�L
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();	
			String key=entry.getKey();
			Integer value=entry.getValue();
			System.out.println("���ǣ�"+key+",ֵ�ǣ�"+value);
		}		
	}
	/**
	 * Entry<String,value>��Map�ڲ���һ���ӿڣ�ֻ����Map�ڲ�ʹ�ã�ʵ����getKey��getValue����java�Լ��ṩ��ר�����ڱ���map����Ľӿ�
	 * 		�����˵����ӿ��������ط�û��ʲô�á�
	 */
	
	/**
	 * ��������3��4,�����������ַ������Ÿ����׶���������ţ���˶�����ôд��д��test07����ʽ
	 */
	@Test
	public void test08() {
		for (String key : map.keySet()) {
			//map.get(key);
			Integer value=map.get(key);
			System.out.println("���ǣ�"+key+",ֵ�ǣ�"+value);
		}
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("���ǣ�"+key+",ֵ�ǣ�"+value);
		}
	}
}
