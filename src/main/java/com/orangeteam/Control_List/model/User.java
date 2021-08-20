package com.orangeteam.Control_List.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    @NonNull
    private String name;

    @Getter @Setter
    @NonNull
    private String surname;
}
