package com.lxp.test;

import com.lxp.bean.User;
import com.lxp.mapper.UserMapper;
import com.lxp.sqlsession.MySqlsession;

/** 
 * @Description : 测试自己实现的mybatis框架
 * @author : lxp
 * @date : 2019年5月28日 上午8:47:51 
*/

public class TestMybatis {
	public static void main(String[] args) {
		MySqlsession mySqlsession = new MySqlsession();
		UserMapper mapper = mySqlsession.getMapper(UserMapper.class);
		User user = mapper.getUserById("2");
		System.out.println(user);
	}
}
