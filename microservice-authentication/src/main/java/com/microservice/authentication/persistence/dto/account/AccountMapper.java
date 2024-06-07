package com.microservice.authentication.persistence.dto.account;

import com.microservice.authentication.persistence.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDto toAccountResponseDto(Account account);
}
