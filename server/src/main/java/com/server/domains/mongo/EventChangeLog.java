package com.server.domains.mongo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.server.domains.enums.ChangeType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_change_log")
public class EventChangeLog {

    @Id
    private String id;
    private String eventId;
    private ChangeType changeType;
    private String fieldName;
    private String oldValue;
    private String newValue;
    private Date timestamp;
    private String changedBy;
}
