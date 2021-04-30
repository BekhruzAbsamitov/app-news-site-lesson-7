package uz.pdp.appnewssitelesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssitelesson7.entity.Comment;
import uz.pdp.appnewssitelesson7.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCreatedBy(User createdBy);
}
