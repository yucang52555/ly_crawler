package org.lengyan.crawler.store.service.impl;

import java.util.List;

import org.lengyan.crawler.store.dao.GushiwenBookMapper;
import org.lengyan.crawler.store.model.po.Book;
import org.lengyan.crawler.store.service.IBookService;
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
