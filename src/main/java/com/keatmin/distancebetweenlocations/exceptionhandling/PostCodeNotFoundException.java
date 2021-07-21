package com.keatmin.distancebetweenlocations.exceptionhandling;

public class PostCodeNotFoundException extends RuntimeException {

	public PostCodeNotFoundException(String code) {
		super("Post code not found : " + code);
	}

}
