package com.sia.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sia.core.Spitter;
import com.sia.core.SpitterForm;
import com.sia.interfaces.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new SpitterForm());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid SpitterForm spitterForm, Errors errors)
			throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		Spitter spitter = spitterForm.toSpitter();
		spitterRepository.save(spitter);
		MultipartFile profilePicture = spitterForm.getProfilePicture();
		profilePicture.transferTo(new File("C:\\spittrdata\\uploads\\" + spitter.getUsername() + ".jpg"));
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}
