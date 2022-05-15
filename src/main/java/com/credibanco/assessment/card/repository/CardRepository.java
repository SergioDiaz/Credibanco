package com.credibanco.assessment.card.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.card.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
	Optional<Card> findByHashCode(String hashCode);
	void deleteByHashCode(String hashCode);
}
