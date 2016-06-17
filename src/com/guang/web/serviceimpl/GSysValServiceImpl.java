package com.guang.web.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.web.dao.DaoTools;
import com.guang.web.mode.GSysVal;
import com.guang.web.service.GSysValService;

@Service
public class GSysValServiceImpl implements GSysValService{

	
	@Resource private DaoTools daoTools;
	

	public void save(GSysVal sysVal) {
		daoTools.add(sysVal);
	}

	public GSysVal find() {
		return daoTools.find(GSysVal.class, 1l);
	}

	public void update(int platfrom) {
		GSysVal adp = find();
		adp.setPlatfrom(platfrom);
		daoTools.update(adp);
	}

	public void update(GSysVal sysVal) {
		daoTools.update(sysVal);
	}

}
