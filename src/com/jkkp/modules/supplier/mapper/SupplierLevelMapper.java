package com.jkkp.modules.supplier.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierLevel;

public interface SupplierLevelMapper extends Mapper<SupplierLevel> {
    public List<SupplierLevel> findAll();
}