package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.With;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    private Long id;

    @NonNull 
    private String name;

    @With 
    private String email;

    @ToString.Exclude
    private String password;

    @Builder.Default 
    private String status = "ACTIVE";

    @Singular 
    private List<String> roles;
}
