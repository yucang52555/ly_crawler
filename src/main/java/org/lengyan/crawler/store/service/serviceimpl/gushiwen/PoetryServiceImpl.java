package org.lengyan.crawler.store.service.serviceimpl.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.dao.gushiwen.GushiwenPoetryMapper;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Poetry;
import org.lengyan.crawler.store.service.service.gushiwen.IPoetryService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("poetryService")
public class PoetryServiceImpl implements IPoetryService{
	
	@Autowired
	private GushiwenPoetryMapper poetryMapper;
	
	public void savePoetrys(List<Poetry> gswPoetry) {
		Integer size = CommonUtils.sizeOf(gswPoetry);
		for (int i = 0; i < size; i++) {
			Poetry poetry = gswPoetry.get(i);
			try {
				Integer result = poetryMapper.insertSelective(poetry);
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
