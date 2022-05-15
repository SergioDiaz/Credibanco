package com.credibanco.assessment.card.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.controller.CardController;
import com.credibanco.assessment.card.dto.CardDTO;
import com.credibanco.assessment.card.exceptions.ResourceNotFoundException;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.CardRepository;
import com.credibanco.assessment.card.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	public CardServiceImpl(CardRepository cardRepository) {
		super();
		this.cardRepository = cardRepository;
	}

	@Override
	public Card createNewCard(CardDTO cardDTO) {
		Random random = new Random();
		Integer randomInt = random.nextInt(100 - 1) + 1;

		cardDTO.setCardMasked("1231****23123");
		cardDTO.setCardHashCode("sdsaqwe312341as");
		cardDTO.setCardValidationNumber(randomInt);
		
		Card card = convertDtoToEntity(cardDTO);

		return cardRepository.save(card);
	}
	
	public Object activateCard(CardDTO cardDTO, String hashCode) {
		Card card = getCardByHashCode(hashCode);
		if(cardDTO.getCardValidationNumber().equals(card.getValidationNumber())) {
			if(Boolean.TRUE.equals(card.getActive())) {
				logger.info("Tarjeta previamente activada");
				return "Tarjeta previamente activada";
			}
			logger.info("Tarjeta activada");
			card.setStatus("ACTIVADA");
			card.setActive(true);
			return cardRepository.save(card);
		}else {
			logger.info("Número de validación inválido");
			return "Número de validación inválido";
		}
	}

	public Card getCardByHashCode(String hashCode) {
		Optional<Card> card = cardRepository.findByHashCode(hashCode);
		if(card.isPresent()) {
			return card.get();
		}else {
			throw new ResourceNotFoundException("Tarjeta no existe");
		}
	}

	@Override
	public void deleteCard(String hashCode) {
		cardRepository.deleteByHashCode(hashCode);
	}

	@Override
	public List<CardDTO> getAllCards(){
		return cardRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	private CardDTO convertEntityToDto(Card card) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(card, CardDTO.class);
	}

	private Card convertDtoToEntity(CardDTO cardDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(cardDTO, Card.class);
	}

}
