package soft_uni.example.jsonprocessing.domain.models.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserImportModel {

    private String firstName;

    private String lastName;

    private int age;
}
