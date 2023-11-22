package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummarySaleDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.utils.DateUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        return new SaleMinDTO(result.get());
    }

    public Page<SaleMinDTO> findReport(String minDate, String maxDate, String name, Pageable page) {

        LocalDate min = calculateDateMinimum(minDate);
        LocalDate max = calculateDateMaximum(maxDate);
        return repository.findReport(min, max, name, page);
    }

    public Page<SummarySaleDTO> findSummary(String minDate, String maxDate, Pageable page) {

        LocalDate min = calculateDateMinimum(minDate);
        LocalDate max = calculateDateMaximum(maxDate);
        return repository.findSummary(min, max, page);
    }

    private LocalDate calculateDateMinimum(String minDate) {

        if (minDate.isEmpty()) {
            return LocalDate.now().minusYears(1L);
        } else {
            return DateUtilsService.stringToLocalDate(minDate);
        }
    }

    private LocalDate calculateDateMaximum(String maxDate) {

        if (maxDate.isEmpty()) {
            return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        } else {
            return DateUtilsService.stringToLocalDate(maxDate);
        }
    }
}
