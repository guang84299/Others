package com.guang.web.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GArea;
import com.guang.web.service.GAreaService;


@Service
public class GAreaServiceImpl implements GAreaService {
	@Resource private DaoTools daoTools;

	public void add(GArea area) {
		try {
			daoTools.add(area);
		} catch (Exception e) {
		}
	}

	public GArea find(int id) {
		return daoTools.find(GArea.class, id);
	}

	public QueryResult<GArea> findAll() {
		return daoTools.find(GArea.class, null, null, 0, 10000, null);
	}

}
