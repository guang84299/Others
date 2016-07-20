package com.guang.web.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.dao.QueryResult;
import com.guang.web.mode.GSdk;
import com.guang.web.service.GSdkService;

@Service
public class GSdkServiceImpl implements GSdkService{
	@Resource private DaoTools daoTools;

	public void add(GSdk sdk) {
		daoTools.add(sdk);
	}

	public void delete(Long id) {
		daoTools.delete(GSdk.class, id);
	}

	public void update(GSdk sdk) {
		daoTools.update(sdk);
	}

	public GSdk find(Long id) {
		return daoTools.find(GSdk.class, id);
	}

	public GSdk findNew() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		List<GSdk> list = daoTools.find(GSdk.class, "online",1+"", 0, 1, lhm).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GSdk> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GSdk.class, null,null, firstindex, 20, lhm);
	}
	
}
