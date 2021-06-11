package com.ott.setplex.nora.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ConfigProperties {

	private String txtLogin;
	private String txtLoginSuccess;
	private String txtWelcome;
	private String txtFailWelcome;
	private String txtFailLogin;
	private String txtFailLoginDesc;

	public String getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(String txtLogin) {
		this.txtLogin = txtLogin;
	}

	public String getTxtLoginSuccess() {
		return txtLoginSuccess;
	}

	public void setTxtLoginSuccess(String txtLoginSuccess) {
		this.txtLoginSuccess = txtLoginSuccess;
	}

	public String getTxtWelcome() {
		return txtWelcome;
	}

	public void setTxtWelcome(String txtWelcome) {
		this.txtWelcome = txtWelcome;
	}

	public String getTxtFailWelcome() {
		return txtFailWelcome;
	}

	public void setTxtFailWelcome(String txtFailWelcome) {
		this.txtFailWelcome = txtFailWelcome;
	}

	public String getTxtFailLogin() {
		return txtFailLogin;
	}

	public void setTxtFailLogin(String txtFailLogin) {
		this.txtFailLogin = txtFailLogin;
	}

	public String getTxtFailLoginDesc() {
		return txtFailLoginDesc;
	}

	public void setTxtFailLoginDesc(String txtFailLoginDesc) {
		this.txtFailLoginDesc = txtFailLoginDesc;
	}
}
