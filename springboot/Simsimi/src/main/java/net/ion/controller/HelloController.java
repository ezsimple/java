package net.ion.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;

import net.ion.entity.ChatMessage;
import net.ion.utils.HttpUtils;
import net.ion.utils.ProxyUtils;
import net.ion.entity.BotMessage;

@Controller
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings")
    public BotMessage greeting(ChatMessage message) throws Exception {

		String url = "http://sandbox.api.simsimi.com/request.p";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("key", "cd5c9387-7223-4e3a-a23e-9412e9b4595a");
		param.put("text", message.getName());
		param.put("ft", "1.0");
		param.put("lc", "ko");
		String res = ProxyUtils.getText(url, param, "response");
		logger.debug(res);
        return new BotMessage(message.getName(), res);
    }
}
