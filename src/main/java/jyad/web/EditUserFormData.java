package jyad.web;

import jyad.domain.UserApp;
import jyad.domain.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class EditUserFormData extends CreateUserFormData {
    private Long id;
    private long version;

    public static EditUserFormData fromUser(UserApp user) {
        EditUserFormData result = new EditUserFormData();
        result.setId(user.getId());
        result.setVersion(user.getVersion());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setGender(user.getGender());
        result.setBirthday(user.getBirthday());
        result.setEmail(user.getEmail());
        result.setPhoneNumber(user.getPhoneNumber());
        return result;
    }

    public UserDto toDto() {
        return new UserDto(version, firstName, lastName, gender,
                email,birthday, phoneNumber);
    }
}
