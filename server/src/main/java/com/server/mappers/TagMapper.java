package com.server.mappers;

import com.server.domains.Tag;
import com.server.domains.requests.event.TagRequest;
import com.server.domains.responses.TagResponse;

public class TagMapper {

  public static TagResponse generateResponse(Tag tag) {
    return TagResponse.builder()
        .id(tag.getId())
        .name(tag.getName())
        .shortName(tag.getShortName())
        .build();
  }

  public static Tag createTag(TagRequest request) {
    return Tag.builder()
        .name(request.getName())
        .shortName(request.getShortName())
        .build();
  }

  public static Tag updateTag(Tag tag, TagRequest request) {
    tag.setName(request.getName());
    tag.setShortName(request.getShortName());
    return tag;
  }
}
