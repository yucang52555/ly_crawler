package org.lengyan.crawler.store.service.serviceimpl.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.dao.gushiwen.GushiwenAuthorMapper;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Author;
import org.lengyan.crawler.store.service.service.gushiwen.IAuthorService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorService")
public class AuthorServiceImpl implements IAuthorService{
	
	@Autowired
	private GushiwenAuthorMapper authorMapper;
	
	public void saveAuthors(List<Author> gswAuthor) {
		Integer size = CommonUtils.sizeOf(gswAuthor);
		for (int i = 0; i < size; i++) {
			Author author = gswAuthor.get(i);
			Integer result = authorMapper.insertSelective(author);
			System.out.println(result);
		}
	}
	
}
