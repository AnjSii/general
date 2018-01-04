package com.wu.general.shiro;

import java.nio.charset.Charset;
import java.util.Date;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;

/**
 * 自动登录
 *
 */
// TODO: rememberMe cookie 安全性，nginx，by hzl 2016/3/4
public class GeneralRememberMeManager extends CookieRememberMeManager {
	private static final Charset UTF8 = Charset.forName("UTF-8");


	protected byte[] convertPrincipalsToBytes(PrincipalCollection principals) {
		String realName = principals.getRealmNames().iterator().next();
		Long pricipal = (Long)principals.getPrimaryPrincipal();
		// FIXME: 增加浏览器指纹? by hzl 2016/3/3
		byte[] bytes = (realName + "|" + pricipal.toString() + "|" + new Date().getTime()).getBytes(UTF8);
		if (getCipherService() != null) {
			bytes = encrypt(bytes);
		}
		return bytes;
	}


	protected PrincipalCollection convertBytesToPrincipals(byte[] bytes, SubjectContext subjectContext) {
		if (getCipherService() != null) {
			bytes = decrypt(bytes);
		}

		String[] s = new String(bytes, UTF8).split("\\|");

		String realName = s[0];
		long pricipal = Long.parseLong(s[1]);

		// TODO: 检查创建日期? by hzl 2016/3/2
		Date createDate = new Date(Long.parseLong(s[2]));

		return new SimplePrincipalCollection(pricipal, realName);
	}


	public void setBase64CipherKey(String base64CipherKey) {
		setCipherKey(Base64.decode(base64CipherKey));
	}

	public void setBase64EncryptionCipherKey(String base64EncryptionCipherKey) {
		setEncryptionCipherKey(Base64.decode(base64EncryptionCipherKey));
	}

	public void setBase64DecryptionCipherKey(String base64DecryptionCipherKey) {
		setDecryptionCipherKey(Base64.decode(base64DecryptionCipherKey));
	}
}
