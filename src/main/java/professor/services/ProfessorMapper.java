package professor.services;

import professor.domain.Professor;
import professor.domain.dtos.ProfessorDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorMapper {

    public static ProfessorDTO mapToDTO(Professor professor){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setFirstName(professor.getFirstName());
        professorDTO.setLastName(professor.getLastName());

        return professorDTO;

    }


    public static List<ProfessorDTO> mapCollectionOfProfessorsToList(Collection<Professor> allProfessors) {
        return allProfessors.stream()
                .map(ProfessorMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
