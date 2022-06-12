package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import dtos.HarbourDTO;
import entities.Boat;
import entities.Harbour;
import errorhandling.NotFoundException;
import facades.HarbourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("harbour")
public class HarbourResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HarbourFacade FACADE =  HarbourFacade.getFacade(EMF);
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
        HarbourDTO dto = GSON.fromJson(jsonContext, HarbourDTO.class);
        Harbour harbour = new Harbour(dto);
        HarbourDTO created = new HarbourDTO(FACADE.create(harbour));
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
//        HarbourDTO dto = GSON.fromJson(jsonContext, HarbourDTO.class);
//        Harbour harbour = new Harbour(
//                dto.getDummyStr1(),
//                dto.getDummyStr2()
//        );
//        harbour.setId(id);
//        HarbourDTO updated = new HarbourDTO(FACADE.update(harbour));
//        return Response
//                .ok("SUCCESS")
//                .entity(GSON.toJson(updated))
//                .build();
//    }

//    @Path("{id}")
//    @DELETE
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response delete(@PathParam("id") Long id) throws NotFoundException {
//        HarbourDTO deleted = new HarbourDTO(FACADE.delete(id));
//        return Response
//                .ok("SUCCESS")
//                .entity(GSON.toJson(deleted))
//                .build();
//    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        HarbourDTO found = new HarbourDTO(FACADE.getById(id));
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(found))
                .build();
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<HarbourDTO> dtoList = new ArrayList<>();
        for (Harbour harbour : FACADE.getAll()) {
            dtoList.add(new HarbourDTO(harbour));
        }
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(dtoList))
                .build();
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getHarbourCount() {
        long count = FACADE.getCount();
        return "{\"count\":"+count+"}";
    }

    @Path("{id}/boats")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBoatsInHarbour(@PathParam("id") Long id) {
        List<BoatDTO> boats = new ArrayList<>();
        for (Boat b : FACADE.getAllBoatsInHarbour(id)) {
            boats.add(new BoatDTO(b));
        }
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(boats))
                .build();
    }
}
