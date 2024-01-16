package com.cp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer>{

}
