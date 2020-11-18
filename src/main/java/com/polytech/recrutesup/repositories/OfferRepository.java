package com.polytech.recrutesup.repositories;

import com.polytech.recrutesup.entities.Attachment;
import com.polytech.recrutesup.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findById(Long id);

    List<Offer> findAllByCompany(Company company);


}
