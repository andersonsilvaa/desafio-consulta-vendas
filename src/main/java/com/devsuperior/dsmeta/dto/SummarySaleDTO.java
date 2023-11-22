package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummarySaleDTO {

	private String sellerName;
	private Double total;

	public SummarySaleDTO(Sale entity) {
		new ModelMapper().map(entity, this);
	}
}
