package org.studyeasy.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringStarter.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}

