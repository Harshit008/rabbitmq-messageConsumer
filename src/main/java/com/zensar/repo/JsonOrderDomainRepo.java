package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.JsonOrderDomain;
@Repository
public interface JsonOrderDomainRepo extends JpaRepository<JsonOrderDomain, Integer> {

}
