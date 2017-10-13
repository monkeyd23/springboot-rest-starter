package com.zautomate.zportal.commons.dao;

import java.util.List;

public interface GenericDao<E, K> {
	// Get count
	public Long count();
	
	// Get all entities
	public List<E> getAll();
	
	// Get paginated entities
	public List<E> getPaginated(int start, int size);
	
	// Get entity by Id
	public E get(K key);
	
	// Save or Update entity
	public void save(E entity);
	
	// Add new Entity
	public void add(E entity);
    
	// Update existing Entity
    public void update(E entity);
    
    // Remove existing Entity
    public void remove(E entity);
}
