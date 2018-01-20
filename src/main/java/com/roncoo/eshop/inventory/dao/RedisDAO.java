package com.roncoo.eshop.inventory.dao;

/**
 * redis dao
 * 
 * @author Administrator
 *
 */
public interface RedisDAO {

	void set(String key, String value);
	
	String get(String key);

	void delete(String key);
	
}
