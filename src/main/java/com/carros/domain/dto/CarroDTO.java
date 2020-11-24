package com.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private String tipo;
	
	private String url_Foto;
	private String url_Video;
	private String latitude;
	private String longitude;
	
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
	
}
