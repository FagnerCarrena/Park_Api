package com.FCarrena.demoparkapi.web.dto.mapper;

import com.FCarrena.demoparkapi.entity.Vaga;
import com.FCarrena.demoparkapi.web.dto.VagaCreateDto;
import com.FCarrena.demoparkapi.web.dto.VagaResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VagaMapper {

    public static Vaga toVaga(VagaCreateDto dto){
        return new ModelMapper().map(dto, Vaga.class);
    }

    public static VagaResponseDto toDto(Vaga vaga){
        return new ModelMapper().map(vaga, VagaResponseDto.class);

    }


}
