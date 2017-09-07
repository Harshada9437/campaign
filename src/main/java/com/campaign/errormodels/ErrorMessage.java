package com.campaign.errormodels;

/**
 * Created by Hp on 07-02-2016.
 */
public class ErrorMessage {
	private String message;

	public ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage{" + "message='" + message + '\'' + '}';
	}
}
