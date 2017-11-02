package com.zsj.annotation.testMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtils {
	@Before
	public void before(){
		System.out.println("before");
	}
	
	public void after(){
		System.out.println("after");
	}
	
	public void runMethod(Class clazz,Method targetMethod){
		Method befor = null;
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if(method.isAnnotationPresent(Before.class)){
				befor = method;
			}
		}
		
		if(targetMethod.isAnnotationPresent(MethodAnnotation.class)){
			try {
				befor.invoke(this, null);
			} catch (IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e1){
				e1.printStackTrace();
			}
		}
		
		try {
//			if(clazz.isInterface()){
//				clazz.isAssignableFrom(clazz);
				targetMethod.invoke(clazz.newInstance(), null);
//			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
