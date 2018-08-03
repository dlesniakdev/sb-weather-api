package pl.com.bbzoftware.sbi.base.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SbUser {
    private String id;
    private String name;
    private String password;
    private String role;

    public static SbUser tempAdmin() {
        SbUser sbUser = new SbUser();
        sbUser.setId("1");
        sbUser.setName("admin");
        sbUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
        sbUser.setRole("ADMIN");
        return sbUser;
    }

    public static SbUser tempUser() {
        SbUser sbUser = new SbUser();
        sbUser.setId("2");
        sbUser.setName("user");
        sbUser.setPassword(new BCryptPasswordEncoder().encode("user"));
        sbUser.setRole("USER");
        return sbUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
