package com.sii.fileupload.mapper;

import com.sii.fileupload.Dto.TransfertDto;
import com.sii.fileupload.entities.Transfert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransfertMapper {
    TransfertDto transfertToTransfertDto(Transfert transfert);
    Transfert transfertDtoToTransfert(TransfertDto transfertDto);
}
