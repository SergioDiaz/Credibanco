package com.credibanco.assessment.card.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardTransactionsDTO;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.repository.CardRepository;
import com.credibanco.assessment.card.repository.TransactionRepository;
import com.credibanco.assessment.card.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public Transaction createNewTransaction(CardTransactionsDTO cardTransactionsDTO) {
		
		Transaction transaction = convertDtoToEntity(cardTransactionsDTO);

		return transactionRepository.save(transaction);
	}

	private CardTransactionsDTO convertEntityToDto(Transaction transaction) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(transaction, CardTransactionsDTO.class);
	}

	private Transaction convertDtoToEntity(CardTransactionsDTO cardTransactionsDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(cardTransactionsDTO, Transaction.class);
	}

}
