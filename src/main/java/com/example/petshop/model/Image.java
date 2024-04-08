package com.example.petshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column(nullable = false)
    private byte[] data;
    @Column
    private String type;


    public Image(String name, byte[] data, String type) {
        this.name = name;
        this.data = data;
        this.type = type;
    }
}
