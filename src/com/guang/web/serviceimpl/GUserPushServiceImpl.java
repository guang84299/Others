package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUserPush;
import com.guang.web.service.GUserPushService;

@Service
public class GUserPushServiceImpl implements GUserPushService{
	@Resource private  DaoTools daoTools;
	public void add(GUserPush userPush) {
		daoTools.add(userPush);
	}

	public void delete(Long id) {
		daoTools.delete(GUserPush.class, id);
	}

	public void update(GUserPush userPush) {
		daoTools.update(userPush);
	}

	public GUserPush find(Long id) {
		return daoTools.find(GUserPush.class, id);
	}

	public QueryResult<GUserPush> findAll(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUserPush.class, null, null, firstindex, 20, lhm);
	}

	public QueryResult<GUserPush> findByPushId(long pushId, int start) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUserPush.class, "pushId", pushId+"", start, 20, lhm);
	}

	public GUserPush findByPushIdAndUserId(long pushId, long userId) {
		QueryResult<GUserPush> qr = daoTools.find(GUserPush.class, "pushId", pushId+"", "userId",userId+"",0, 1, null);
		
		if(qr.getList() != null && qr.getList().size() > 0)
			return qr.getList().get(0);
		return null;
	}

	public QueryResult<GUserPush> findByPushIdAndType(long pushId, String type,
			int maxNum) {
		return daoTools.find(GUserPush.class, "pushId", pushId+"",type,1+"", 0, maxNum, null);
	}

}
