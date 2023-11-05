package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.UserDTO;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.repo.UserRepo;

import java.time.LocalDateTime;


@Service
public class UserService extends BaseService<User> {
    @Autowired
    UserRepo userRepo;

    @Override
    protected JpaRepository<User, Long> getRepo() {
        return userRepo;
    }

    @Override
    protected User convertDTOtoModel(BaseDTO<User> baseDTO) {
        User user = new User();
        UserDTO userDTO = (UserDTO)baseDTO;
        user.setActive(userDTO.isActive());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        user.setAddress(userDTO.getAddress());

        return user;
    }

    @Override
    protected void updateEntity(final User entity, BaseDTO<User> baseDTO) {
        UserDTO userDTO = (UserDTO)baseDTO;
        entity.setActive(userDTO.isActive());
        entity.setEmail(userDTO.getEmail());
        entity.setName(userDTO.getName());
        entity.setPassword(userDTO.getPassword());
        entity.setPhone(userDTO.getPhone());
        entity.setAddress(userDTO.getAddress());
    }

    @Override
    protected BaseDTO<User> convert(User entity) {
        return new UserDTO(entity);
    }


}


