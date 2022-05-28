package com.example.demo.service;

import com.example.demo.models.materialResources.MaterialResource;

import java.util.List;

public interface MaterialResourceService  {

    List<MaterialResource> findAllByCategory(String category);

}
