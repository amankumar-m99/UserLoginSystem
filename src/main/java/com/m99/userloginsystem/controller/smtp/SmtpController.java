package com.m99.userloginsystem.controller.smtp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m99.userloginsystem.entity.smtp.Smtp;
import com.m99.userloginsystem.service.smtp.SmtpService;
import com.m99.userloginsystem.utils.ConsolePrinter;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/smtp")
@PreAuthorize("hasRole('admin')")
@CrossOrigin
public class SmtpController {

	@Autowired
	private SmtpService smtpService;

	@PostConstruct
	public void initSmtp() {
		ConsolePrinter.printInfo("Initialising SMTP...");
		try {
			smtpService.initSmtps();
		} catch (IOException e) {
			ConsolePrinter.printException(e, "Couldn't initialize smtp due to IO exception.");
		}
	}

	@PostMapping("/add")
	public Smtp add(@RequestBody Smtp smtp) {
		return smtpService.add(smtp);
	}

	@GetMapping("/get-all")
	public List<Smtp> getAllSmtp(){
		return smtpService.getAllSmtp();
	}

	@GetMapping("/get/{id}")
	public Smtp getSmtp(@RequestParam int id){
		return smtpService.getById(id);
	}

	@PutMapping("/edit/{id}")
	@PatchMapping("/edit/{id}")
	public Smtp editSmtp(@RequestParam int id){
		return smtpService.getById(id);
	}

	@PutMapping("/mark-selected")
	@PatchMapping("/mark-selected")
	public void markSelected(@RequestBody Smtp smtp){
		smtpService.markSelected(smtp.getId());
	}

	@DeleteMapping("/delete/{id}")
	public Smtp deleteSmtp(@RequestParam int id){
		return smtpService.deleteById(id);
	}
}
