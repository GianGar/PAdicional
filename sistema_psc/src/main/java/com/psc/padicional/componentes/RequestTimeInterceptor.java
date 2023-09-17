package com.psc.padicional.componentes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.psc.padicional.repositorios.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (long)request.getAttribute("startTime");
		String url = request.getRequestURL().toString();
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//String username= "";
		//if (null != auth && auth.isAuthenticated()) {
		//	username = auth.getName();
		//	}
		
		LOG.info("Url a: '"+url+"' en "+(System.currentTimeMillis()-startTime)+ " ms.");
		//GUARDAR LOG EN BASE... logRepository.save(new com.psc.padicional.entidades.Log(new Date(),auth.getDetails().toString(),username,url));
			}

}
