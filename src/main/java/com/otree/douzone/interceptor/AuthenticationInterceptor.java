package com.otree.douzone.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.otree.douzone.dto.Workspace;
import com.otree.douzone.service.WorkspaceService;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	private WorkspaceService workspaceService;
	
	public AuthenticationInterceptor(WorkspaceService workspaceService) {
		this.workspaceService = workspaceService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean result = false;
		if (request.getSession().getAttribute("userId") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			result = false;
		} else  {
			int userId = (int)request.getSession().getAttribute("userId");
			List<Workspace> workspaceList = null;
			workspaceList = workspaceService.getWorkspaceList(userId);
			request.setAttribute("workspaceList", workspaceList);
			result = true;
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
