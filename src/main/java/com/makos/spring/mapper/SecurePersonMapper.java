package com.makos.spring.mapper;

import com.makos.spring.dto.SecurePersonDTO;
import com.makos.spring.model.SecurePerson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SecurePersonMapper {
    SecurePersonMapper INSTANCE = Mappers.getMapper(SecurePersonMapper.class);

    SecurePerson toEntity(SecurePersonDTO securePersonDTO);

}
