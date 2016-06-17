package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;


@Service
public interface GUserService {

	void add(GUser user);
	void delete(long id);
	void update(GUser user);
	GUser find(long id);
	GUser find(String name);
	public QueryResult<GUser> findAlls(int firstindex);
}
