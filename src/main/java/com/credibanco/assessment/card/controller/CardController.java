package com.credibanco.assessment.card.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.constant.CardResponse;
import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
		 
	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	private CardService cardService;

	public CardController(CardService cardService) {
		super();
		this.cardService = cardService;
	}
	
	// build create new card REST API
	@PostMapping("/create")
	public ResponseEntity<?> createNewCard(@RequestBody CardDTO cardDTO, HttpServletResponse response){
		HashMap<String, Object> mapResponse = cardService.createNewCard(cardDTO);

		if(mapResponse.get("responseCode").equals(CardResponse.CREATED_SUCCESSFUL.getCode())) {
			return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
		}
	}

	// build activate card REST API
	@PutMapping("/activate")
	public ResponseEntity<?> activateCard(@RequestBody CardDTO cardDTO, HttpServletResponse response){
		HashMap<String, Object> mapResponse = cardService.activateCard(cardDTO);
		
		if(mapResponse.get("responseCode").equals(CardResponse.ACTIVATED_SUCCESSFUL.getCode())) {
			return new ResponseEntity<>(mapResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
		}
	}

	// build get card by hash code REST API
	@GetMapping("/{hashCode}")
	public ResponseEntity<?> getCardByHashCode(@PathVariable String hashCode){
		try {
			return new ResponseEntity<>(cardService.getCardByHashCode(hashCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// build delete card code REST API
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCard(@RequestBody CardDTO cardDTO){
		HashMap<String, Object> mapResponse = cardService.deleteCard(cardDTO);
		
		if(mapResponse.get("responseCode").equals(CardResponse.DELETED_SUCCESSFUL.getCode())) {
			return new ResponseEntity<>(mapResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
		}
	}

	// build get all cards REST API
	@GetMapping("/cards")
	public ResponseEntity<?> getAllCards(){
		return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
	}
}
