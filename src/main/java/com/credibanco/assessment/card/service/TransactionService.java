package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardTransactionsDTO;
import com.credibanco.assessment.card.model.Transaction;

public interface TransactionService {

	Transaction createNewTransaction(CardTransactionsDTO cardTransactionsDTO);
}
