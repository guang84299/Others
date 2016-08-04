package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdmin;
import com.guang.web.mode.GUser;
import com.guang.web.mode.GatherAppInfo;
import com.guang.web.service.GGatherDataService;

@Service
public class GGatherDataServiceimpl implements GGatherDataService{
	@Resource DaoTools daoTools;
	public QueryResult<GatherAppInfo> findAll() {
		return daoTools.find(GatherAppInfo.class, null, null, 0, 1000000, null);
	}
	public QueryResult<GatherAppInfo> findAlls(int firstindex) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GatherAppInfo.class, null, null, firstindex, 20, lhm);
	}
	public void delete(long id) {
		// TODO Auto-generated method stub
		daoTools.delete(GatherAppInfo.class, id);
	}
}
