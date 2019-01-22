package com.controller.selenium.testing;

import lombok.Data;

@Data	
public class Precondition<K, V> {
	/**
	 * 
	 */
	private K key;
	private V value;
	
	public Precondition(K description, V execution) {
		this.key = description;
		this.value = execution;
	}
}
