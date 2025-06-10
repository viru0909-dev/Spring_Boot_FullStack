package org.studyeasy.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringStarter.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
