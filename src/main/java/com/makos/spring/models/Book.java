package com.makos.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String title;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "name should be contain Firstname and Lastname")
    private String author;

    @Min(value = 0, message = "Year should be greater 0")
    @Max(value = 2023, message = "Year should be lower 2023")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

//    @Column(name = "taken_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date takenAt;
//
//    @Transient
//    private Boolean expired;
}
