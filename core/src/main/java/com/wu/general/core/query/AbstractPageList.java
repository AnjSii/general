package com.wu.general.core.query;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.Query;

public abstract class AbstractPageList<T> implements PageList<T> {
	private static final long serialVersionUID = 3L;

	private int rowCount;

	private int pages;

	private int currentPage;

	private int pageSize;

	private List<T> result;

	private final Query<T> query;

	public AbstractPageList(Query<T> q) {
		this.query = q;
	}

	public List<T> getResult() {
		return this.result;
	}

	protected void doList(String totalSQL, String queryHQL, Map<String, String> joins) {
		List<T> rs = Collections.emptyList();
		long total = this.query.getRows(totalSQL);

		if (total > Integer.MAX_VALUE) {
			throw new IllegalStateException("return records " + total + " over " + Integer.MAX_VALUE);
		}

		this.rowCount = (int)total;
		this.pageSize = (int)total;
		this.pages = 1;
		this.currentPage = 1;

		if (total > 0) {
			rs = this.query.getResult(queryHQL, joins);
		}
		this.result = rs;
	}

	protected void doList(String totalSQL, String queryHQL, Map<String, String> joins, int pageSize, int pageNo) {
		List<T> rs = Collections.emptyList();

		long total = this.query.getRows(totalSQL);

		if (total > Integer.MAX_VALUE) {
			throw new IllegalStateException("return records " + total + " over " + Integer.MAX_VALUE);
		}

		this.rowCount = (int)total;
		this.pageSize = pageSize;
		this.pages = (int)(total + pageSize - 1L) / pageSize;
		this.currentPage = (pageNo > this.pages) ? this.pages : pageNo;

		if (total > 0) {
			//if (pageSize > 0) {
				this.query.setFirstResult((this.currentPage - 1) * pageSize);
				this.query.setMaxResults(pageSize);
			//}
			rs = this.query.getResult(queryHQL, joins);
		}
		this.result = rs;
	}

	public int getPages() {
		return this.pages;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getNextPage() {
		int p = this.currentPage + 1;
		if (p > this.pages)
			p = this.pages;
		return p;
	}

	public int getPreviousPage() {
		int p = this.currentPage - 1;
		if (p < 1)
			p = 1;
		return p;
	}
}
