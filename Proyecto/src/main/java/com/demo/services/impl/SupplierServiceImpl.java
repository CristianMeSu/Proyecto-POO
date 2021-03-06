package com.demo.services.impl;

import com.demo.models.Supplier;
import com.demo.repositorio.SupplierRepository;
import com.demo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void createSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> findById(int id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void removeSupplier(int id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}
