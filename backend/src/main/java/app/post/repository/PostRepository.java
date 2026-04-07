package app.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.post.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
