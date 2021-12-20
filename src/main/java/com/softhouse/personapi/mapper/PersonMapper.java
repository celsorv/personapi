package com.softhouse.personapi.mapper;

import com.softhouse.personapi.dto.request.PersonDTO;
import com.softhouse.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper(componentModel = "spring") // habilita @Autowired
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    //@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

    List<PersonDTO> toDTOList(List<Person> persons);

}
