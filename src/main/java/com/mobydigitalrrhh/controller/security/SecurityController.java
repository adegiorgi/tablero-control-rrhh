package com.mobydigitalrrhh.controller.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@PostMapping("/oauth/app_token")
	private String getAppToken() {
		return "";
	}
	
}
