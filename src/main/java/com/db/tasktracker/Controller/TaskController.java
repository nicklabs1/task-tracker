package com.db.tasktracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.tasktracker.Model.Task;
import com.db.tasktracker.Service.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ==== REST APIs ====
    @ResponseBody
    @GetMapping("/api/tasks")
    public List<Task> getAllTasksApi() {
        return taskService.getAllTasks();
    }

    @ResponseBody
    @PostMapping("/api/tasks")
    public Task createTaskApi(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @ResponseBody
    @PutMapping("/api/tasks/{id}")
    public Task updateTaskApi(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @ResponseBody
    @DeleteMapping("/api/tasks/{id}")
    public void deleteTaskApi(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // ==== Thymeleaf UI ====
    // Show all tasks
    @GetMapping("/tasks-ui")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";  // index.html
    }

    // Show form to register new task
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("task", new Task());
        return "register";  // register.html
    }

    // Save task from form
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/tasks-ui";
    }

    // Delete task (UI link)
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        System.out.println(">>> Deleting task id: " + id);
        taskService.deleteTask(id);
        return "redirect:/tasks-ui";
    }

}
