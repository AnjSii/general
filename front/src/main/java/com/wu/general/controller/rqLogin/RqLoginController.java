package com.wu.general.controller.rqLogin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.general.foundation.domain.QrLogin;
import com.wu.general.foundation.service.rqLoginService.QrLoginService;
import com.wu.general.utils.CommUtil;
import com.wu.general.utils.QRCodeUtil;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RqLoginController {

	@Autowired
	private QrLoginService qrLoginService;

	//进入到登录页面
	@RequestMapping({ "/login.htm" })
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	//ajax调用获取二维码
	@RequestMapping({ "/getRQCode.htm" })
	public void getRQCode(HttpServletRequest request, HttpServletResponse response) {
		String sid;
		QrLogin qrLogin;
		//生成sid，并且查询数据库中是否存在，存在继续生成新的sid，直到没有重复的为止。
		do {
			//生成sid
			sid = randomSid();
			qrLogin = this.qrLoginService.getObjBySid(sid);
		} while (qrLogin != null);

		//插入新的数据
		qrLogin = new QrLogin();
		qrLogin.setAddTime(new Date());
		qrLogin.setSid(sid);
		this.qrLoginService.save(qrLogin);

		Map<String, String> map = new HashMap<>();
		//生成二维码的内容，这里使用json的格式。
		String content = "{\"url\": \"http://localhost:8088/scanRQCode.htm\",\"sid\":" + sid + "}";
		System.out.println(sid);
		String imgBase64 = "";
		try {
			//创建二维码
			BufferedImage bufferedImage = QRCodeUtil.createImage(content, null, false);
			//将创建的二维码转成base64的格式返回前台，就不用将二维码图片进行存贮操作了。
			imgBase64 = QRCodeUtil.encodeToBase64(bufferedImage, QRCodeUtil.FORMAT_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回ajax里面的内容。
		map.put("imgBase64", imgBase64);
		map.put("sid", sid);
		String json = Json.toJson(map, JsonFormat.compact());

		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//手机扫描二维码的到url调用方法
	@RequestMapping({ "/scanRQCode.htm" })
	public void scanRQCode(HttpServletRequest request, HttpServletResponse response,
						String userName, String sid) {
		//用于扫描二维码是否成功。
		boolean isLogin;
		/*------------- 这里只是简单的进行了登录验证，具体情况要在具体的项目实现 -------------*/
		//使用扫面出来的二维码得到的sid进行查询
		QrLogin qrLogin = this.qrLoginService.getObjBySid(sid);
		//userName应该是由token或者其他机制获得，并不是直接传过来。
		if (!userName.isEmpty() && qrLogin != null) {
			//更新表中的字段
			qrLogin.setUserName(userName);
			qrLogin.setScanTime(new Date());
			isLogin = this.qrLoginService.update(qrLogin);
		} else {
			isLogin = false;
		}
		/*------------------------------------------------------------------------------*/

 		Map<String, Boolean> map = new HashMap<>();
		map.put("isLogin", isLogin);

		String json = Json.toJson(map, JsonFormat.compact());
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//ajax轮询长连接判断是否登录成功
	@RequestMapping({ "/LongConnectionCheck.htm" })
	public void LongConnectionCheck(HttpServletRequest request, HttpServletResponse response, String sid) {
		//用sid查询数据库，取出数据
		QrLogin qrLogin = this.qrLoginService.getObjBySid(sid);
		Map<String, String> map = new HashMap<>();
		//判定qrLogin内的内容
		if (qrLogin != null && qrLogin.getUserName() != null && !qrLogin.getUserName().isEmpty()) {
			//二维码失效时间为一分钟
			long addTime = qrLogin.getAddTime().getTime();
			long scanTime = qrLogin.getScanTime().getTime();
			System.out.println(scanTime - addTime);
			if (scanTime - addTime < 60000) {
				//这里，进行简单的登录，将username返回前台，进行判断登录
				//具体情况要在具体的项目中进行登录验证，以及返回前台的内容。
				map.put("userName", qrLogin.getUserName());
			} else {
				map.put("Invalid", "二维码已经失效");
			}
		}

		String json = Json.toJson(map, JsonFormat.compact());
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//随机生成Sid方法
	private String randomSid() {
		StringBuilder sid = new StringBuilder();
		Random random = new Random();
		//随机生成，大写，小写字母，数字组成的八位Sid
		for (int i = 0; i < 8; i++) {
			//求余，如果余数为零那么就是输出字符串，余数不为零输出数字。
			boolean charOrNum = random.nextInt(2) % 2 == 0;
			//输出字母还是数字
			if (charOrNum) {
				//输出是大写字母还是小写字母,65～90号为26个大写英文字母，97～122号为26个小写英文字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				sid.append((char) (random.nextInt(26) + temp));
			} else {
				sid.append(random.nextInt(10));
			}
		}
		if (CommUtil.null2Int(sid) != 0) {
			return randomSid();
		}
		return sid.toString();
	}
}
