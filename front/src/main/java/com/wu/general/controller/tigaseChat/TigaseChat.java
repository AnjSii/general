package com.wu.general.controller.tigaseChat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.general.utils.CommUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TigaseChat {

	@RequestMapping({ "/senderTigaseChat.htm" })
	public String SenderTigaseChat(Model model) {
		model.addAttribute("userName", "sender");
		model.addAttribute("passord", "123");

		model.addAttribute("sender", "啦啦啦");
		model.addAttribute("senderUserName", "wuxun");
		model.addAttribute("senderPassord", "123");

		model.addAttribute("receiver", "宏利建材旗舰店");
		model.addAttribute("receiverUserName", "hicailiao");
		model.addAttribute("receiverPassword", "123");
		return "senderTigaseChat";
	}

	@RequestMapping({ "/receiverTigaseChat.htm" })
	public String ReceiverTigaseChat(Model model) {
		model.addAttribute("userName", "receiver");
		model.addAttribute("password", "123");

		model.addAttribute("sender", "宏利建材旗舰店");
		model.addAttribute("senderUserName", "hicailiao");
		model.addAttribute("senderPassord", "123");

		model.addAttribute("receiver", "啦啦啦");
		model.addAttribute("receiverUserName", "wuxun");
		model.addAttribute("receiverPassword", "123");
		return "receiverTigaseChat";
	}

	@RequestMapping({ "chatting_content_ajax.htm" })
	public String chatting_content_ajax(HttpServletRequest request, HttpServletResponse response, String chatRecordXml,
										String chatWithName, String chatCurrentName, Model model) {
		Map<Long, Map<String, String>> chatMap = new TreeMap<>();

		if (!chatRecordXml.isEmpty()) {
			try {
				Document document = DocumentHelper.parseText(chatRecordXml);
				Element iqElement = document.getRootElement();
				Element chatElement = iqElement.element("chat");
				String beginTime = chatElement.attributeValue("start");
				long chatStratTime = 0;

				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					format.setTimeZone(TimeZone.getTimeZone("UTC"));
					if (beginTime != null) {
						Date chatStratDate = format.parse(beginTime);
						chatStratTime = chatStratDate.getTime();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

				Iterator iterator = chatElement.elementIterator();
				while (iterator.hasNext()) {
					Map<String, String> nameMap = new HashMap<>();
					Element elem = (Element)iterator.next();

					String secs = elem.attributeValue("secs");
					long time = 0;
					if (secs != null) {
						time = chatStratTime + CommUtil.null2Long(secs) * 1000;
					}

					String elemName = elem.getName();
					String chatContent = elem.elementText("body");
					if (elemName.equals("to")) {
						nameMap.put(chatCurrentName, chatContent);
					} else if (elemName.equals("from")) {
						nameMap.put(chatWithName, chatContent);
					}

					chatMap.put(time, nameMap);
				}
				model.addAttribute("senderUserName", chatCurrentName);
				model.addAttribute("receiverUserName", chatWithName);
				model.addAttribute("chatMap", chatMap);
				model.addAttribute("CommUtil", CommUtil.class);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return "chatting_content_ajax";
	}

	@RequestMapping({ "chatting_users_ajax.htm" })
	public String chatting_users_ajax(HttpServletRequest request, HttpServletResponse response, String users,
									String currentreceiver, Model model) {
		List<String> userList = new ArrayList<>();
		if (!users.isEmpty()) {
			try {
				Document document = DocumentHelper.parseText(users);
				Element iqElement = document.getRootElement();
				Element listElement = iqElement.element("list");

				Iterator iterator = listElement.elementIterator();
				while (iterator.hasNext()) {
					Element elem = (Element)iterator.next();
					if (elem.getName().equals("chat")) {
						String userName = elem.attributeValue("with").split("@")[0];
						if (!currentreceiver.equals(userName)) {
							if (!userList.contains(userName)) {
								userList.add(userName);
							}
						}
					}
				}
				model.addAttribute("userList", userList);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return "chatting_user_ajax";
	}
}
