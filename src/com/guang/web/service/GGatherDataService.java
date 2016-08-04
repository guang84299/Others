package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GatherAppInfo;

@Service
public interface GGatherDataService {
	void delete(long id);
	QueryResult<GatherAppInfo> findAll();
	QueryResult<GatherAppInfo> findAlls(int index);
}
