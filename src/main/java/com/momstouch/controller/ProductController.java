package com.momstouch.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.momstouch.web.HomeController;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}

	@RequestMapping(value = "product_detail", method = RequestMethod.GET)
	public String productDetail(Model model) {

		return "/product/productDetail";
	}

	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String category(Model model) {

		return "/product/productKind";
	}

	@RequestMapping(value = "category2", method = RequestMethod.GET)
	public String category2(Model model) {

		return "/product/productKind2";

	}

	@RequestMapping(value = "category3", method = RequestMethod.GET)
	public String category3(Model model) {

		return "/product/productKind3";

	}

}
