package uz.pdp.appnewssitelesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitelesson7.dto.PostDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.Post;
import uz.pdp.appnewssitelesson7.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> get() {
        return postRepository.findAll();
    }

    public ApiResponse add(PostDto postDto) {
        Post post = new Post();
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        post.setTitle(postDto.getTitle());
        final boolean exists = postRepository.existsByUrl(postDto.getUrl());
        if (exists) {
            return new ApiResponse("Post URL already exists", false);
        }
        final Post savedPost = postRepository.save(post);
        return new ApiResponse("Post saved!", true, savedPost);
    }

    public ApiResponse edit(PostDto postDto, Long id) {
        final Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Post not found", false);
        }
        final boolean exists = postRepository.existsByUrlAndIdNot(postDto.getUrl(), id);
        if (exists) {
            return new ApiResponse("Post URL already exists", false);
        }

        final Post p = optionalPost.get();
        p.setText(postDto.getText());
        p.setUrl(postDto.getUrl());
        p.setTitle(postDto.getTitle());
        final Post save = postRepository.save(p);
        return new ApiResponse("Post edited", true, save);
    }

    public ApiResponse delete(Long id) {
        final boolean exists = postRepository.existsById(id);
        if (!exists) {
            return new ApiResponse("Post does not exists", false);
        }
        postRepository.deleteById(id);
        return new ApiResponse("Post deleted", true);
    }
}
