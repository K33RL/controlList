package com.orangeteam.Control_List.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    @NonNull
    private User user;

    @Getter @Setter
    private int durationMin;

    @Getter @Setter
    @NonNull
    private String description;
}
