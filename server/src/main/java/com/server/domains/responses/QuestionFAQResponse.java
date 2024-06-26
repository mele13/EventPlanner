package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionFAQResponse {
    private Integer id;
    private String question;
}
