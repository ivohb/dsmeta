package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public List<Sale> findAll() {
		return repository.findAll();
	}

	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		LocalDate min = minDate.isEmpty() ? today.minusDays(365) : LocalDate.parse(minDate);		
		LocalDate max = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);		
		return repository.findSales(min, max, pageable);
	}

}
