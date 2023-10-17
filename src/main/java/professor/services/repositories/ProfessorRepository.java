package professor.services.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import professor.domain.Professor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProfessorRepository {

    private final Map<String, Professor> professorMap;

    public ProfessorRepository() {
        professorMap = new HashMap<>();
    }

    public Collection<Professor> getAllProfessors() {
        return professorMap.values();
    }

    public Professor getProfessorById(String id){
        return professorMap.get(id);
    }

    public Professor createProfessor(Professor professor) {
        return professorMap.put(professor.getId(), professor);
    }

    public Professor deleteProfessor(String id) {
        return professorMap.remove(id);
    }
}
