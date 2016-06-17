package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUserPush;


@Service
public interface GUserPushService {
	void add(GUserPush userPush);
	void delete(Long id);
	void update(GUserPush userPush);
	GUserPush find(Long id);
	QueryResult<GUserPush> findAll(int firstindex);	
	QueryResult<GUserPush> findByPushId(long pushId,int start);
	GUserPush findByPushIdAndUserId(long pushId,long userId);
	QueryResult<GUserPush> findByPushIdAndType(long pushId,String type,int maxNum);
}

