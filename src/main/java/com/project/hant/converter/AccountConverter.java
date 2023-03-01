package com.project.hant.converter;

import com.project.hant.dtos.AccountDao;
import com.project.hant.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public AccountEntity toEntity(AccountDao dto) {
        AccountEntity entity = new AccountEntity();
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }

    public AccountDao toDto(AccountEntity entity){
        AccountDao dao = new AccountDao();
        dao.setUserName(entity.getUserName());
        dao.setPassword(entity.getPassword());
        dao.setRole(entity.getRole());
        return dao;
    }
}
