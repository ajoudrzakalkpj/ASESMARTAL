package com.ajou.ase.raspberrycontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.ase.common.RequestParameter;
import com.ajou.ase.common.Utils;
import com.ajou.ase.raspberrycontrol.Raspberry;
import com.ajou.ase.raspberrycontrol.RaspberryServiceImpl;



@Controller
public class RaspberryController {

	@Resource(name = "raspberrycontrolService")
	private RaspberryServiceImpl raspberryService;
		
	@RequestMapping("/raspberrycontrol/raspberry_checkRegistration.do")
	public ModelAndView checkRegistration(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
							
		System.out.println("---------------------------/raspberrycontrol/raspberry_checkRegistration.do---------------------------");
		System.out.println("rp = " + rp);
		
		//클라이언트로부터 파라미터 받은 ID를 저장 및 확인
		String currentNumSN = rp.get("raspberryNumSN").toString();
		System.out.println("Receive Number of Serial Number = "+ currentNumSN);
		
		//클라이언트로 파라미터를 받은 것을 바탕으로 query 날려서 null 인지 아니면 값이 있는지를 확인
		if(this.raspberryService.getObjectForNumSNcheck(rp) == null)
		{
			System.out.println("No same Serail Number");
			
		//	this.raspberryService.saveObject(rp);
					
			map.put("success", "No same Serial Number");
		
		}else{
			System.out.println("There are already registered in SMART AL");
			map.put("fail", "Exist same Serial Number");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
}
