package com.wu.general.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * Token with sid
 *
 * @author Xiangchun Zeng
 */
public class SidToken implements HostAuthenticationToken {

	private char[] sid;

	private String host;

	public SidToken() {
	}

	public SidToken(final char[] sid, final String host) {
		this.sid = sid;
		this.host = host;
	}

	public void setSid(char[] sid) {
		this.sid = sid;
	}

	public char[] getSid() {
		return sid;
	}

	public Object getPrincipal() {
		return sid;
	}

	public Object getCredentials() {
		return getSid();
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String getHost() {
		return host;
	}

	public void clear() {
		this.host = null;

		if (this.sid != null) {
			for (int i = 0; i < sid.length; i++) {
				this.sid[i] = 0x00;
			}
			this.sid = null;
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(" - ");
		if (host != null) {
			sb.append(" (").append(host).append(")");
		}
		return sb.toString();
	}
}
