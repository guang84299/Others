package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GStatistics;

@Service
public interface GStatisticsService {
	void add(GStatistics statistics);
	void delete(Long id);
	void update(GStatistics statistics);
	GStatistics find(Long id);
	QueryResult<GStatistics> findAlls(int firstindex);
}
