package com.br.bhouse.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreatedResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private long id;

	public CreatedResourceEvent(Object source, HttpServletResponse response, long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public long getId() {
		return id;
	}

}
