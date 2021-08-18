package com.orangeteam.Control_List.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Getter
    @Setter
    private long id;

    @Getter @Setter
    @NonNull
    private String name;

    @Getter @Setter
    @NonNull
    private String surName;
}
