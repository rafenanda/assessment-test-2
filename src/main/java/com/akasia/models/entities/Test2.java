package com.akasia.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TEST2")
public class Test2 {
    @Id
    @Column
    private int id;

    @Column(name = "A")
    private String a;
    
    @Column(name = "B")
    private String b;
    
    @Column(name = "C")
    private String c;
    
    @Column(name = "D")
    private String d;

    @Column(name = "E")
    private String e;
}
