package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.Comment;
import com.server.domains.User;
import com.server.domains.requests.event.CommentRequest;
import com.server.domains.responses.CommentResponse;
import com.server.mappers.CommentMapper;
import com.server.repositories.CommentRepository;
import com.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
  private final CommentRepository repository;
  private final UserRepository userRepository;

  public List<CommentResponse> getAll() {
    Iterable<Comment> comments = repository.findAll();

    List<CommentResponse> commentResponses = new ArrayList<>();
    comments.forEach(comment -> commentResponses.add(CommentMapper.generateResponse(comment)));
    return commentResponses;
  }

  public CommentResponse getById(Integer id) {
    return CommentMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Comment not found")));
  }

  public CommentResponse createComment(CommentRequest request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    final Comment comment = repository.save(CommentMapper.createComment(request, user));
    return CommentMapper.generateResponse(comment);
  }

  public CommentResponse updateComment(CommentRequest request) {
    Optional<Comment> optionalComment = repository.findById(request.getId());
    Comment existingComment = optionalComment.orElseThrow(() -> new IllegalArgumentException("Comment not found"));

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    final Comment updatedComment = repository.save(CommentMapper.updateComment(existingComment, request, user));
    return CommentMapper.generateResponse(updatedComment);
  }

  public void deleteComment(Integer id) {
    repository.deleteById(id);
  }
}
