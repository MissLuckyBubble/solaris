package uni.fmi.Solaris.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.UserDTO;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<BaseDTO<User>> findAll(){
        return userService.getAll();
    }

    @PostMapping()
    public BaseDTO<User> create (@RequestBody UserDTO newUser){
        return userService.create(newUser);
    }

    @PutMapping()
    public BaseDTO<User> update(@RequestBody UserDTO user){
        return userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable long userId){
        boolean isRemoved = userService.remove(userId);

        String deletedMessage = "User with id: " + userId + " was deleted";
        String notDeletedMessage = "User with id: " + userId + " does not exist";
        return isRemoved? new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage,HttpStatusCode.valueOf(404));
    }


}
