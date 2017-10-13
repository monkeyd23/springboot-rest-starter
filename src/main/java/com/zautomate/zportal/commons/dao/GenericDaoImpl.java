package com.zautomate.zportal.commons.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {
	
	// Autowired Hibernate Session Factory
	@Autowired
    private SessionFactory sessionFactory;
    
	// Class variable to hold runtime class
    protected Class<? extends E> entity;
    
 
    /**
     * By defining this class as abstract, we prevent Spring from creating 
     * instance of this class If not defined as abstract, 
     * getClass().getGenericSuperClass() would return Object. There would be 
     * exception because Object class does not hava constructor with parameters.
	*/
	public GenericDaoImpl() {
         Type t = getClass().getGenericSuperclass();
         ParameterizedType pt = (ParameterizedType) t;
         entity = (Class<? extends E>) pt.getActualTypeArguments()[0];
    }
    
    // Method to get current session
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
     
    // Get count
    @Override
    public Long count() {
    	return (Long) getCurrentSession().createCriteria(entity).setProjection(Projections.rowCount()).uniqueResult();
    }
    
    // Get all entities 
	@Override
	public List<E> getAll() {
		return getCurrentSession().createCriteria(entity).list();
	}
	
	@Override
	public List<E> getPaginated(int start, int size) {
		return getCurrentSession().createCriteria(entity).setFirstResult(start).setMaxResults(size).list();
	}
	
	// Get entity by Id
	@Override
	public E get(K key) {
		return (E) getCurrentSession().get(entity, key);
	}
	
	// Save or Update entity
	@Override
	public void save(E entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	
	// Add new Entity
	@Override
	public void add(E entity) {
		getCurrentSession().save(entity);
	}
	
	// Update existing Entity
	@Override
	public void update(E entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	
	// Remove existing Entity
	@Override
	public void remove(E entity) {
		getCurrentSession().delete(entity);
	}
}
