package com.ajou.ase.raspberrycontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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

	//참고로 이 로직은 Find Raspberry 누른뒤에도 적용되지만 RaspberryInfo가져오는 로직에도 적용한다. 
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
	
	@RequestMapping("/raspberrycontrol/RemoveRaspberry.do")
	public ModelAndView removeRaspberry(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		
		System.out.println("-------------/raspberrycontrol/RemoveRaspberry.do--------------"); 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		//여기서는 remopve라고 사용하지만, 실제로 여기서는 권한값을 0으로 주고, server admin 값을 없앤다. 
		this.raspberryService.removeRaspberryInfo(rp);

	//	rp.put("boardRootUpper", rp.get("boardSeqNum").toString());

		map.put("success", "success");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	@RequestMapping("/raspberrycontrol/load_AllDisplayList.do")
	public ModelAndView loadAllDisplayList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		

		System.out.println("-------------/raspberrycontrol/load_AllDisplayList.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> resultList = new HashMap<String, Object>();
		
		// http request에 대한 요청값(server admin 과 status값)을 가지고 라즈베리 정보 가져오기
		ArrayList<Raspberry> raspberryList = (ArrayList<Raspberry>) this.raspberryService.getConfirmedList(rp);
		ArrayList<RaspberrySA> raspberrySAList = new ArrayList<RaspberrySA>(); 
				
		// 위의 결과 나온 라즈베리파이의 시리얼 정보로 모든 SA 정보 가져오기 
		// 가져오고 난다음 addAll을 사용하여 모든 데이터를 다 붙여넣었음(이렇게 안하면 이전에 했던것들이 사라짐)
		for(int i=0; i < raspberryList.size();i++){
			param.put("raspberryNumSN", raspberryList.get(i).getRaspberryNumSN());
			raspberrySAList.addAll((ArrayList<RaspberrySA>) this.raspberryService.getSAListBySerialNumber(param));
		}
		// 두개의 정보를 조합해야해야하기 때문에 resultList를 먼저 선언하고 resultSet을 매 순환마다 새로 선언하여 주입 
		for(int i=0; i < raspberrySAList.size();i++){
			Map<String, String> resultSet = new HashMap<String, String>();
			resultSet.put("saNumSeq", String.valueOf(raspberrySAList.get(i).getSaNumSeq()));
			resultSet.put("saType", raspberrySAList.get(i).getSaType());
			resultSet.put("saRaspberrySN", raspberrySAList.get(i).getSaRaspberrySN());
			resultSet.put("saUpdateValue", raspberrySAList.get(i).getSaUpdateValue());
			for(int l=0; l<raspberryList.size();l++){
				if(raspberryList.get(l).getRaspberryNumSN().equals(raspberrySAList.get(i).getSaRaspberrySN()))
					resultSet.put("raspberryID", raspberryList.get(l).getRaspberryID());
			}
			resultList.put(String.valueOf(i), resultSet);
		}

		if(resultList.size() == 0){					
			map.put("fail", "There is no SA.");
		}else{ 
			map.put("success", resultList);
		}
	
		System.out.println("map =" +map);
			
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
				
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	
	@RequestMapping("/raspberrycontrol/load_SAvalueinfo.do")
	public ModelAndView loadSAvalueinfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		

		System.out.println("-------------/raspberrycontrol/load_SAvalueinfo.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> resultList = new HashMap<String, Object>();
		
		// http request에 대한 요청값(server admin 과 status값)을 가지고 라즈베리 정보 가져오기
		ArrayList<Raspberry> raspberryList = (ArrayList<Raspberry>) this.raspberryService.getConfirmedList(rp);
		ArrayList<RaspberrySA> raspberrySAList = new ArrayList<RaspberrySA>(); 
		
		String gettingParameter = rp.get("saNumSeq").toString();
		StringTokenizer st = new StringTokenizer(gettingParameter, ",");

		// 가져온 파라미터를 정수값으로 변경시켜서 하나하나 쿼리를 날려야 함 
		
		while(st.hasMoreTokens()){
			int numSeq = Integer.parseInt(st.nextToken());
			System.out.println(numSeq);
			RaspberrySA raspberrysa = new RaspberrySA();
			raspberrysa.setSaNumSeq(numSeq);
			raspberrySAList.addAll(raspberryService.getSAListByRelatedSeqNum(raspberrysa));
			
		}
		

		for(int i=0; i < raspberrySAList.size() ; i++){
			Map<String, String> resultSet = new HashMap<String, String>();
			for(int l=0; l < raspberryList.size() ; l++){
				if(raspberryList.get(l).getRaspberryNumSN().equals(raspberrySAList.get(i).getSaRaspberrySN())){
					resultSet.put("saNumSeq", String.valueOf(raspberrySAList.get(i).getSaNumSeq()));
					resultSet.put("saUpdateValue", raspberrySAList.get(i).getSaUpdateValue());
					resultSet.put("saType", raspberrySAList.get(i).getSaType());
					resultSet.put("raspberryID", raspberryList.get(l).getRaspberryID());
				}
			}
			resultList.put(String.valueOf(i), resultSet);
		}

		if(resultList.size() == 0){					
			map.put("fail", "There is no SA.");
		}else{ 
			map.put("success", resultList);
		}
		
		System.out.println("map =" +map);
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	@RequestMapping("/raspberrycontrol/load_ConfirmedListWithSSID.do")
	public ModelAndView loadConfirmedListWithSSID(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");

		System.out.println("-------------/raspberrycontrol/load_ConfirmedListWithSSID.do--------------");
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> raspberryMap = new HashMap<String, Object>();
		
		ArrayList<Raspberry> raspberryList = (ArrayList<Raspberry>) this.raspberryService.getConfirmedListWithSSID(rp);
		
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
	
}
