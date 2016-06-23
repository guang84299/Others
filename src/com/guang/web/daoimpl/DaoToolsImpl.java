package com.guang.web.daoimpl;

import java.util.LinkedHashMap;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;

@Repository @Transactional
public class DaoToolsImpl implements DaoTools{
	@PersistenceContext private EntityManager em ;
	public void add(Object obj) {
		em.persist(obj);
	}

	public <T> void delete(Class<T> entityclass, Object id) {
		em.remove(find(entityclass,id));
	}
	public void update(Object obj) {
		em.merge(obj);
	}
	public <T> T find(Class<T> entityclass, Object id) {
		return em.find(entityclass, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName,
			String value,int  firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityclass);
		String orderbysq = getOrderBy(orderby);
		String sql = null;
		Query query = null;
		if(columnName!=null && value!=null)
		{
			sql = "select o from "+entityname+" o where o."+columnName+" = '"+value+"' "+orderbysq;
			query = em.createQuery(sql);
		}else{
			sql = "select o from "+entityname+" o "+orderbysq;	
			query = em.createQuery(sql);
		}	
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setList(query.getResultList());
		if(columnName!=null && value!=null)
		{
			query = em.createQuery("select count(o) from "+entityname+" o where o."+columnName+" = '"+value+"' "+orderbysq);
			qr.setNum((Long)query.getSingleResult());
		}else{
			query = em.createQuery("select count(o) from "+entityname+" o");
			qr.setNum((Long)query.getSingleResult());
		}	
		return qr;
	}

	@SuppressWarnings("unchecked")
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName,
			String value, String columnName2, String value2, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityclass);
		String orderbysq = getOrderBy(orderby);
		String sql = null;
		Query query = null;
		if(columnName!=null && value!=null && columnName2!=null && value2!=null)
		{
			sql = "select o from "+entityname+" o where o."+columnName+" = '"+value+"' and o."+columnName2+" = '"+value2+"' " +orderbysq;
			query = em.createQuery(sql);
		}else{
			sql = "select o from "+entityname+" o "+orderbysq;	
			query = em.createQuery(sql);
		}	
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setList(query.getResultList());
		if(columnName!=null && value!=null)
		{
			query = em.createQuery("select count(o) from "+entityname+" o where o."+columnName+" = '"+value+"' and o."+columnName2+" = '"+value2+"' "+orderbysq);
			qr.setNum((Long)query.getSingleResult());
		}else{
			query = em.createQuery("select count(o) from "+entityname+" o");
			qr.setNum((Long)query.getSingleResult());
		}	
		return qr;
	}
	
	//��������ѯ
		protected String getOrderBy(LinkedHashMap<String, String> orderby)
		{
			StringBuffer orderbysq = new StringBuffer("");
			if(orderby!=null && orderby.size()>0){
				orderbysq.append(" order by ");
				for(String key : orderby.keySet()){
					orderbysq.append("o.").append(key+" ").append(orderby.get(key)+",");
				}
				orderbysq.deleteCharAt(orderbysq.length()-1);
			}
			return orderbysq.toString();			
		}
	//��ȡʵ����������ݿ��еı���
	protected <T> String getEntityName(Class<T> entityclass){
		String entityname = entityclass.getSimpleName();
		Entity entity = entityclass.getAnnotation(Entity.class);
		if(entity.name()!=null&& !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}

}