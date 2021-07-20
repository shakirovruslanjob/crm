package com.ruslanshakirov.crm.mappers;

import com.ruslanshakirov.crm.entity.document.Doc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocMapper {
    void toEntity(Doc doc, @MappingTarget Doc dbDoc);
}
