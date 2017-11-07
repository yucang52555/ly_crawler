package org.lengyan.crawler.store.service.serviceimpl.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.dao.gushiwen.GushiwenBookMapper;
import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Book;
import org.lengyan.crawler.store.service.service.gushiwen.IBookService;
import org.lengyan.crawler.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private GushiwenBookMapper bookMapper;
	
	public void saveBooks(List<Book> gswBooks) {
		Integer size = CommonUtils.sizeOf(gswBooks);
		for (int i = 0; i < size; i++) {
			Book book = gswBooks.get(i);
			Integer result = bookMapper.insertSelective(book);
			System.out.println(result);
		}
	}
	
}
