package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.domain.SourceDomain;
@Repository
public interface SourceRepo extends JpaRepository<SourceDomain, Integer> {

}
