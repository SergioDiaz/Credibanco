package com.credibanco.assessment.card.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.dto.CardTransactionsDTO;
import com.credibanco.assessment.card.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	// build create new card REST API
	@PostMapping("/create")
	public ResponseEntity<?> createNewTransaction(@RequestBody CardTransactionsDTO cardTransactionsDTO, 
												HttpServletResponse response){
		try {
			return new ResponseEntity<>(transactionService.createNewTransaction(cardTransactionsDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("createCard(): {}", e.getMessage());
			return new ResponseEntity<>("createCard():" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// build activate card REST API
//	@PutMapping("/{hashCode}")
//	public ResponseEntity<?> activateCard(@PathVariable String hashCode, @RequestBody CardDTO cardDTO, 
//											HttpServletResponse response){
//		try {
//			return new ResponseEntity<>(cardService.activateCard(cardDTO, hashCode), HttpStatus.CREATED);
//		} catch (Exception e) {
//			logger.error("activateCard(): {}", e.getMessage());
//			return new ResponseEntity<>("activateCard():" + e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}

}
