package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.AddressDomain;
@Repository
public interface AddressRepo extends JpaRepository<AddressDomain, Integer> {

}
