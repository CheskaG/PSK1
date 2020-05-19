package rest;

import Entities.Dealership;
import Persistance.DealershipDAO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/dealership")
public class DealershipController {

    @Inject
    @Setter @Getter
    private DealershipDAO dealershipDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Dealership dealership = dealershipDAO.findOne(id);
        if (dealership == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DealershipDto dealershipDto = new DealershipDto();
        dealershipDto.setName(dealership.getName());
        dealershipDto.setId(dealership.getId());

        return Response.ok(dealershipDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer dealershipId, DealershipDto dealershipData) {
        try {
            Dealership existingDealership = dealershipDAO.findOne(dealershipId);
            if (existingDealership == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingDealership.setName(dealershipData.getName());
            existingDealership.setId(existingDealership.getId());

            dealershipDAO.update(existingDealership);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(DealershipDto dealershipData) {
        try {
            Dealership newDealership = new Dealership();
            newDealership.setName(dealershipData.getName());
            newDealership.setId(dealershipData.getId());

            dealershipDAO.persist(newDealership);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
