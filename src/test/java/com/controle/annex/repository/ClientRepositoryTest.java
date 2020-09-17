package com.controle.annex.repository;

import com.controle.annex.entities.Client;
import com.controle.annex.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    private Long ID = 1L;
    private static final String EMAIl = "emailTestClient@test.com";
    private static final String NAME = "nameTest";
    private static final String FONE = "(11)1.1111-1111";
    private static final String CPF = "123.123.123-12";


    @Autowired
    private ClientRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void initTest(){
        Employee employee = new Employee("name","321.321.321-32","(11)0.0000-0000", "nametest@emailtest", null);
        employeeRepository.save(employee);

        Client client = new Client();
        client.setId(ID);
        client.setEmail(EMAIl);
        client.setCpf(CPF);
        client.setFone(FONE);
        client.setName(NAME);
        client.setEmployee(employee);
        repository.save(client);

    }
    @After
    public void deleteAll(){
        repository.deleteAll();
        employeeRepository.deleteAll();
    }
    @Test
    public void testSave(){
        Employee employee = new Employee("newNAme","777.777.777-77","(77)7.7777-7777", "nametest@emailtest", null);
        employeeRepository.save(employee);

        Client client = new Client();
        client.setId(ID);
        client.setEmail(EMAIl);
        client.setCpf(CPF);
        client.setFone(FONE);
        client.setName(NAME);
        client.setEmployee(employee);
        Client response = repository.save(client);

        assertNotNull(response);
        assertEquals(EMAIl, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(FONE, response.getFone());
        assertEquals(NAME, response.getName());
        assertEquals(employee.getId(), response.getEmployee().getId());

    }
    @Test
    public void testFindById(){
        Optional<Client> findClient = repository.findById(ID);
        assertTrue(findClient.isPresent());
        assertEquals(ID, findClient.get().getId());
    }
}
