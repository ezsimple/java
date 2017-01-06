package com.springapp.mvc;

import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 25
 * Time: 오후 12:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Path("/customer")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserRestful {

    @Autowired
    UserRepository userRepository;

    @GET
    @Path("/list")
    public UserResponse getUsers() {
        UserResponse userResponse = new UserResponse();
        List<User> users = userRepository.findAll();
        userResponse.setUsers(users);
        for (int i = 0; i < users.size(); i++) {
            printUser(users.get(i));
        }
        return userResponse;
    }

    @GET
    @Path("/{userId}")
    public UserResponse getUser(@PathParam("userId") Long id) {
        UserResponse userResponse = new UserResponse();

        User user = userRepository.findOne(id);
        printUser(user);

        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @GET
    @Path("/old")
    public Response getUser() {
        return Response.ok().entity("<xml>씨팔</xml>").build();
    }

    private void printUser(final User user) {
        System.out.println("-------------------");
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
    }
}
