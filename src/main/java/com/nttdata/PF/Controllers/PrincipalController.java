package com.nttdata.PF.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de la página principal
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/home/")
public class PrincipalController {

	/**
	 * Dirije a la pantalla principal
	 * 
	 * @return String
	 */
	@GetMapping
	public String homePage() {
		return "/home";
	}

}
