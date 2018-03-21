package com.sia.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sia.core.Spittle;
import com.sia.core.SpittleForm;
import com.sia.interfaces.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	@Autowired
	private SpittleRepository spittleRepository;

	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	// @RequestMapping(method = GET)
	// public String spittles(Model model) {
	// model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// return "spittles";
	// }

	// @RequestMapping(method = GET)
	// public String spittles(Model model) {
	// model.addAttribute("spittleList",
	// spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// return "spittles";
	// }

	// @RequestMapping(method = GET)
	// public String spittles(Map model) {
	// model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// return "spittles";
	// }

	// @RequestMapping(method = GET)
	// public List<Spittle> spittles() {
	// return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
	// }

	// @RequestMapping(method = GET)
	// public List<Spittle> spittles(@RequestParam("max") long max,
	// @RequestParam("count") int count) {
	// return spittleRepository.findSpittles(max, count);
	// }

	// @RequestMapping(method = GET)
	// public List<Spittle> spittles(@RequestParam(value = "max", defaultValue =
	// MAX_LONG_AS_STRING) long max,
	// @RequestParam(value = "count", defaultValue = "20") int count) {
	// return spittleRepository.findSpittles(max, count);
	// }

	@RequestMapping(method = GET)
	public String spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count, Model model) {
		model.addAttribute(spittleRepository.findSpittles(max, count));
		return "spittles";
	}

	@RequestMapping(value = "/{spittleId}", method = GET)
	public String spittle(@PathVariable long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(method = POST)
	public String saveSpittle(SpittleForm form, Model model) throws Exception {
		spittleRepository
				.save(new Spittle(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
		return "redirect:/spittles";
	}
}
