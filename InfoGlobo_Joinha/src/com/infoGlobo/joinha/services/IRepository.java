/**
 * 
 */
package com.infoGlobo.joinha.services;

import java.util.ArrayList;

/**
 * @author Ricardo
 *
 */
public interface IRepository<T> {
	public void add(T obj);
	
	public boolean remove(T obj);
	
	public void removeAll();
	
	public ArrayList<T> getList();
	
	public T GetById(int id);
}
