package com.example.netcracker.homework6.mapper;

import com.example.netcracker.homework6.model.dto.ShopDTO;
import com.example.netcracker.homework6.model.entity.Shop;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapper.class)
public interface ShopMapper extends EntityMapper<Shop, ShopDTO> {
}
