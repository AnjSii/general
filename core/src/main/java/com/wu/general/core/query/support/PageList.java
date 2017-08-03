package com.wu.general.core.query.support;

import java.io.Serializable;
import java.util.List;

public interface PageList<T> extends Serializable {
	List<T> getResult();

	int getPages();

	int getRowCount();

	int getCurrentPage();

	int getPageSize();

	void doList();

	void doList(int currentPage, int pageSize);
}
