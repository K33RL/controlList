package com.orangeteam.Control_List.model;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    @Getter
    @Setter
    private long id;

    @Getter @Setter
    @NonNull
    private User user;

    @Getter @Setter
    @NonNull
    private OffsetDateTime startTime;

    @Getter @Setter
    @NonNull
    private OffsetDateTime endTime;

    @Getter @Setter
    @NonNull
    private String description;
}
