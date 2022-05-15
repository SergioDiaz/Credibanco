package com.credibanco.assessment.card.controller;

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

import com.credibanco.assessment.card.dto.CardDTO;
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
		try {
			return new ResponseEntity<>(cardService.createNewCard(cardDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("createCard(): {}", e.getMessage());
			return new ResponseEntity<>("createCard():" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// build activate card REST API
	@PutMapping("/{hashCode}")
	public ResponseEntity<?> activateCard(@PathVariable String hashCode, @RequestBody CardDTO cardDTO, 
											HttpServletResponse response){
		try {
			return new ResponseEntity<>(cardService.activateCard(cardDTO, hashCode), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("activateCard(): {}", e.getMessage());
			return new ResponseEntity<>("activateCard():" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// build get card by hash code REST API
	@GetMapping("/{hashCode}")
	public ResponseEntity<?> getCardByHashCode(@PathVariable String hashCode){
		try {
			return new ResponseEntity<>(cardService.getCardByHashCode(hashCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getCardByHashCode(): {}", e.getMessage());
			return new ResponseEntity<>("getCardByHashCode():" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// build delete card code REST API
	@DeleteMapping("/{hashCode}")
	public ResponseEntity<?> deleteCard(@PathVariable String hashCode){
		try {
			cardService.deleteCard(hashCode);
			return new ResponseEntity<>("Tarjeta borrada", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("deleteCard(): {}", e.getMessage());
			return new ResponseEntity<>("deleteCard():" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// build get all cards REST API
	@GetMapping("/cards")
	public ResponseEntity<?> getAllCards(){
		return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
	}
}
