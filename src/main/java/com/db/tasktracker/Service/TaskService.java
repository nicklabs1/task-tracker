package com.db.tasktracker.Service;
import java.util.List;

import com.db.tasktracker.Model.Task;

public interface TaskService {
    List<Task> getAllTasks();
    Task createTask(Task task);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
}