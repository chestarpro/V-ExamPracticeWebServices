package org.example.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestEntity {
    private Long id;
    private LocalDateTime requestDateTime;
    private String name;
    private Integer yearOfBirth;
    private String gender;

    public String getRequestDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return requestDateTime.format(formatter);
    }
}
