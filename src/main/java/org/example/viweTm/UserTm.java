package org.example.viweTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTm  {


        private  int userID;
        private  String UserName;
        private String UserPassword;
        private  String UserRole;
        /*private JFXButton delete;
        private JFXButton update;*/

}
