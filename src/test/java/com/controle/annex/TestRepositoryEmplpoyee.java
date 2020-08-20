package com.controle.annex;
import com.controle.annex.entities.Employee;
import com.controle.annex.repository.EmployeeRepository;
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
public class TestRepositoryEmplpoyee {

    @Autowired
    private EmployeeRepository repository;

    private static final Long ID = 1L;
    private static final String NAME = "RepositoryTest";
    private static final String CPF = "321.321.321-32";
    private static final String TELEFONE = "(11)9.1111-1111";
    private static final String EMAIL = "emailtest@test.com";

    @Before
    public void init(){

        Employee employee = new Employee();
        employee.setId(ID);
        employee.setNome(NAME);
        employee.setCpf(CPF);
        employee.setTelefone(TELEFONE);
        employee.setEmail(EMAIL);
        repository.save(employee);
    }
    @After
    public void finish(){
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        Employee employee = new Employee();
        employee.setId(ID);
        employee.setNome(NAME);
        employee.setCpf(CPF);
        employee.setTelefone(TELEFONE);
        employee.setEmail(EMAIL);
        Employee response = repository.save(employee);
        assertNotNull(response);
    }
    @Test
    public void findByName(){
        Optional<Employee> findByName = repository.findByNomeIgnoreCase(NAME);
        assertTrue(findByName.isPresent());
        assertEquals(findByName.get().getNome(), NAME);
    }
    @Test
    public void testFindById(){
        Optional<Employee> findById = repository.findById(ID);
        assertTrue(findById.isPresent());
        assertEquals(findById.get().getId(), ID);
    }
    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setNome("nameTest");
        employee.setCpf("321.321.321-32");
        employee.setTelefone("(11)9.1234-4321");
        employee.setEmail("testemail@email.com");
        Employee response = repository.save(employee);

        String newName = "updateName";
        String newCpf = "444.444.444-44";
        String newEmail = "updateemail@update.com";
        response.setEmail(newEmail);
        response.setNome(newName);
        response.setCpf(newCpf);
        Employee update = repository.save(response);
        Optional<Employee> findNewEmployee = repository.findById(update.getId());
        assertEquals(findNewEmployee.get().getEmail(), newEmail);
        assertEquals(findNewEmployee.get().getNome(), newName);
        assertEquals(findNewEmployee.get().getCpf(), newCpf);

    }
    @Test
    public void testDelete(){
        Optional<Employee> findEmployee = repository.findById(ID);
        Employee employee = new Employee("testName", "777.777.777-77", "(11)9.7777-7777", "deleteTeste@teste.com", null);
        repository.save(employee);
        repository.delete(employee);
        Optional<Employee> response = repository.findById(employee.getId());
        assertFalse(response.isPresent());
    }
}
