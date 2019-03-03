package spring.reactjs.demo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.exceptions.ProjectIdException;
import spring.reactjs.demo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        //Logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("ProjectID "+project.getProjectIdentifier().toUpperCase()+ " already exists");
        }

    }
}
