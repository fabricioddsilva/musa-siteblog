package com.kaos.musa.entities.records;

import com.kaos.musa.entities.enums.UserRole;

public record RegisterDTO(String username, String name, String password, UserRole role) {
}
