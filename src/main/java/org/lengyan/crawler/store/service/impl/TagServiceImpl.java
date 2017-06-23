package org.lengyan.crawler.store.service.impl;

import java.util.List;

import org.lengyan.crawler.store.dao.GushiwenTagMapper;
import org.lengyan.crawler.store.model.po.Tag;
import org.lengyan.crawler.store.service.ITagService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl implements ITagService {
	
	@Autowired
	private GushiwenTagMapper tagMapper;
	
	@Override
	public void saveTags(List<Tag> gswTags) {
		Integer size = CommonUtils.sizeOf(gswTags);
		for (int i = 0; i < size; i++) {
			Tag tag = gswTags.get(i);
			Integer result = tagMapper.insertSelective(tag);
			System.out.println(result);
		}
	}
	
}
