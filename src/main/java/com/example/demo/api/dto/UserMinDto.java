package com.example.demo.api.dto;


public class UserMinDto {
    private Long id;
    private String name;
    private String email;

    public UserMinDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public UserMinDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserMinDtp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
