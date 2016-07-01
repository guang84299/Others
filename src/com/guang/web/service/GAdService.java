package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAd;


@Service
public interface GAdService {

	void add(GAd ad);
	void delete(Long id);
	void update(GAd ad);
	GAd find(Long id);
	QueryResult<GAd> findAds(int firstindex);
	QueryResult<GAd> findAdsByShowLevel();
}
