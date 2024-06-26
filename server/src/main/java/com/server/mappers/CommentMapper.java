package com.server.mappers;

import com.server.domains.Comment;
import com.server.domains.User;
import com.server.domains.requests.event.CommentRequest;
import com.server.domains.responses.CommentResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentMapper {
  public static CommentResponse generateResponse(Comment comment) {
    return CommentResponse.builder()
        .id(comment.getId())
        .datetime(comment.getDatetime())
        .rating(comment.getRating())
        .userId(comment.getUser() != null ? comment.getUser().getId() : null)
        .comment(comment.getComment())
        .build();
  }

  public static Comment createComment(CommentRequest request, User user) {
    return Comment.builder()
        .id(null)
        .datetime(request.getDatetime())
        .rating(request.getRating())
        .user(user)
        .comment(request.getComment())
        .build();
  }

  public static Comment updateComment(Comment comment, CommentRequest request, User user) {
    return Comment.builder()
        .id(comment.getId())
        .datetime(request.getDatetime())
        .rating(request.getRating())
        .user(user)
        .comment(request.getComment())
        .build();
  }
}
