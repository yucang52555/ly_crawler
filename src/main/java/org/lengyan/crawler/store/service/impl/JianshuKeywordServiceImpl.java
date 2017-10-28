package org.lengyan.crawler.store.service.impl;

import java.util.List;

import org.lengyan.crawler.store.dao.jianshu.JianshuKeywordMapper;
import org.lengyan.crawler.store.model.xmlpo.jianshu.JianshuKeyword;
import org.lengyan.crawler.store.service.IJianshuKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jianshuKeywordService")
public class JianshuKeywordServiceImpl implements IJianshuKeywordService {
	
	@Autowired
	private JianshuKeywordMapper jianshuKeywordMapper;

	@Override
	public void saveKeyword(JianshuKeyword jianshuKeyword) {
		jianshuKeywordMapper.insertSelective(jianshuKeyword);
	}

	@Override
	public JianshuKeyword selectJianshuKeyword(String keyword) {
		return jianshuKeywordMapper.selectByKeyword(keyword);
	}

	@Override
	public void updateKeyword(JianshuKeyword jianshuKeyword) {
		jianshuKeywordMapper.updateByPrimaryKeySelective(jianshuKeyword);
	}

	@Override
	public List<JianshuKeyword> selectAllJianshuKeyword() {
		return jianshuKeywordMapper.selectAllJianshuKeyword();
	}
	
	
}
