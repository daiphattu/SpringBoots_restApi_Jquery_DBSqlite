package com.project.hant.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class AccountEntity extends BaseEntity {
    @Column()
    private String userName;

    @Column()
    private String password;

    @Column()
    private String role;
}
