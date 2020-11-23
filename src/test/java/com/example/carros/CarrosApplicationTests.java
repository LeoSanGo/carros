package com.example.carros;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carros.CarrosApplication;
import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class)
class CarrosApplicationTests {

	@Autowired
	private CarroService service;
	
	@Test
	public void testsave() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setDescricao("Versão 2");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		
		Long id = c.getId();
		assertNotNull(id);
		
		Optional<CarroDTO> op = service.getCarroById(id);
		assertTrue(op.isPresent());
		
		c = op.get();
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		assertEquals("Versão 2", c.getDescricao());
		
		service.delete(id);
		
		assertFalse(service.getCarroById(id).isPresent());
	}
	
	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	 
	@Test
	public void testGet() {
		Optional<CarroDTO> op = service.getCarroById(11L);
		
		assertTrue(op.isPresent());
		
		CarroDTO c = op.get();
		
		assertEquals("Ferrari FF", c.getNome());
	}
	
	@Test
	public void testListaPorTipo() {
		
		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());
		
		assertEquals(0, service.getCarrosByTipo("x").size());
	}
	
}
