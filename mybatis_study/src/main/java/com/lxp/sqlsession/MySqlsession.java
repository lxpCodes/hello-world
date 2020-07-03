package com.lxp.sqlsession;

import java.lang.reflect.Proxy;

/** 
 * @Description : 
 * @author : lxp
 * @date : 2019年5月28日 上午8:47:17 
*/

public class MySqlsession {
	
	private Excutor executor = new MyExcutor();
	
	private MyConfiguration myConfiguration = new MyConfiguration();
	
	public <T> T selectOne(String statement,Object parameter){
		return executor.query(statement,parameter);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> clas){
		
		return (T)Proxy.newProxyInstance(clas.getClassLoader(), new Class[]{clas}, new MyMapperProxy(this,myConfiguration));
		
	}
	

}
