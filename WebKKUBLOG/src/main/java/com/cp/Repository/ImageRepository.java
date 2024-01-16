package com.cp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}
