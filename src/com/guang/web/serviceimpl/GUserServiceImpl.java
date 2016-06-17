package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GUser;
import com.guang.web.service.GUserService;

@Service
public class GUserServiceImpl implements GUserService{
	@Resource private  DaoTools daoTools;
	
	public void add(GUser user) {
		daoTools.add(user);
	}

	public void delete(long id) {
		daoTools.delete(GUser.class, id);
	}

	public void update(GUser user) {
		daoTools.update(user);
	}

	public GUser find(long id) {
		return daoTools.find(GUser.class, id);
	}

	public GUser find(String name) {
		QueryResult<GUser> qr = daoTools.find(GUser.class, "name",name, 0, 1, null);
		if(qr.getList() != null && qr.getList().size() > 0)
		return qr.getList().get(0);
		return null;
	}

	public QueryResult<GUser> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUser.class, null, null, firstindex, 20, lhm);
	}

}
