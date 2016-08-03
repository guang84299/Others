package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GOffer;

@Service
public interface GOfferService {
	void add(GOffer offer);
	void delete(Long id);
	void update(GOffer offer);
	GOffer find(Long id);
	QueryResult<GOffer> findAlls(int firstindex);
}
