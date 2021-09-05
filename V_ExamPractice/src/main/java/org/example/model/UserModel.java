package org.example.model;

import lombok.Data;

import java.util.Locale;

@Data
public class UserModel {
    private String name;
    private Integer yearOfBirth;
    private String gender;

    public String printUserInfo() {
        String info = null;
        if(gender.toLowerCase(Locale.ROOT).equals("мужской")
                || gender.toLowerCase(Locale.ROOT).equals("man"))
            info = "Уважаемый " + name + ", ваш год рождения " + yearOfBirth + ", вам " + getCorrectYear();

        if(gender.toLowerCase(Locale.ROOT).equals("женский")
                || gender.toLowerCase(Locale.ROOT).equals("woman"))
            info = "Уважаемая " + name + ", ваш год рождения " + yearOfBirth + ", вам " + getCorrectYear();

        return info;
    }

    private String getCorrectYear() {
        int age = 2021 - yearOfBirth;
        String correctYear = null;

        if(age < 100) {
            if(age > 4 && age < 21) correctYear = "лет";
            else {
                if(age % 10 == 1) correctYear = "год";
                else if(age % 10 > 1 && age % 10 < 5) correctYear = "года";
                else correctYear = "лет";
            }
        }
        if(age > 99) correctYear = "до хрена!";
        return age + " " + correctYear;
    }
}
