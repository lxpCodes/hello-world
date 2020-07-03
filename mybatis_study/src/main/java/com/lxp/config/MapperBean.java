package com.lxp.config;

import java.util.List;

/** 
 * @Description : 描述接口的类
 * @author : lxp
 * @date : 2019年5月27日 下午7:38:38 
*/

public class MapperBean {
	private String interfaceName;//接口名
	private List<Function> functions;//接口下所有方法
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	
}
