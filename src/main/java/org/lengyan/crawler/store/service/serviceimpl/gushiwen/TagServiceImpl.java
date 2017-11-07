package org.lengyan.crawler.store.service.serviceimpl.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.dao.gushiwen.GushiwenTagMapper;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Tag;
import org.lengyan.crawler.store.service.service.gushiwen.ITagService;
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

	@Override
	public List<Tag> selectAllTags() {
		return tagMapper.selectAllTags();
	}
	
}
