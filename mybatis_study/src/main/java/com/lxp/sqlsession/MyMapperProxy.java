package com.lxp.sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.lxp.config.Function;
import com.lxp.config.MapperBean;

/** 
 * @Description : 实现调用指定方法的动态代理类
 * @author : lxp
 * @date : 2019年5月28日 上午8:46:56 
*/

public class MyMapperProxy implements InvocationHandler{
	
	private MySqlsession mySqlsession;
	
	private MyConfiguration myConfiguration;
	
	
	public MyMapperProxy(MySqlsession mySqlsession, MyConfiguration myConfiguration) {
		this.mySqlsession = mySqlsession;
		this.myConfiguration = myConfiguration;
	}


	@Override
	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		MapperBean mapper = myConfiguration.readMapper("UserMapper.xml");
		//是否有xml文件对应的接口
		if (!method.getDeclaringClass().getName().equals(mapper.getInterfaceName())) {
			return null;
		}
		List<Function> functions = mapper.getFunctions();
		if (functions != null || functions.size() != 0) {
			for (Function function : functions) {
				if (method.getName().equals(function.getFuncName())) {
					return mySqlsession.selectOne(function.getSql(), String.valueOf(objects[0]));
				}
			}
		}
		return null;
	}


	
}
