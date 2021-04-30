package uz.pdp.appnewssitelesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitelesson7.aop.CheckPermission;
import uz.pdp.appnewssitelesson7.dto.PostDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.Post;
import uz.pdp.appnewssitelesson7.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody PostDto postDto) {
        final ApiResponse response = postService.add(postDto);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    @CheckPermission(value = "EDIT_POST")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody PostDto postDto) {
        final ApiResponse response = postService.edit(postDto, id);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        final ApiResponse response = postService.delete(id);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_POST')")
    @GetMapping
    public HttpEntity<?> get() {
        final List<Post> posts = postService.get();
        return ResponseEntity.status(posts != null ? 201 : 409).body(posts);
    }

}
