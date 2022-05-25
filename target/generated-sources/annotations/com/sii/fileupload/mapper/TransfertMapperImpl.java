package com.sii.fileupload.mapper;

import com.sii.fileupload.Dto.TransfertDto;
import com.sii.fileupload.entities.Transfert;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-25T14:23:26+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class TransfertMapperImpl implements TransfertMapper {

    @Override
    public TransfertDto transfertToTransfertDto(Transfert transfert) {
        if ( transfert == null ) {
            return null;
        }

        TransfertDto transfertDto = new TransfertDto();

        transfertDto.setSender( transfert.getSender() );
        transfertDto.setReceiver( transfert.getReceiver() );
        transfertDto.setTitle( transfert.getTitle() );
        transfertDto.setMessage( transfert.getMessage() );

        return transfertDto;
    }

    @Override
    public Transfert transfertDtoToTransfert(TransfertDto transfertDto) {
        if ( transfertDto == null ) {
            return null;
        }

        Transfert transfert = new Transfert();

        transfert.setSender( transfertDto.getSender() );
        transfert.setReceiver( transfertDto.getReceiver() );
        transfert.setTitle( transfertDto.getTitle() );
        transfert.setMessage( transfertDto.getMessage() );

        return transfert;
    }
}
