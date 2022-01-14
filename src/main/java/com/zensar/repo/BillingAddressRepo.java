package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.BillingAddressDomain;
@Repository
public interface BillingAddressRepo extends JpaRepository<BillingAddressDomain, Integer> {

}
