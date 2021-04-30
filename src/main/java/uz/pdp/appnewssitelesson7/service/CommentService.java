package uz.pdp.appnewssitelesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitelesson7.dto.CommentDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.Comment;
import uz.pdp.appnewssitelesson7.entity.Post;
import uz.pdp.appnewssitelesson7.repository.CommentRepository;
import uz.pdp.appnewssitelesson7.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public ApiResponse add(CommentDto commentDto) {
        Comment comment = new Comment();
        final Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Post not found", false);
        }
        final Post post = optionalPost.get();
        comment.setPost(post);
        comment.setText(commentDto.getText());
        final Comment save = commentRepository.save(comment);
        return new ApiResponse("Comment saved", true, save);
    }

    public List<Comment> get() {
        return commentRepository.findAll();
    }

    public ApiResponse edit(CommentDto commentDto, Long id) {
        final Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            return new ApiResponse("Comment not found", false);
        }
        final Comment comment = optionalComment.get();

        final Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Post not found", false);
        }
        final Post post = optionalPost.get();
        comment.setPost(post);

        comment.setText(commentDto.getText());

        final Comment save = commentRepository.save(comment);

        return new ApiResponse("Comment edited", true, save);
    }

    public ApiResponse delete(Long id) {
        final boolean exists = commentRepository.existsById(id);
        if (!exists) {
            return new ApiResponse("Comment not found", false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }
}
