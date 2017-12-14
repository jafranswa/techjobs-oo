package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobField;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODONE #1 - get the Job with the given ID and pass it into the view
    model.addAttribute(jobData.findById(id));

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        if (errors.hasErrors()){
            model.addAttribute(errors);
            return"new-job";
        }

        Job job = new Job();
        //private int JobId = new jobId;

        job.setName(jobForm.getName());
        job.setEmployer(jobData.getEmployers().findById(jobForm.getEmployerId()));
        job.setPositionType(jobData.getPositionTypes().findById(Integer.parseInt(jobForm.getPositionType())));
        job.setLocation(jobData.getLocations().findById(Integer.parseInt(jobForm.getLocation())));
        job.setCoreCompetency(jobData.getCoreCompetencies().findById(Integer.parseInt(jobForm.getCoreCompetency())));
        jobData.add(job);

        //model.addAttribute(Id = job.getId());

        return "redirect:/job?id=" + job.getId();
        //jobForm.getEmployerId
    }
}

