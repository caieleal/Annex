package com.controle.annex;

import com.controle.annex.entities.Client;
import com.controle.annex.entities.Employee;
import com.controle.annex.entities.Manufacturer;
import com.controle.annex.repository.ClientRepository;
import com.controle.annex.repository.EmployeeRepository;
import com.controle.annex.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnnexApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	public static void main(String[] args) {
		SpringApplication.run(AnnexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Employee employee = new Employee();
		employee.setNome("teste");
		employee.setCpf("123.155.144.75");
		employee.setTelefone("(11)91111-1111");
		employee.setEmail("teste@teste.com");
        repository.save(employee);

		Client client = new Client();
		client.setName("clientTest");
		client.setFone("(11)9.1111-1111");
		client.setCpf("222.222.222-22");
		client.setEmail("clientTest@test.com");
		client.setEmployee(employee);
		clientRepository.save(client);

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName("manufacturer");
		manufacturerRepository.save(manufacturer);




    }
}
