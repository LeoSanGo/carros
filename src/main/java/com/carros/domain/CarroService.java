package com.carros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public Iterable<Carro> getCarros() {
		return rep.findAll();
	}

	public Optional<Carro> getCarroById(Long id) {

		return rep.findById(id);
	}
	
	public List<Carro> getCarrosByTipo(String tipo) {
		
		return rep.findByTipo(tipo);
	}
	
	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
		return rep.save(carro);
		
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel inserir o registro");
		Optional<Carro> optional = getCarroById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			rep.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarroById(id);
		if (carro.isPresent()) {
			rep.deleteById(id);
		}
	}

}
