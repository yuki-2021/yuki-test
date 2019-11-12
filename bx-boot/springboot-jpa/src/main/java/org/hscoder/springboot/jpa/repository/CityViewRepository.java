package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.domain.CityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityViewRepository extends JpaRepository<CityView, Long> {

}
