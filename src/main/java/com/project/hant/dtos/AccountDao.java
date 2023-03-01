package com.project.hant.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDao {
    private String userName;
    private String password;
    private String role;
}
