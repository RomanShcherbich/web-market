package com.example.webmarket.controllers;

import com.example.webmarket.domain.Feedback;
import com.example.webmarket.repository.FeedbackRepository;
import com.example.webmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FeedbackController {

    @Autowired
    public FeedbackRepository feedbackRepository;
/*
    @Autowired
    public UserRepository userRepository;
    UserController userController = new UserController();
*/
    @GetMapping("/feedback")
    public String showFeedbackForm(Feedback feedback) {
        return "add-feedback";
    }

    @PostMapping("/addfeedback")
    public String addFeedback(@Valid Feedback feedback, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-feedback";
        }

        feedbackRepository.save(feedback);
        return refreshIndex(model);
    }

    @GetMapping({"","index"})
    public String refreshIndex(Model model) {
        model.addAttribute("feedbacks", feedbackRepository.findAll());
        return "../static/index";
    }

/*
    @PostMapping("/addfeedback")
    public String addFeedback(@Valid Feedback feedback, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-feedback";
        }

        feedbackRepository.save(feedback);
        model.addAttribute("feedback", feedback);
        return "../static/index";
    }
*/
/*
    @PostMapping("/addfeedback")
    public String addFeedback(@Valid Feedback feedback, BindingResult result, Model model1, Model model2) {
        if(result.hasErrors()) {
            return "../static/index";
        }

        feedbackRepository.save(feedback);
        model1.addAttribute("feedback", feedback);
//        userController.refreshIndex(model);
        model2.addAttribute("users", userRepository.findAll());
        return "../static/index";
    }*/
}
