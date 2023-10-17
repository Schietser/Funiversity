package professor.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse.*;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import professor.domain.Professor;
import professor.domain.dtos.CreateProfessorDTO;
import professor.domain.dtos.ProfessorDTO;
import professor.domain.dtos.UpdateProfessorDTO;
import professor.services.ProfessorService;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/professor")
public class ProfessorController {

    ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(StatusCode.OK)
    public List<ProfessorDTO> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(StatusCode.CREATED)
    public ProfessorDTO createProfessor(CreateProfessorDTO createProfessorDTO){
        return professorService.createProfessor(createProfessorDTO);
    }

    @GET
    @Path("/{id}")
    public ProfessorDTO findProfessorById(@PathParam("id") String id){
        return professorService.findProfessorById(id);
    }

    @PATCH
    @Path("/{id}")
    public ProfessorDTO updateProfessorById(@PathParam("id") String id, UpdateProfessorDTO updateProfessorDTO){
        return professorService.updateProfessorById(id, updateProfessorDTO);
    }

    @DELETE
    @Path("/{id}")
    public ProfessorDTO deleteProfessor(@PathParam("id") String id){
        return professorService.delete(id);
    }





    @ServerExceptionMapper(NotFoundException.class)
    protected Response notFoundException(NotFoundException exception){
        return Response.status(BAD_REQUEST).entity(exception.getMessage()).build();
    }




}
