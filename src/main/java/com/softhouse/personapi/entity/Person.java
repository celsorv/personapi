package com.softhouse.personapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cpf;

    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Phone> phones;

}


/*

{
    "firstName": "Jucilaine",
    "lastName": "da Silva de Souza de Coimbra Cavalcante",
    "cpf": "11111111111",
    "birthDate": "1985-05-10",
    "phones": [
        {
            "type": "HOME",
            "number": "(11) 91025-1022"
        }
    ]
}

 */