package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAd;
import com.guang.web.service.GAdService;


@Service
public class GAdServiceImpl implements GAdService{

	@Resource private DaoTools daoTools;

	public void add(GAd ad) {
		daoTools.add(ad);
	}

	public void delete(Long id) {
		daoTools.delete(GAd.class, id);
	}

	public void update(GAd ad) {
		daoTools.update(ad);
	}

	public GAd find(Long id) {
		return daoTools.find(GAd.class, id);
	}

	public QueryResult<GAd> findAds(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GAd.class, null, null, firstindex, 20, lhm);
	}

}
