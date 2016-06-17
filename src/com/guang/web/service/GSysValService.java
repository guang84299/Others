package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.mode.GSysVal;

@Service
public interface GSysValService {
	public void save(GSysVal sysVal);
	public GSysVal find();
	public void update(int platfrom);
	public void update(GSysVal sysVal);
}
