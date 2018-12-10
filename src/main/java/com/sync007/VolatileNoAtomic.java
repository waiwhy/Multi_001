package com.sync007;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 */
public class VolatileNoAtomic extends Thread{
//	private static volatile int count;
	private static AtomicInteger count = new AtomicInteger(0);
	private static void addCount(){
		for (int i = 0; i < 1000; i++) {
//			count++ ;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	
	public void run(){
		addCount();
	}

	public static  Boolean adda(String aa){
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");

		return list.contains(aa);
	}

	
	public static void main(String[] args) throws ScriptException {

//		List<JSONObject> list = new ArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			JSONObject object = new JSONObject();
//			object.put("id", i);
//			object.put("node" + i, i % 2 == 0 ? "" : i);
//			list.add(object);
//		}
//		for (int j = 0; j < 10; j++) {
//			String node = "node" + j;
//			List<JSONObject> name = list.stream().filter(a -> StringUtils.isNotBlank(a.getString(node))).collect(Collectors.toList());
//			ScriptEngineManager manager = new ScriptEngineManager();
//			ScriptEngine engine = manager.getEngineByName("js");
//			String str1 = name.size() + ">=" + j;
//			Object result1 = engine.eval(str1);
//			System.out.println(JSONObject.toJSONString(name) + "\n" + result1);
//		}


		/*VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}

		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}*/


//		List<String> list = new ArrayList<String>();
//		list.add("aa");
//		list.add("bb");
//		list.add("cc");
//		list.add("dd");
//		JexlContext jexlContext = new MapContext();
//		VolatileNoAtomic volatileNoAtomic = new VolatileNoAtomic();
//		String expression = " volatileNoAtomic.adda(\"aa\") == true   ";
//		jexlContext = new MapContext();
//  		jexlContext.set("ture", true);
//		jexlContext.set("volatileNoAtomic",volatileNoAtomic);
// 		jexlContext.set("bb", "bb");
//
//	/*	org.apache.commons.jexl2.Expression e1 = new JexlEngine().createExpression(expression);
//		Boolean num1 = (Boolean) e1.evaluate(jexlContext);
//		System.out.println(num1);*/
//
//		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//        ScriptEngine engine = scriptEngineManager.getEngineByName("js");
//        String str1 =volatileNoAtomic.adda("aa")  + "== " + true;
//    	Object result1 = engine.eval(str1);
//
//        System.out.println(result1);

		//	ScriptEngineManager manager = new ScriptEngineManager();
//			ScriptEngine engine = manager.getEngineByName("js");


       String s="11,3,5,6,7,i8";
		String[] split = s.split(",");
		List<String> list = Arrays.asList(split);
		Integer ii=3;
		System.out.println(list.contains(ii.toString()));



	}

	
}
