package com.example.webmarket.controllers;

import com.example.webmarket.domain.Feedback;
import com.example.webmarket.repository.FeedbackRepository;
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

    @GetMapping("/feedback")
    public String showFeedbackForm(Feedback feedback) {
        return "contacts";
    }

    @PostMapping("/addfeedback")
    public String addFeedback(@Valid Feedback feedback, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "contacts";
        }
        model.addAttribute("feedback", feedback);
        feedbackRepository.save(feedback);
        return refreshIndex(model);
    }

    @GetMapping({"","index"})
    public String refreshIndex(Model model) {
        model.addAttribute("feedbacks", feedbackRepository.findAll());
        return "../static/index";
    }
}
