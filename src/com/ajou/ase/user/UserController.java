package com.ajou.ase.user;

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
import com.ajou.ase.user.User;
import com.ajou.ase.user.UserServiceImpl;


@Controller
public class UserController {
	
	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	/**
	 * 11.4. 봉재 - 사용자 로그인 로직 : 세션 정보에 SSID를 추가하였음 
	 * */
	@RequestMapping("/user/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		System.out.println("--------------------------/user/login.do---------------------------"); 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		
		User user = (User) this.userService.login(rp);
		
		if(user != null){
			userMap.put("userSeq", user.getUserNumSeq());
			userMap.put("userID", user.getUserID());
			userMap.put("userServerAdmin", user.getUserServerAdmin());
			user.setUserLastSSID(rp.get("userSSID").toString());
			this.userService.updateSSID(user);
			userMap.put("userSSID", user.getUserLastSSID());
			
			//Session data sending

			map.put("success", userMap);
		}else{
			map.put("fail", "Login Fail");
		}
		
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
	
	// 11. 2. 봉재 - ID 유일성 체크   
	
	@RequestMapping("/user/user_checkUniqueId.do")
	public ModelAndView checkUniqueId(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
							
		System.out.println("---------------------------/user/user_checkUniqueId.do---------------------------");
		System.out.println("rp = " + rp);
		
		//클라이언트로부터 파라미터 받은 ID를 저장 및 확인
		String currentId = rp.get("userID").toString();
		System.out.println("Input ID= "+ currentId);
		
		//클라이언트로 파라미터를 받은 것을 바탕으로 query 날려서 null 인지 아니면 값이 있는지를 확인
		if(this.userService.getObjectForIdcheck(rp) == null)
		{
			System.out.println("No same ID");
			map.put("success", "No same ID");
		
		}else
		{
			System.out.println("Exist same ID");
			map.put("fail", "Exist same ID");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	// 11.3 봉재 - 사용자 등록 로직 구현
	@RequestMapping("/user/user_registration.do")
	public ModelAndView userRegistration(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		
		//체커
		System.out.println("---------------------------/user/user_registration.do---------------------------");
		System.out.println("rp = "+ rp);
		
		user.setUserID(rp.get("userID").toString());
		user.setUserPassword(rp.get("userPassword").toString());
		user.setUserName(rp.get("userName").toString());
		user.setUserPhoneNumber(rp.get("userPhoneNumber").toString());
		user.setUserBirthDate(rp.get("userBirthdate").toString());
		user.setUserPrivilege(rp.get("userPrivilege").toString());
		user.setUserServerAdmin(rp.get("userServerAdmin").toString());
		
		int intUserPrivilege = Integer.parseInt(user.getUserPrivilege());
		if (intUserPrivilege == 2){
			user.setUserConfirmed(1);
			System.out.println("확정= "+ user.getUserConfirmed());
		}else{
			user.setUserConfirmed(0);
			System.out.println("미확정= "+ user.getUserConfirmed());
		}		
		
		//데이터 주입 확인
		System.out.println("이름= "+ user.getUserName());
		System.out.println("아뒤= "+ user.getUserID());
		System.out.println("암호= "+ user.getUserPassword());
		System.out.println("폰번= "+ user.getUserPhoneNumber());
		System.out.println("태어난일= "+ user.getUserBirthDate());
		System.out.println("사용자 권한= "+ user.getUserPrivilege());
		System.out.println("사용자 속한 사람= "+ user.getUserServerAdmin());
		System.out.println("현재 확정상태= "+ user.getUserConfirmed());
						
		//성공이후 userService의 save를 이용하여 insert SQL명령 실행
		userService.save(user);
		
		//성공여부에 따라 map으로 k와 v값을 각각 주입
		if(user !=null)	map.put("success", "Success to Registration");
		else map.put("fail", "Fail to Registration");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));

		return mnv;
	}
	
	// 11.5 봉재 - 시스템 관리자에 의해 새로운 사용자가 시스템 등록 이후 확정 대기 리스트 가져오는 로직  

	@RequestMapping("/user/user_getconfirmationlist.do")
	public ModelAndView getConfirmationList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		
		//체커 : 데이터가 잘 넘어오는지 확인 
		System.out.println("-------------getConfirmationList()--------------");
		System.out.println(rp);
				
		ArrayList<User> userList = (ArrayList<User>) this.userService.getListByUnConfirmed(rp);
		
		System.out.println(userList);

		if(userList.size() == 0){					
			map.put("fail", "There is no confirmation waiting member.");
		}else{ 
			map.put("success", userList);
		}
		System.out.println("map =" +map);
				
		//mnv로 userMap을 가지고 있는 map을 전송 
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	// 11.5 봉재 - 시스템 관리자가 새로게 등록한 사용자를 승인한후 submit하는 로직
	
	@RequestMapping("/user/user_updateconfirmationinfo.do")
	public ModelAndView updateConfirmationInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//업데이트를 위한 기본 생성자
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
				
		// 모델 생성
		User user = new User();
		
		System.out.println("-------------updateconfirmationInfo()--------------");
		System.out.println("rp = "+ rp);
		
		// 세터를 이용하여 모델로 데이터 주입
		user.setUserID(rp.get("userID").toString());

		System.out.println("아뒤= "+ user.getUserID());
		
		boolean checker = userService.updateConfirmationInfo(user);
		
		// 성공여부에 따라 map으로 k와 v값을 각각 주입
		
		if(checker)	map.put("success", "Confirmation Success");
		else map.put("fail", "Confirmation Fail");
		
		// client로 리턴 : 클라이언트로 데이터를 보내기 위해 map과 callback을 전송하기 위해 mnv에 값 주입 후 리턴
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}

