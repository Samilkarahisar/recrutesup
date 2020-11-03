package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
