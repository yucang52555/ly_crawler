package org.lengyan.crawler.spider.conversion;


public class DoubleTypeHandle implements TypeHandle<Double> {

	@Override
	public Double getValue(Object src) throws Exception {
		return Double.valueOf(src.toString());
	}

}
