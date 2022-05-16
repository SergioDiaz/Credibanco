package com.credibanco.assessment.card.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.model.Card;

public interface CardService {

	HashMap<String, Object> createNewCard(CardDTO cardDTO);
	HashMap<String, Object> activateCard(CardDTO cardDTO);
	Card getCardByHashCode(String hashCode);
	HashMap<String, Object> deleteCard(CardDTO cardDTO);
	List<CardDTO> getAllCards();
}
