package com.softhouse.personapi.service;

import com.softhouse.personapi.dto.request.PersonDTO;
import com.softhouse.personapi.dto.response.MessageResponseDTO;
import com.softhouse.personapi.entity.Person;
import com.softhouse.personapi.exception.PersonNotFoundException;
import com.softhouse.personapi.mapper.PersonMapper;
import com.softhouse.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    //@Autowired
    //private PersonMapper personMapper;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();

    }

    public List<PersonDTO> listAll() {
        return personMapper.toDTOList(personRepository.findAll());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        return personMapper.toDTO(verifyIfExists(id));
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
