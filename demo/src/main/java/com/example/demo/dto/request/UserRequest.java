package com.example.demo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotNull(message = "Nama depan tidak boleh kosong")
    @Size(min = 1, message = "Nama depan tidak boleh kosong")
    private String firstName;
    @NotNull(message = "Nama belakang tidak boleh kosong")
    @Size(min = 1, message = "Nama depan tidak boleh kosong")
    private String lastName;

}
