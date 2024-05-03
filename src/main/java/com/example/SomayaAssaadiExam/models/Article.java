package com.example.SomayaAssaadiExam.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;


    @NotBlank(message = "Description is required")
    @Size(min = 10 , message = "Description must be at least 10 characters")
    private String description;


    @Positive(message = "price doit être un entier positif supérieur à 0 ")
    private int price;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article(Long id, String name, String description, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.description= description;
        this.price = price;
        this.quantity = quantity;
    }
}
