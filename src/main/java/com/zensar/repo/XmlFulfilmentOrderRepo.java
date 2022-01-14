package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.XmlFulfilmentOrderDomain;
@Repository
public interface XmlFulfilmentOrderRepo extends JpaRepository<XmlFulfilmentOrderDomain, Integer> {

}
