package eg.iti.shareit.model.dto;

// Generated Nov 9, 2015 9:29:03 PM by Hibernate Tools 4.0.0

import eg.iti.shareit.common.dto.GenericDto;


/**
 * UserDto generated by hbm2java
 */
public class UserDto implements java.io.Serializable, GenericDto {

    private int id;
    private String email;
    private String username;
    private String password;
    private String imageUrl;
    private int points;
    private GenderDto gender;


    public UserDto() {
    }

    public UserDto(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDto(int id, String email, String username, String password, String imageUrl, int points, GenderDto gender) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
        this.points = points;
        this.gender = gender;
    }

    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public GenderDto getGender() {
        return gender;
    }

    public void setGender(GenderDto gender) {
        this.gender = gender;
    }

   

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

}
