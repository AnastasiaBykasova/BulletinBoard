package com.example.models;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity // Аннотация, которая делает этот класс сущностью Hibernate
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "categoryname")
    private String categoryName;

    //  Lombok  генерирует конструкторы автоматически
    public Category() { }
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    //getters
    public Long getId() {
        return id;
    }
    public String getCategoryName() {
        return categoryName;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
