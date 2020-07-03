package com.lxp.sqlsession;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lxp.config.Function;
import com.lxp.config.MapperBean;

/** 
 * @Description : 读取与解析配置信息，并返回处理后的environment
 * @author : lxp
 * @date : 2019年5月28日 上午8:46:24 
*/

public class MyConfiguration {
	private static ClassLoader classloader = ClassLoader.getSystemClassLoader();
	
	/**
	 * 获取数据库连接
	 * @param resouce
	 * @return
	 */
	public Connection build(String resouce){
		
		try {
			InputStream inputStream = classloader.getResourceAsStream(resouce);
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			return parseDataConfig(root);
		} catch (DocumentException | ClassNotFoundException e) {
			throw new RuntimeException("解析xml配置文件出错");
		}
	}

	private Connection parseDataConfig(Element node) throws ClassNotFoundException {
		if (!node.getName().equals("database")) {
			throw new RuntimeException("数据库配置文件根节点应该为<database>");
		}
		String driverClass = null;
		String url = null;
		String username = null;
		String password = null;
		for (Object item : node.elements("property")) {
			Element element = (Element)item;
			String value = getValue(element);
			String name = element.attributeValue("name");
			if(name == null || value == null){
				throw new RuntimeException("<property>的属性name和value不能为空");
			}
			
			switch(name){
				case "url":url = value;break;
				case "username":username = value;break;
				case "password":password = value;break;
				case "driverClass":driverClass = value;break;
				default: throw new RuntimeException("<databse>含有未知属性");	
			}
		}
		
		Class.forName(driverClass);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * 获取property的值
	 * @param element
	 * @return
	 */
	private String getValue(Element element) {
		
		return (String) (element.hasContent()? element.getText() : element.attribute("value"));
	}
	
	
	/**
	 * 读取mapper配置文件
	 * @return
	 */
	public MapperBean readMapper(String path){
		MapperBean mapper = new MapperBean();
		
		try {
			InputStream inputStream = classloader.getResourceAsStream(path);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputStream);
			Element root = document.getRootElement();
			mapper.setInterfaceName(root.attributeValue("nameSpace").trim());//接口名
			List<Function> funcs = new ArrayList();
			Iterator iterator = root.elementIterator();
			while (iterator.hasNext()) {
				Function func = new Function();
				Element element = (Element)iterator.next();
				String sqlType = element.getName().trim();
				String funcName = element.attributeValue("id").trim();
				String sql = element.getText().trim();
				String resultType = element.attributeValue("resultType").trim();
				func.setSqltype(sqlType);
				func.setFuncName(funcName);
				
				Object newInstance = null;
				try {
					newInstance = Class.forName(resultType).newInstance();
				} catch (Exception e) {
					// TODO: handle exception
				}
				func.setResultType(newInstance);
				func.setSql(sql);
				funcs.add(func);
			}
			mapper.setFunctions(funcs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapper;
	}
}
