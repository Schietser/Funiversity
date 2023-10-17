package professor.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import professor.domain.Professor;
import professor.domain.dtos.CreateProfessorDTO;
import professor.domain.dtos.ProfessorDTO;
import professor.domain.dtos.UpdateProfessorDTO;
import professor.services.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

import static professor.services.ProfessorMapper.mapCollectionOfProfessorsToList;
import static professor.services.ProfessorMapper.mapToDTO;

@ApplicationScoped
public class ProfessorService {

    ProfessorRepository profRepo;

    public ProfessorService(ProfessorRepository profRepo) {
        this.profRepo = profRepo;
    }

    public List<ProfessorDTO> getAllProfessors() {
        return mapCollectionOfProfessorsToList(profRepo.getAllProfessors());
    }

    public ProfessorDTO createProfessor(CreateProfessorDTO createProfessorDTO) {

        Professor createdProfessor = new Professor(
                createProfessorDTO.getFirstName(),
                createProfessorDTO.getLastName());

        profRepo.createProfessor(createdProfessor);

        return mapToDTO(createdProfessor);

    }

    public ProfessorDTO findProfessorById(String id){
        Optional<Professor> optionalProfessor = Optional.ofNullable(profRepo.getProfessorById(id));
        return mapToDTO(optionalProfessor.orElseThrow(
                () -> new NotFoundException("Professor could not be found")
        ));
    }

    public ProfessorDTO updateProfessorById(String id, UpdateProfessorDTO updateProfessorDTO) {
        Optional<Professor> optionalProfessor = Optional.ofNullable(profRepo.getProfessorById(id));
        Professor profToUpdate = optionalProfessor.orElseThrow(
                () -> new NotFoundException("Professor could not be found"));

        profToUpdate.setFirstName(updateProfessorDTO.getFirstName());
        profToUpdate.setLastName(updateProfessorDTO.getLastName());

        profRepo.createProfessor(profToUpdate);

        return mapToDTO(profToUpdate);

    }

    public ProfessorDTO delete(String id) {
        Optional<Professor> optionalProfessor = Optional.ofNullable(profRepo.getProfessorById(id));
        Professor profToDelete = optionalProfessor.orElseThrow(
                () -> new NotFoundException("Professor could not be found"));

        return mapToDTO(profRepo.deleteProfessor(id));
    }
}
