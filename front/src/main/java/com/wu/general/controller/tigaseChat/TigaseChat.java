package com.wu.general.controller.tigaseChat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TigaseChat {

	@RequestMapping({ "/senderTigaseChat.htm" })
	public String SenderTigaseChat(Model model) {
		model.addAttribute("userNname", "sender");
		model.addAttribute("passord", "123");
		System.out.println("哈哈哈");
		return "senderTigaseChat";
	}

	@RequestMapping({ "/receiverTigaseChat.htm" })
	public String ReceiverTigaseChat(Model model) {
		model.addAttribute("userNname", "receiver");
		model.addAttribute("password", "123");
		return "receiverTigaseChat";
	}
}
