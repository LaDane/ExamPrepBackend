package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTO;
import entities.Address;
import entities.Owner;
import errorhandling.NotFoundException;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("owner")
public class OwnerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OwnerFacade FACADE =  OwnerFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("ping")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String jsonContext) {
        OwnerDTO dto = GSON.fromJson(jsonContext, OwnerDTO.class);
        Owner owner = new Owner(dto);
        OwnerDTO created = new OwnerDTO(FACADE.create(owner));
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(created))
                .build();
    }

//    @Path("{id}")
//    @PUT
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response update(@PathParam("id") Long id, String jsonContext) throws NotFoundException {
//        OwnerDTO dto = GSON.fromJson(jsonContext, OwnerDTO.class);
//        Owner owner = new Owner(
//                dto.getDummyStr1(),
//                dto.getDummyStr2()
//        );
//        owner.setId(id);
//        OwnerDTO updated = new OwnerDTO(FACADE.update(owner));
//        return Response
//                .ok("SUCCESS")
//                .entity(GSON.toJson(updated))
//                .build();
//    }

//    @Path("{id}")
//    @DELETE
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response delete(@PathParam("id") Long id) throws NotFoundException {
//        OwnerDTO deleted = new OwnerDTO(FACADE.delete(id));
//        return Response
//                .ok("SUCCESS")
//                .entity(GSON.toJson(deleted))
//                .build();
//    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        OwnerDTO found = new OwnerDTO(FACADE.getById(id));
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(found))
                .build();
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<OwnerDTO> dtoList = new ArrayList<>();
        for (Owner owner : FACADE.getAll()) {
            dtoList.add(new OwnerDTO(owner));
        }
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(dtoList))
                .build();
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOwnerCount() {
        long count = FACADE.getCount();
        return "{\"count\":"+count+"}";
    }
}
