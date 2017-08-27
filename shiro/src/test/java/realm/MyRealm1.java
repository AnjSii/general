package realm;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
	@Override
	public String getName() {
		return "MyReam1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的Toke
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal(); //得到用户名
		String password = new String((char[]) token.getCredentials()); //得到密码
		if (!"wu".equals(userName)) {
			throw new UnknownAccountException(); //如果用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}
		//如果身份认证成功，返回一个SimpleAuthenticationInfo实现。
		return new SimpleAuthenticationInfo(userName, password, getName());
	}
}
