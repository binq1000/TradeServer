package com.design4music.Rest;

import com.design4music.Domain.Flippo;
import com.design4music.Service.FlippoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nekkyou on 20-9-2017.
 */
@RestController
@RequestMapping("/flippo")
public class FlippoRest {

	private FlippoService service;

	public FlippoRest(FlippoService service) {
		this.service = service;
	}

	@ApiOperation(value = "getFlippos", notes = "Get all the Flippos")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Flippo>> getFlippos() {
		List<Flippo> accounts = service.getAllFlippos();
		return new ResponseEntity<List<Flippo>>(accounts, HttpStatus.OK);
	}

	@ApiOperation(value = "getFlippo", notes = "Get a single Flippo by id")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Flippo> getFlippo(@RequestParam("id") long id) {
		Flippo f = service.getFlippo(id);
		return new ResponseEntity<Flippo>(f, HttpStatus.OK);
	}

	@ApiOperation(value = "initFlippos", notes = "Create 75 flippos for testing purposes")
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public void initFlippos() {
		service.initFlippos();
	}
}
