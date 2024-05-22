package com.example.recordsdb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "genres")
@Accessors(chain = true)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Song> songs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="records_genres", joinColumns = @JoinColumn(name = "records_id"), inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private List<RecordEntity> records = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="musicians_genres",
            joinColumns=
            @JoinColumn(name="musician_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="genres_id", referencedColumnName="id")
    )
    private List<Musician> musicians = new ArrayList<>();

}