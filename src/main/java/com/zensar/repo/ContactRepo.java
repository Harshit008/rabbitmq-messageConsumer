package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.ContactDomain;
@Repository
public interface ContactRepo extends JpaRepository<ContactDomain, Integer> {

}
