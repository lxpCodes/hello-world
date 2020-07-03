package com.lxp.sqlsession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lxp.bean.User;

/** 
 * @Description : 
 * @author : lxp
 * @date : 2019年5月28日 上午8:46:43 
*/

public class MyExcutor implements Excutor{
	
	private MyConfiguration myConfiguration = new MyConfiguration();

	@Override
	public <T> T query(String statement, Object parameter) {
		Connection connection = getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(statement);
			//设置参数
			preparedStatement.setString(1, parameter.toString());
			resultSet = preparedStatement.executeQuery();
			//遍历结果
			User user = new User();
			while (resultSet.next()) {
				user.setId(resultSet.getString(1));
				user.setUsername(resultSet.getString(2));
				user.setPwd(resultSet.getString(3));
			}
			return (T)user;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	private Connection getConnection(){
		try {
			Connection connection = myConfiguration.build("config.xml");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
