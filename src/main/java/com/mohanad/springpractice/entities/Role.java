package com.mohanad.springpractice.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role")
    private Integer role;
    @Column(name = "authority")
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
    }

    public Role(String authority, Collection<User> users) {
        this.authority = authority;
        this.users = users;
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }
}
