package com.wu.general.shiro;

/**
 * Token with captcha
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
	private String captcha;

	public UsernamePasswordToken() {
	}

	public UsernamePasswordToken(final String username, final char[] password) {
		super(username, password);
	}

	public UsernamePasswordToken(final String username, final char[] password, final String host) {
		super(username, password, false, host);
	}

	public UsernamePasswordToken(final String username, final char[] password, final boolean rememberMe) {
		super(username, password, rememberMe, null);
	}

	public UsernamePasswordToken(final String username, final char[] password, final boolean rememberMe, final String host) {
		super(username, password, rememberMe, host);
	}

	public UsernamePasswordToken(final String username, final char[] password, final String captcha, final boolean rememberMe, final String host) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}
}