	// 10.9 봉재 : 업데이트 전 아이디 바탕으로 유저정보 가져오기
	
	@RequestMapping("/user/getUserInfo.do")
	public ModelAndView getUserInformation(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//생성자 : 기본 rp,mnv(리턴용), map 외에, userMap HashMap(실제 데이터 저장) 추가 
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();

		
		//체커 : 데이터가 잘 넘어오는지 확인 
		System.out.println("-------------getUserInfo()--------------");
		System.out.println("rp = " + rp);
		
		//데이터 주입 
		User user = (User) this.userService.getObject(rp);
		
		//체커 : 데이터 모델로 주입 확인
		System.out.println("특권= "+ user.getUserPrivilege());
		System.out.println("아뒤= "+ user.getUserID());

		//모델에서 리턴을 위한 userMap으로 다시 주입(매핑)
		if(user != null){
			userMap.put("userCellPhone", user.getUserPhoneNumber());
			userMap.put("userServerAdmin", user.getUserServerAdmin());
			userMap.put("userprivilege", user.getUserPrivilege());
			map.put("success", userMap);
		}else{
			map.put("fail", "fail to get user information");
		}
		
		//mnv로 userMap을 가지고 있는 map을 전송 
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	
	// 10.9 봉재 - 유저정보 업데이트 누른다음 시작
	@RequestMapping("/user/updateUserInfo.do")
	public ModelAndView updateUserInformation(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//업데이트를 위한 기본 생성자
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
				
		// 모델 생성
		User user = new User();

		// 세터를 이용하여 모델로 데이터 주입
		user.setUserID(rp.get("userID").toString());
		user.setUserPassword(rp.get("userPassword").toString());
		user.setUserPhoneNumber(rp.get("userPhoneNumber").toString());
		user.setUserServerAdmin(rp.get("userServerAdmin").toString());
				
		// 체커
		System.out.println("-------------updateUserInfo()--------------");
		System.out.println("rp = "+ rp);
		System.out.println("아뒤= "+ user.getUserID());
		System.out.println("암호= "+ user.getUserPassword());
		System.out.println("폰번= "+ user.getUserPhoneNumber());
		System.out.println("서버관리자= "+ user.getUserServerAdmin());
		
		// 주입 
		boolean successupdate =  userService.userinfoupdate(user);
	
		if(successupdate) map.put("success", "complete user info update");
		else map.put("fail", "fail to update user info");
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		return mnv;
	}

}
