package com.am.pma.controllers;


import com.am.pma.entities.Message;
import com.am.pma.entities.Project;
import com.am.pma.entities.UserAccount;
import com.am.pma.services.MessageService;
import com.am.pma.services.ProjectService;
import com.am.pma.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;


@Controller
@Validated
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/new")
    public String displayCreateMessageForm(Model model, @RequestParam("id") long projectId) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("message", new Message());
        return "messages/new-message";
    }

    @PostMapping("/create")
    public String createMessage(Model model, Principal principal, @Valid Message message, BindingResult bindingResult, @RequestParam("projectId") long projectId) {
        if (bindingResult.hasErrors()) {
            return "messages/new-message";
        }

        Project targetProject = projectService.findByProjectId(projectId);
        UserAccount targetUserAccount = userAccountService.findByUserName(principal.getName());
        message.setProject(targetProject);
        message.setUserAccount(targetUserAccount);
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        messageService.save(message);
        return "redirect:/projects/details?id=" + projectId;

    }

    @GetMapping("/delete")
    public String deleteMessage(Model model, Principal principal, HttpServletRequest request, @RequestParam("id") long messageId, @RequestParam("projectId") long projectId) {

        Message targetMessage = messageService.findByMessageId(messageId);
        if (!targetMessage.getUserAccount().getUserName().equals(principal.getName())) {
            model.addAttribute("errorMessage", "Unauthorized action");
            return "redirect:/projects/details?id=" + projectId;
        }

        messageService.deleteMessage(targetMessage);
        return "redirect:/projects/details?id=" + projectId;
    }
}
