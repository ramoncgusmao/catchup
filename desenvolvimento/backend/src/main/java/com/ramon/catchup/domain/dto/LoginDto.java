package com.ramon.catchup.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cpf;
	private String senha;
	
	
	
	
}
