package com.library.management.librarymanagementsystem.entity_models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(name = "name", length = 30, nullable = false)
    private String authorName;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 12)
    private Long phno;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> listOfBooks;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

}
