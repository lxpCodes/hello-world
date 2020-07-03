package com.lxp.sqlsession;

/** 
 * @Description : 
 * @author : lxp
 * @date : 2019年5月28日 上午8:45:35 
*/

public interface Excutor {
	public <T> T query(String statement, Object parameter);
}
