package com.hessian;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bean.Person;
import com.caucho.hessian.client.HessianProxyFactory;
import com.service.HelloHessian;
import com.service.IRecommendMobileService;
import com.service.MemberRedPackageService;

public class HelloHessianTest {
	@Test
	public void testHessian() {
		try {
//			String url = "http://127.0.0.1:8080/jkkpweb/hessian/helloHessian.do";
			String url = "http://127.0.0.1:8080/jkkpweb/hessian/recommendMobileService.do";
			HessianProxyFactory factory = new HessianProxyFactory();
//			HelloHessian basic = (HelloHessian) factory.create(HelloHessian.class, url);
//			System.out.println("say(): " + basic.sayHello("bruce chen"));
//			 Person bean = basic.getPerson("chen", 28, 1);
//			 System.out.println(bean);
			MemberRedPackageService service = (MemberRedPackageService)factory.create(MemberRedPackageService.class, url);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mobile", "18521003651");
			String name="15911111111";
			IRecommendMobileService iaIRecommendMobileService=(IRecommendMobileService)factory.create(IRecommendMobileService.class,url);
			System.out.println(iaIRecommendMobileService.isvalidate(name));
//			List<MemberRedPackageVO> resultList = memberRedPackageService.findRedPackgeList(params);
//			List<com.bean.MemberRedPackageVO> resultList = service.findMemberPurseRedPackgeList(params);
//			
//			for (com.bean.MemberRedPackageVO memberRedPackageVO : resultList) {
//				System.out.println(memberRedPackageVO.getName());
//				System.out.println(memberRedPackageVO.getPrice());
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
