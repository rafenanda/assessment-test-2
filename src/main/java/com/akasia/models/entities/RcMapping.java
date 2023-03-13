package com.akasia.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "rc_mapping")
public class RcMapping {
    @Id
    @Column(name = "rc")
    private String rc;
    @Column(name = "rd")
    private String rd;
}
