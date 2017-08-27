package realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticatorTest {

	private void login(String configFile) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:" + configFile);

		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wu", "123");

		subject.login(token);
	}

	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();

		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}

	@Test(expected = UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithFail() {
		login("shiro-authenticator-all-fail.ini");
	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}

}
