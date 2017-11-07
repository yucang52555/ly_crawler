package org.lengyan.crawler.store.service.service.gushiwen;

import java.util.List;

import org.lengyan.crawler.store.model.po.htmlpo.gushiwen.Book;

/**
 * 古诗文书籍服务
 * @author kangtiancheng
 * @date 2017年6月23日
 */
public interface IBookService {
	
	/**
	 * 保存书籍信息
	 * @param GSWAuthor
	 * @return
	 */
	public void saveBooks(List<Book> GSWBooks);
	
}
