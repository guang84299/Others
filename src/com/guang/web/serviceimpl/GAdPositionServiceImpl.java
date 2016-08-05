package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GAdPosition;
import com.guang.web.service.GAdPositionService;
@Service
public class GAdPositionServiceImpl implements GAdPositionService{
	@Resource private DaoTools daoTools;
	public void add(GAdPosition adPosition) {
		daoTools.add(adPosition);
	}

	public void delete(Long id) {
		daoTools.delete(GAdPosition.class, id);
	}

	public void update(GAdPosition adPosition) {
		daoTools.update(adPosition);
	}

	public GAdPosition find(Long id) {
		return daoTools.find(GAdPosition.class, id);
	}

	public QueryResult<GAdPosition> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GAdPosition.class, null, null, firstindex, 20, lhm);
	}

	public QueryResult<GAdPosition> findAlls() {
		return daoTools.find(GAdPosition.class, null, null, 0, 20, null);
	}

}
