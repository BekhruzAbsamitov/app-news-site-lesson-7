package uz.pdp.appnewssitelesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssitelesson7.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
