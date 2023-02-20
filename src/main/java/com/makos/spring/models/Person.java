package com.makos.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "name should be contain Firstname and Lastname")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 2023, message = "Age should be lower than 2023")
    @Column(name = "birth_year")
    private int birthYear;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

}
