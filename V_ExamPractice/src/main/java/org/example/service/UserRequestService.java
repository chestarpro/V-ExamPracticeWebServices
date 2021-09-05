package org.example.service;

import org.example.dao.UserRequestDao;
import org.example.dao.impl.UserRequestRequestDaoImpl;
import org.example.entity.UserRequestEntity;
import org.example.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;

@Path("/user")
public class UserRequestService {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveUserRequest(UserRequestEntity userRequestEntity) {
        try {
            if(userRequestEntity.getGender().toLowerCase(Locale.ROOT).equals("man")
                    || userRequestEntity.getGender().toLowerCase(Locale.ROOT).equals("woman")
                    || userRequestEntity.getGender().toLowerCase(Locale.ROOT).equals("мужской")
                    || userRequestEntity.getGender().toLowerCase(Locale.ROOT).equals("женский")) {

                UserRequestEntity dBUser = new UserRequestRequestDaoImpl().saveUserRequest(userRequestEntity);
                if(dBUser != null) {
                    if(userRequestEntity.getYearOfBirth() < 2000 || userRequestEntity.getYearOfBirth() >= 2021)
                        throw new IllegalAccessException("Error! Incorrect data(year of birth)");
                    UserModel userModel = new UserModel();
                    userModel.setName(dBUser.getName());
                    userModel.setYearOfBirth(dBUser.getYearOfBirth());
                    userModel.setGender(dBUser.getGender());
                    return Response.status(Response.Status.OK).entity(userModel.printUserInfo()).build(); // OK!
                } else throw new IllegalAccessException("Error! Incorrect data");

            } else throw new IllegalAccessException("Error! Incorrect data(gender)");
        } catch (IllegalAccessException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserRequestEntity> getAllUserRequests() {
        return new UserRequestRequestDaoImpl().getAllUserRequests();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/id={id}")
    public Response deleteByUserRequestId(@PathParam("id") Long id) {
        UserRequestDao userRequestDao = new UserRequestRequestDaoImpl();
        if(userRequestDao.deleteByUserRequestId(id))
            return Response.status(Response.Status.OK).entity("Запрос удален!").build();
        return Response.status(Response.Status.BAD_REQUEST).entity("Id не найден!").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name={name}")
    public List<UserModel> getByUserName(@PathParam("name") String name) {
        return new UserRequestRequestDaoImpl().getByUserName(name);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/year={year}")
    public List<UserModel> getByUserYearOfBirth(@PathParam("year") Integer yearOfBirth) {
        return new UserRequestRequestDaoImpl().getByUserYearOfBirth(yearOfBirth);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/gender={gender}")
    public List<UserModel> getByUserGender(@PathParam("gender") String gender) {

        return new UserRequestRequestDaoImpl().getByUserGender(gender);
    }
}
