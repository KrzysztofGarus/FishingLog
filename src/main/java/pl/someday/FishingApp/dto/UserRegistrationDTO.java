package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Klasa DTO (Data Transfer Object) reprezentująca dane rejestracyjne użytkownika.
 * Zawiera informacje wymagane do utworzenia nowego konta, takie jak imię, nazwisko,
 * numer licencji wędkarskiej, adres email (jako nazwa użytkownika), oraz hasło.
 */

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private Long license;

    @NotBlank
    @Email
    private String username;

    @NotBlank
    private String password;
}
