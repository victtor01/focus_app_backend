package com.focus.app.shared.utils;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class EmailAddress {

    private String email;

    public void setEmail(String email) {
        this.validEmail(email);
        this.email = email;
    }

    public EmailAddress(String email) {
        this.validEmail(email);
        this.email = email;
    }


    private void validEmail(String email) {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("O email deve ser válido.");
        }
    }

    @Override
    public String toString() {
        return email;
    }
}


