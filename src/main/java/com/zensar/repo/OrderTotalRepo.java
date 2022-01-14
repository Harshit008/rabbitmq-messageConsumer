package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.OrderTotalsDomain;
@Repository
public interface OrderTotalRepo extends JpaRepository<OrderTotalsDomain, Integer> {

}
