/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.struts;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Juergen Hoeller
 * @since 09.04.2004
 */
public class StrutsSupportTests {

	@Test
	@SuppressWarnings("serial")
	public void actionSupportWithContextLoaderPlugIn() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		ActionSupport action = new ActionSupport() {
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void actionSupportWithRootContext() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		ActionSupport action = new ActionSupport() {
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void dispatchActionSupportWithContextLoaderPlugIn() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		DispatchActionSupport action = new DispatchActionSupport() {
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void dispatchActionSupportWithRootContext() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		DispatchActionSupport action = new DispatchActionSupport() {
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void lookupDispatchActionSupportWithContextLoaderPlugIn() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		LookupDispatchActionSupport action = new LookupDispatchActionSupport() {
			@Override
			protected Map getKeyMethodMap() {
				return new HashMap();
			}
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void lookupDispatchActionSupportWithRootContext() throws ServletException {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.addMessage("test", Locale.getDefault(), "testmessage");
		final ServletContext servletContext = new MockServletContext();
		wac.setServletContext(servletContext);
		wac.refresh();
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};
		LookupDispatchActionSupport action = new LookupDispatchActionSupport() {
			@Override
			protected Map getKeyMethodMap() {
				return new HashMap();
			}
		};
		action.setServlet(actionServlet);

		assertThat(wac).isEqualTo(action.getWebApplicationContext());
		assertThat(servletContext).isEqualTo(action.getServletContext());
		assertThat("testmessage").isEqualTo(action.getMessageSourceAccessor().getMessage("test"));

		action.setServlet(null);
	}

	@Test
	@SuppressWarnings("serial")
	public void testDelegatingActionProxy() throws Exception {
		final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/");
		ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public String getServletName() {
				return "action";
			}
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		ModuleConfig moduleConfig = mock(ModuleConfig.class);
		given(moduleConfig.getPrefix()).willReturn("");

		plugin.init(actionServlet, moduleConfig);
		assertThat(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX)).isNotNull();

		DelegatingActionProxy proxy = new DelegatingActionProxy();
		proxy.setServlet(actionServlet);
		ActionMapping mapping = new ActionMapping();
		mapping.setPath("/test");
		mapping.setModuleConfig(moduleConfig);
		ActionForward forward = proxy.execute(
				mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
		assertThat("/test").isEqualTo(forward.getPath());

		TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/test");
		assertThat(testAction.getServlet()).isNotNull();
		proxy.setServlet(null);
		plugin.destroy();
		assertThat(testAction.getServlet()).isNull();
	}

	@Test
	@SuppressWarnings("serial")
	public void delegatingActionProxyWithModule() throws Exception {
		final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/WEB-INF");
		ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
		plugin.setContextConfigLocation("action-servlet.xml");
		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public String getServletName() {
				return "action";
			}
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		ModuleConfig moduleConfig = mock(ModuleConfig.class);
		given(moduleConfig.getPrefix()).willReturn("/module");

		plugin.init(actionServlet, moduleConfig);
		assertThat(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX)).isNull();
		assertThat(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX + "/module")).isNotNull();

		DelegatingActionProxy proxy = new DelegatingActionProxy();
		proxy.setServlet(actionServlet);
		ActionMapping mapping = new ActionMapping();
		mapping.setPath("/test2");
		mapping.setModuleConfig(moduleConfig);
		ActionForward forward = proxy.execute(
				mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
		assertThat("/module/test2").isEqualTo(forward.getPath());

		TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/module/test2");
		assertThat(testAction.getServlet()).isNotNull();
		proxy.setServlet(null);
		plugin.destroy();
		assertThat(testAction.getServlet()).isNull();

		verify(moduleConfig);
	}

	@Test
	@SuppressWarnings("serial")
	public void delegatingActionProxyWithModuleAndDefaultContext() throws Exception {
		final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/WEB-INF");
		ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
		plugin.setContextConfigLocation("action-servlet.xml");
		ActionServlet actionServlet = new ActionServlet() {
			@Override
			public String getServletName() {
				return "action";
			}
			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		ModuleConfig defaultModuleConfig = mock(ModuleConfig.class);
		given(defaultModuleConfig.getPrefix()).willReturn("");

		ModuleConfig moduleConfig = mock(ModuleConfig.class);
		given(moduleConfig.getPrefix()).willReturn("/module");


		plugin.init(actionServlet, defaultModuleConfig);
		assertThat(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX)).isNotNull();
		assertThat(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX + "/module")).isNull();

		DelegatingActionProxy proxy = new DelegatingActionProxy();
		proxy.setServlet(actionServlet);
		ActionMapping mapping = new ActionMapping();
		mapping.setPath("/test2");
		mapping.setModuleConfig(moduleConfig);
		ActionForward forward = proxy.execute(
				mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
		assertThat("/module/test2").isEqualTo(forward.getPath());

		TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/module/test2");
		assertThat(testAction.getServlet()).isNotNull();
		proxy.setServlet(null);
		plugin.destroy();
		assertThat(testAction.getServlet()).isNull();
	}

}
