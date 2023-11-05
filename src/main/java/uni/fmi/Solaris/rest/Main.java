package uni.fmi.Solaris.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @GetMapping(path = "/")
    public String getMain(){
        return "Hello world";
    }
}
