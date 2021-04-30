package uz.pdp.appnewssitelesson7.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private Long postId;
}
