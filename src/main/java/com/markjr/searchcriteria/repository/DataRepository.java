package com.markjr.searchcriteria.repository;

import com.markjr.searchcriteria.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long>, JpaSpecificationExecutor<Data> {
}
