package com.example.prashanth;

import com.example.prashanth.adapter.persistence.UserRepository;
import com.example.prashanth.model.UserModel;
import com.example.prashanth.usecase_impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@SpringBootTest
class PrashanthApplicationTests {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void getAllTest() {
        when(userRepository.getAll()).thenReturn(Stream.of(new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766"),
                new UserModel(54, "kumar", "prashant kumar", "kumar@123", "9922113344")).collect(Collectors.toList()));
        Assertions.assertEquals(2, userService.getAll().size());
    }

    @Test
    public void registerUserTest() {

        String str = "Inserted...Successfully";

        UserModel userModel = new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766");
        when(userRepository.insert(userModel)).thenReturn(str);
        Assertions.assertEquals(str, userService.userRegister(userModel));

    }

    @Test
    public void userDeleteTest() {
        UserModel userModel = new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766");
        userService.delete(45);
        verify(userRepository, times(1)).delete(45);

    }

    @Test
    public void getUserByIdTest() {
        long id = 45;
        UserModel userModel = new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766");
        when(userRepository.getOne(id)).thenReturn(userModel);
        Assertions.assertEquals(userModel, userService.getOne(id));
        //Assertions.assertNotNull(userModel);
    }

    @Test
    public void getDetailsTest(){
        String userId="prashant"; String password="prasha@123";
        UserModel userModel=new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766");
        when(userRepository.getByUserId(userId)).thenReturn(userModel);
        Assertions.assertEquals(userModel,userService.getDetails(userId,password));
        //Assertions.assertNotNull(userModel);
    }

    @Test
    public void updateTest(){

        String str = "Updated Successfully";
        UserModel userModel = new UserModel(45, "prashant", "Prashant kumar", "prasha@123", "9988997766");
        when(userRepository.update(userModel)).thenReturn(str);
        Assertions.assertEquals(str, userService.update(userModel));

    }
}
