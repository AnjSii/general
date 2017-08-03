package com.wu.general.core.domain.virtual;

import java.util.Objects;

public class SysMap {
	private final String key;

	private final Object value;

	public SysMap(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		int h = 7;
		h = 31 * h + (key != null ? key.hashCode() : 0);
		h = 31 * h + (value != null ? value.hashCode() : 0);
		return h;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		SysMap o = (SysMap) obj;

		return (Objects.equals(key, o.key) || key != null && key.equals(o.key)) &&
				(value == o.value || value != null && value.equals(o.value));
	}
}
