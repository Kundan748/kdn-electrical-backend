package com.kdn.kdnelectrical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

}
