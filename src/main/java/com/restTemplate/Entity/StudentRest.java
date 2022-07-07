package com.restTemplate.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class StudentRest {
    @Id
    private String id;;

    private String name;
    private String country;
    private String subject;


}
