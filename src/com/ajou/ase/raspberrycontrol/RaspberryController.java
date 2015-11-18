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

import com.ajou.ase.board.Board;
import com.ajou.ase.common.RequestParameter;
import com.ajou.ase.common.Utils;
import com.ajou.ase.raspberrycontrol.Raspberry;
import com.ajou.ase.raspberrycontrol.RaspberryServiceImpl;
import com.ajou.ase.user.User;
import com.ajou.ase.raspberrycontrol.RaspberrySA;



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
					
			map.put("fail", "No same Serial Number");
		
		}else{
			System.out.println("There are already registered in SMART AL");
			map.put("success", "Exist same Serial Number");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/raspberrycontrol/raspberry_PreRegistration.do")
	public ModelAndView userRegistration(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Raspberry raspberry = new Raspberry();
		
		//체커
		System.out.println("---------------------------/raspberrycontrol/raspberry_PreRegistration.do---------------------------");
		System.out.println("rp = "+ rp);
		
		raspberry.setRaspberryNumSN(rp.get("raspberryNumSN").toString());
		raspberry.setRaspberryIPAddr(rp.get("raspberryIPAddr").toString());
		raspberry.setRaspberrySSID(rp.get("raspberrySSID").toString());
		raspberry.setRaspberryStatus("0");
		
		//데이터 주입 확인
		System.out.println("SerialNumber= "+ raspberry.getRaspberryNumSN());
		System.out.println("IPAddress= "+ raspberry.getRaspberryIPAddr());
		System.out.println("SSID= "+ raspberry.getRaspberrySSID());
		System.out.println("Status= "+ raspberry.getRaspberryStatus());
						
		//성공이후 userService의 save를 이용하여 insert SQL명령 실행
		raspberryService.save(raspberry);
		
		//성공여부에 따라 map으로 k와 v값을 각각 주입
		if(raspberry !=null)	map.put("success", "Success to Registration");
		else map.put("fail", "Fail to Registration");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));

		return mnv;
	}
	
	@RequestMapping("/raspberrycontrol/load_ConfirmedList.do")
	public ModelAndView loadConfirmedList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/raspberrycontrol/load_ConfirmedList.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> raspberryMap = new HashMap<String, Object>();
		
		ArrayList<Raspberry> raspberryList = (ArrayList<Raspberry>) this.raspberryService.getConfirmedList(rp);
		
		System.out.println("Confirmedlist = " + raspberryList);
		
		if(raspberryList.size() == 0){					
			map.put("fail", "There is no confirmation waiting member.");
		}else{ 
			map.put("success", raspberryList);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	@RequestMapping("/raspberrycontrol/load_UnconfirmedList.do")
	public ModelAndView loadUnconfirmedList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/raspberrycontrol/load_UnconfirmedList.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> raspberryMap = new HashMap<String, Object>();
		
		ArrayList<Raspberry> raspberryList = (ArrayList<Raspberry>) this.raspberryService.getUnconfirmedList(rp);
		
		System.out.println("Unconfirmedlist = " + raspberryList);
		
		if(raspberryList.size() == 0){					
			map.put("fail", "There is no confirmation waiting node.");
		}else{ 
			map.put("success", raspberryList);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	@RequestMapping("/raspberrycontrol/load_UnconfirmedRaspberry.do")
	public ModelAndView loadUnconfirmedRaspberry(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/raspberrycontrol/load_UnconfirmedRaspberry.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
				
		Raspberry raspberry = (Raspberry) this.raspberryService.getObjectbyNumSeq(rp);
		
		System.out.println("UnconfirmedRaspberry = " + raspberry);
		
		if(raspberry == null){					
			map.put("fail", "There is error to get the raspberryPi data.");
		}else{ 
			map.put("success", raspberry);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	@RequestMapping("/raspberrycontrol/load_UnconfirmedRaspberrySA.do")
	public ModelAndView loadUnconfirmedRaspberrySA(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/raspberrycontrol/load_UnconfirmedRaspberrySA.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> raspberryMap = new HashMap<String, Object>();
		
		ArrayList<RaspberrySA> raspberrySAList = (ArrayList<RaspberrySA>) this.raspberryService.getUnconfirmedSAList(rp);
		
		System.out.println("UnconfirmedSAlist = " + raspberrySAList);
		
		if(raspberrySAList.size() == 0){					
			map.put("fail", "There is no Sensor and Actuator.");
		}else{ 
			map.put("success", raspberrySAList);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	@RequestMapping("/raspberrycontrol/ConfirmeRaspberry.do")
	public ModelAndView confirmeRaspberry(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//업데이트를 위한 기본 생성자
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
				
		boolean successupdate =  raspberryService.confirmRaspberry(rp);
	
		if(successupdate) map.put("success", "complete Raspberry info confirm");
		else map.put("fail", "fail to confirm Raspberry info");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}

	
}
