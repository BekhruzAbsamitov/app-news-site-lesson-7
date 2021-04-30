package uz.pdp.appnewssitelesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssitelesson7.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByUrl(String url);

    boolean existsByUrlAndIdNot(String url, Long id);
}
