package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GPush;
import com.guang.web.service.GPushService;
@Service
public class GPushServiceImpl implements GPushService{
	@Resource private DaoTools daoTools;
	public void add(GPush push) {
		daoTools.add(push);
	}

	public void delete(Long id) {
		daoTools.delete(GPush.class, id);
	}

	public void update(GPush push) {
		daoTools.update(push);
	}

	public GPush find(Long id) {
		return daoTools.find(GPush.class,id);
	}

	public QueryResult<GPush> findAll(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GPush.class, null, null, firstindex, 20, lhm);
	}

	public QueryResult<GPush> findByAdId(long adId) {
		return daoTools.find(GPush.class, "adId", adId+"", 0, 1000000, null);
	}

}
