package org.studyeasy.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringStarter.models.Authority;

@Repository
public interface  AuthorityRepository extends JpaRepository<Authority,Long>{
    
}
