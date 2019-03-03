package spring.reactjs.demo.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.reactjs.demo.ppmtool.domain.Project;
import spring.reactjs.demo.ppmtool.services.ProjectService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody  Project project, BindingResult result){

        if(result.hasErrors()){

            Map<String,String> errorMap = new HashMap<String,String>();
            for (FieldError err: result.getFieldErrors()){
                errorMap.put(err.getField(),err.getDefaultMessage());
            }

            return new ResponseEntity<  Map<String,String> >(errorMap, HttpStatus.BAD_REQUEST);
        }
        Project prj1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(prj1, HttpStatus.CREATED);
    }
}
