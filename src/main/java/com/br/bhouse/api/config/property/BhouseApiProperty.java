package com.br.bhouse.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "bhouse")
public class BhouseApiProperty {

	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {

		private boolean enableHttps;
		private String allowedOrigin;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

		public String getAllowedOrigin() {
			return allowedOrigin;
		}

		public void setAllowedOrigin(String allowedOrigin) {
			this.allowedOrigin = allowedOrigin;
		}

	}

}
