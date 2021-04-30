package uz.pdp.appnewssitelesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitelesson7.dto.CommentDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.Comment;
import uz.pdp.appnewssitelesson7.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CommentDto commentDto) {
        final ApiResponse response = commentService.add(commentDto);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        final ApiResponse response = commentService.edit(commentDto, id);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        final ApiResponse response = commentService.delete(id);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_COMMENT')")
    @GetMapping
    public HttpEntity<?> get() {
        final List<Comment> comments = commentService.get();
        return ResponseEntity.status(comments != null ? 201 : 409).body(comments);
    }

}
