package com.example.projecttracersystem.ProjectController;

import com.example.projecttracersystem.ApiResponse.ApiResponse;
import com.example.projecttracersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/project-tracer-system")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse(project.toString()+" successfully added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
        projects.set(index, project);
        return new ApiResponse("Project successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);
        return new ApiResponse("Project successfully deleted");
    }

    @PutMapping("/change-status/{index}")
    public ApiResponse changeProjectStatus(@PathVariable int index) {

        if (projects.get(index).getStatus().equalsIgnoreCase("not done")){
            projects.get(index).setStatus("done");
            return new ApiResponse("Project status successfully changed");
    } else {
            return new ApiResponse("Project status is \" already done\" ");
           }
    }

    @GetMapping("/search-title/{title}")
    public ApiResponse searchProjectTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return new ApiResponse(project + " successfully found");
            }
        }
        return new ApiResponse("Project not found");
    }

    @GetMapping("/search-company/{company}")
    public ApiResponse searchProjectCompany(@PathVariable String company) {
        ArrayList<Project> company_project = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(company)) {
                company_project.add(project);
            }
        }
        if(company_project.size()>0){
        return new ApiResponse("Project of company: "+company_project);
        }
        return new ApiResponse("There are no projects for this company");
    }





}
