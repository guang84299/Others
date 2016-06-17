package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPush;



@Service
public interface GPushService {

	void add(GPush push);
	void delete(Long id);
	void update(GPush push);
	GPush find(Long id);
	QueryResult<GPush> findAll(int firstindex);
	QueryResult<GPush> findByAdId(long adId);
}
