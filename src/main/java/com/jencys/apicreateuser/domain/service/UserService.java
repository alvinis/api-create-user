package com.jencys.apicreateuser.domain.service;

import com.jencys.apicreateuser.dto.UserRequestDTO;
import com.jencys.apicreateuser.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequestDTO userRequestDTO);
}
