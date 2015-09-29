package com.jkkp.pc.saleActivity.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.ISaleActivityService;
import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.service.IActivityJionSignService;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.IAdminService;
import com.jkkp.pc.common.utils.HttpRequestDeviceUtils;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.MathUtils;

@Controller
@RequestMapping("/main/activity")
public class wxSaleActivityController extends BaseController {

	@Autowired
	private IActivityJionSignService activityJionSignService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private ISaleActivityService saleActivityService;
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping("index.do")
	public String index(HttpServletRequest request,HttpServletResponse response) throws IOException{
		boolean flag = HttpRequestDeviceUtils.isMobileDevice(request);
		if(flag){
			String url = Sysconfig.CONFIG_PARAM.get(ConfigConstant.WEIXIN_URL);
			if(!CheckedUtil.isNotEmpty(url)){
				url = "http://www.jiakeke.com/jkkpwxweb/activity/index.xhtml";
			}
			response.sendRedirect(url);
		}
		return "/pc/other/revolution";
	}
	
	@ResponseBody
	@RequestMapping("enjoin.do")
	public Object enjoin(HttpServletRequest request){
		try {
			if(request.getParameter("phone").isEmpty()){
				return new ResponseObject(false, "请您填写电话号码");
			}
			if(request.getParameter("name").isEmpty()){
				return new ResponseObject(false, "请您填写称呼");
			}
			String phone=request.getParameter("phone");
			String name=request.getParameter("name");
			Integer decoration=CommonUtil.stringToInteger(request.getParameter("decoration"));
			ActivityJionSign bean=new ActivityJionSign();
			bean.setName(name);
			bean.setPhone(phone);
			bean.setActivityId(1);
			bean.setSex(0);
			bean.setDecoration(decoration);
			bean.setJoinTime(new Date());
			bean.setSpId(0);
			
			// 分配给一个客服跟进-------->
			int rid = Integer.valueOf(Sysconfig.CONFIG_PARAM.get(ConfigConstant.SERVICER_RID));
			Admin admin = new Admin();
			admin.setRid(rid);
			List<Admin> list = adminService.select(admin);
			if (!list.isEmpty()) {
				int[] rids = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Admin ad = list.get(i);
					rids[i] = ad.getId();
				}
				MathUtils.bubbleSort(rids);
				Integer adminId = saleActivityService.getLastAdminId();
				if (adminId != null && adminId > 0) {
					for (int i = 0; i < rids.length; i++) {
						if (adminId == rids[rids.length - 1]) {
							bean.setAdminId(rids[0]);
							break;
						}
						if (rids[i] == adminId) {
							bean.setAdminId(rids[i + 1]);
							break;
						}
					}
				} else {
					bean.setAdminId(rids[0]);
				}
			}
			
			Integer rel=activityJionSignService.signIn(bean, "", "", 0, 1);
			addPhoneToMember(bean.getPhone());
			if(rel==1){
				return new ResponseObject(false, "您已报名,无需重复报名");
			}
			SendMsgUtil.send(bean.getPhone(), "恭喜您成功报名9月5日举办的2015家居建材采购大会天津站(西青区知景道198号,社会山国际会议中心三层)。");
			SendMsgUtil.send(bean.getPhone(), "采购大会价格低于市场价20~40%!有疑问请拨打4001118108【家可可】");
			return new ResponseObject(true, "报名成功,扫描二维码,升级VIP,享受VIP特权");
		} catch (Exception e) {
			e.printStackTrace();
		  logger.error("微信引流活动web报名报错");
		  return new ResponseObject(false, "报名出错");
		}
	}
	
	/**
	 * 微信报名用户添加至加\家可可平台会员
	 * @param phone
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public void addPhoneToMember(String phone) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		List<String> list = memberService.findAllPhone();
		if(!list.contains(phone)){
			Member member = new Member();
			member.setMobile(phone);
			member.setNickname("家可可用户");
			String pass = Sysconfig.CONFIG_PARAM.get(ConfigConstant.INIT_PASSWORD);
			if(pass == null){
				pass = "a1234567";
			}
			member.setPassword(CommonUtil.md5(pass));
			memberService.save(member);
		}
	}
	
}
