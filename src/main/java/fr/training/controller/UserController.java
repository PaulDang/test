package fr.training.controller;

import fr.training.entity.User;
//import fr.training.repository.UserRepository;
import fr.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/gets")
    public List<User> findAll(){
        List<User> users = repo.findAll();
        return users;
    }

    @GetMapping("/get/{id}")
    public Optional<User> findById(@PathVariable Long id) throws Exception {
        return repo.findById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody @Valid User user
            , BindingResult bindingResult, Model model) throws Exception {
            Optional<User> updateUser = repo.findById(user.getId());
            ResponseEntity<User> responseEntity;

            if (updateUser == null){
                responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else {
                User savedUser = repo.save(user);
                responseEntity = new ResponseEntity<>(savedUser, HttpStatus.OK);
            }
            return responseEntity;
        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete
            (@PathVariable Long id) throws Exception {
                Optional<User> deleteUser = repo.findById(id);
                if (deleteUser != null){
//                    repo.delete(deleteUser);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser
            (@RequestBody @Valid User user,BindingResult bindingResult, Model model)
        throws Exception {
            ResponseEntity<User> responseEntity;

            if (bindingResult.hasErrors()) {
                responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                User savedUser = repo.save(user);
                responseEntity = new ResponseEntity<>(savedUser, HttpStatus.OK);
            }
            return responseEntity;
        }

}
