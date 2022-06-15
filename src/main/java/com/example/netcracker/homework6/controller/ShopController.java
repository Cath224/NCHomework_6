package com.example.netcracker.homework6.controller;

import com.example.netcracker.homework6.mapper.EntityMapper;
import com.example.netcracker.homework6.model.dto.ShopDTO;
import com.example.netcracker.homework6.model.entity.Shop;
import com.example.netcracker.homework6.service.api.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Tag(name = "Shop")
@RestController
@RequestMapping("api/v1/shops")
public class ShopController extends EntityController<Shop, ShopDTO> {

    private final ShopService service;

    public ShopController(EntityMapper<Shop, ShopDTO> mapper, ShopService service) {
        super(mapper, service);
        this.service = service;
    }

    @Operation(summary = "Get Shops names by it's districts; Task: 3.b")
    @GetMapping("names")
    public Set<String> findNamesByDistricts(@RequestParam Set<String> districts) {
        return service.findNamesByDistricts(districts);
    }

    @Operation(summary = "Get Shops located not in district and it's customers discounts are in range; Task: 5.c")
    @GetMapping("filter-district-not")
    public List<ShopDTO> findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(@RequestParam String district,
                                                                                    @RequestParam(required = false, defaultValue = "10.00") float minDiscount,
                                                                                    @RequestParam(required = false, defaultValue = "15.00") float maxDiscount) {
        return toDtos(service.findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(district, minDiscount, maxDiscount));
    }

    @Override
    protected void setId(UUID id, ShopDTO dto) {
        dto.setId(id);
    }
}
