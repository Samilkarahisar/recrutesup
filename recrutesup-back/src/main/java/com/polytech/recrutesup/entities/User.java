package com.polytech.recrutesup.entities;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
public class User implements Serializable {
        private Long _id;
        private String _name;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "survey_id", unique = true, nullable = false)
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Column(name = "name")
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
