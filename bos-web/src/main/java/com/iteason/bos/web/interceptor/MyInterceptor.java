	package com.iteason.bos.web.interceptor;
	
	import com.opensymphony.xwork2.ActionContext;
	import com.opensymphony.xwork2.ActionInvocation;
	import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
	
	public class MyInterceptor extends MethodFilterInterceptor {
	
		@Override
		protected String doIntercept(ActionInvocation invocation) throws Exception {
			
			//如果没有登陆，就让其登陆
			Object object = ActionContext.getContext().getSession().get("loginUser");
			if(object != null){
				//放行
				return invocation.invoke();
			}else{
				return "toLogin";
			}
			
		}
	
		 
	}
