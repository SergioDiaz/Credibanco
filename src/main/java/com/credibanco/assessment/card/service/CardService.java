package com.credibanco.assessment.card.service;

import java.util.List;

import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.model.Card;

public interface CardService {

	Card createNewCard(CardDTO cardDTO);
	Object activateCard(CardDTO cardDTO, String hashCode);
	Card getCardByHashCode(String hashCode);
	void deleteCard(String hashCode);
	List<CardDTO> getAllCards();
}
