package com.wu.general.controller.tigaseChat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TigaseChat {

	@RequestMapping({ "/senderTigaseChat.htm" })
	public String SenderTigaseChat(Model model) {
		model.addAttribute("userName", "sender");
		model.addAttribute("passord", "123");

		model.addAttribute("senderUserName", "啦啦啦");
		model.addAttribute("senderPassord", "123");

		model.addAttribute("receiverUserName", "宏利建材旗舰店");
		model.addAttribute("receiverPassword", "123");
		return "senderTigaseChat";
	}

	@RequestMapping({ "/receiverTigaseChat.htm" })
	public String ReceiverTigaseChat(Model model) {
		model.addAttribute("userName", "receiver");
		model.addAttribute("password", "123");

		model.addAttribute("receiverUserName", "宏利建材旗舰店");
		model.addAttribute("receiverPassword", "123");
		return "receiverTigaseChat";
	}

	@RequestMapping({ "/getMessage.htm" })
	public String getMessage(Model model) {

		return "getMessage.html";
	}
}
