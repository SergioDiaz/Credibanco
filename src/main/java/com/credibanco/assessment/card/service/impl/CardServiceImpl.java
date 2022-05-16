package com.credibanco.assessment.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.constant.CardErrors;
import com.credibanco.assessment.card.constant.CardResponse;
import com.credibanco.assessment.card.constant.CardUtilConstants;
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
	
	Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);
	
	public CardServiceImpl(CardRepository cardRepository) {
		super();
		this.cardRepository = cardRepository;
	}

	@Override
	public HashMap<String, Object> createNewCard(CardDTO cardDTO) {
		Random random = new Random();
		Integer randomInt = random.nextInt(CardUtilConstants.END_RANDOM - CardUtilConstants.START_RANDOM) + 1;
		
		HashMap<String, Object> mapResponse;
		if(Boolean.TRUE.equals(validateNewCard(cardDTO))) {
			cardDTO.setCardMasked(maskCardNumber(cardDTO.getPan()));
			cardDTO.setCardHashCode(DigestUtils.sha256Hex(cardDTO.getPan()));
			cardDTO.setCardValidationNumber(randomInt);
			
			Card card = cardRepository.save(convertDtoToEntity(cardDTO));
			
			mapResponse = formatResponse(CardResponse.CREATED_SUCCESSFUL);
			mapResponse.put("hashCode",card.getHashCode());
			mapResponse.put("validationNumber",card.getValidationNumber());
			mapResponse.put("maskedPan",card.getMasked());
		}else {
			mapResponse = formatResponse(CardResponse.CREATED_FAILED);
		}

		return mapResponse;
	}
	
	public HashMap<String, Object> activateCard(CardDTO cardDTO) {
		HashMap<String, Object> mapResponse;
		Optional<Card> optionalCard = cardRepository.findByHashCode(cardDTO.getCardHashCode());

		if(optionalCard.isEmpty()) {
			return formatResponse(CardResponse.NOT_EXISTS);
		}

		Card card = optionalCard.get();

		if(cardDTO.getCardValidationNumber().equals(card.getValidationNumber())) {
			if(Boolean.TRUE.equals(card.getActive())) {
				return formatResponse(CardResponse.CARD_ALREADY_ACTIVE);
			}
			card.setStatus("ACTIVADA");
			card.setActive(true);

			mapResponse = formatResponse(CardResponse.ACTIVATED_SUCCESSFUL);
			mapResponse.put("maskedPan",card.getMasked());

			return mapResponse;
		}else {
			return formatResponse(CardResponse.INVALID_VALIDATION_NUMBER);
 		}
	}

	public Card getCardByHashCode(String hashCode) {
		Optional<Card> optionalCard = cardRepository.findByHashCode(hashCode);

		if(optionalCard.isEmpty()) {
			throw new ResourceNotFoundException("getCardByHashCode()", "hashCode", hashCode);
		}
		return optionalCard.get();
	}

	@Override
	public HashMap<String, Object> deleteCard(CardDTO cardDTO) {
		Optional<Card> optionalCard = cardRepository.findByHashCode(cardDTO.getCardHashCode());

		if(optionalCard.isEmpty()) {
			return formatResponse(CardResponse.NOT_EXISTS);
		}
		
		Card card = optionalCard.get();
		
		if(cardDTO.getCardValidationNumber().equals(card.getValidationNumber())) {
			cardRepository.deleteByHashCode(cardDTO.getCardHashCode());
			return formatResponse(CardResponse.DELETED_SUCCESSFUL);
		}else {
			return formatResponse(CardResponse.INVALID_VALIDATION_NUMBER);
		}
	}

	@Override
	public List<CardDTO> getAllCards(){
		return cardRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	private HashMap<String, Object> formatResponse(CardResponse cardResponse){
		
		HashMap<String, Object> mapResponse = new HashMap<>();
		logger.info(cardResponse.getDescription());
		mapResponse.put("message",cardResponse.getDescription());
		mapResponse.put("responseCode",cardResponse.getCode());
		return mapResponse;
	}
	
	private Boolean validateNewCard(CardDTO cardDTO) {

		if(cardDTO.getPan().isEmpty()){
			logger.error(CardErrors.CARD_IS_EMPTY);
			return false;
		}
		if(!StringUtils.isNumeric(cardDTO.getPan())) {
			logger.error(CardErrors.CARD_FORMAT_NOT_VALID);
			return false;
		}
		if(cardDTO.getOwnerFirstName().isEmpty()) {
			logger.error(CardErrors.FIRST_NAME_EMPTY);
			return false;
		}
		if(cardRepository.findByHashCode(DigestUtils.sha256Hex(cardDTO.getPan())).isPresent()) {
			logger.error(CardErrors.CARD_ALREADY_EXISTS);
			return false;
		}
		if(CardUtilConstants.CARD_LENGTH != cardDTO.getPan().length()) {
			logger.warn("Pan should have 16 digits");
		}
		return true;
	}
	
	private String maskCardNumber(String cardNumber) {
		int start = CardUtilConstants.FIRST_MASK;
		int end = cardNumber.length() - CardUtilConstants.LAST_MASK;
		StringBuilder maskedNumber = new StringBuilder(end - start);

		for(int i = CardUtilConstants.ZERO; i < (end - start); i++) {
			maskedNumber.append(CardUtilConstants.STAR);
		}
		return cardNumber.substring(CardUtilConstants.ZERO, start) + maskedNumber.toString() + cardNumber.substring(end);
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
