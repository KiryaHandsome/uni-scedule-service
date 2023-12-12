package by.bsuir.schedule.service;

import java.util.List;

public class Roles {

    private static final List<String> roles = List.of("Admin", "Teacher");

    public static String getRoleById(int id) {
        return roles.get(id);
    }
}
