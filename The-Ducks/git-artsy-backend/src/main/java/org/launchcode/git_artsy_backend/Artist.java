package org.launchcode.git_artsy_backend;

import jakarta.persistence.*;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

import java.util.Iterator;

/**
 * Represents an Artist in the application.
 * Extends the User class to inherit common properties.
 */
@Entity
public class Artist implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column for the artist's profile picture URL
    @Column
    private String profilePictureUrl;

    // Column for the artist's biography
    @Column
    private String bio;

    // Column for the artist's website URL
    @Column
    private String website;

    public Artist(String profilePictureUrl, String bio, String website) {
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.website = website;
    }

    // Getters and setters for all fields
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public void setFullName(String s) {

    }

    @Override
    public Iterator<Group> getGroups() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String s) {

    }

    @Override
    public Iterator<Role> getRoles() {
        return null;
    }

    @Override
    public UserDatabase getUserDatabase() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void setUsername(String s) {

    }

    @Override
    public void addGroup(Group group) {

    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public boolean isInGroup(Group group) {
        return false;
    }

    @Override
    public boolean isInRole(Role role) {
        return false;
    }

    @Override
    public void removeGroup(Group group) {

    }

    @Override
    public void removeGroups() {

    }

    @Override
    public void removeRole(Role role) {

    }

    @Override
    public void removeRoles() {

    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
