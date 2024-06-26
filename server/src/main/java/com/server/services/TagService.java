package com.server.services;

import com.server.domains.Tag;
import com.server.domains.requests.event.TagRequest;
import com.server.domains.responses.TagResponse;
import com.server.mappers.TagMapper;
import com.server.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagService {

  private final TagRepository tagRepository;

  public List<TagResponse> getAll() {
    Iterable<Tag> tags = tagRepository.findAll();

    List<TagResponse> tagResponses = new ArrayList<>();
    tags.forEach(tag -> tagResponses.add(TagMapper.generateResponse(tag)));
    return tagResponses;
  }

  public TagResponse getById(Integer id) {
    return TagMapper.generateResponse(tagRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Tag not found")));
  }

  public TagResponse createTag(TagRequest request) {
    Tag tag = tagRepository.save(TagMapper.createTag(request));
    return TagMapper.generateResponse(tag);
  }

  public TagResponse updateTag(TagRequest request) {
    Optional<Tag> optionalTag = tagRepository.findById(request.getId());
    Tag existingTag = optionalTag.orElseThrow(() -> new IllegalArgumentException("Tag not found"));

    Tag updatedTag = TagMapper.updateTag(existingTag, request);
    updatedTag = tagRepository.save(updatedTag);
    return TagMapper.generateResponse(updatedTag);
  }

  public void deleteTag(Integer id) {
    tagRepository.deleteById(id);
  }
}
