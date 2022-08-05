package com.miniproject.grocery.model;

import java.util.List;

public class Priority {
    private List<Integer> priority;

    public List<Integer> getPriority() {
        return priority;
    }

    public void setPriority(List<Integer> priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "priority='" + priority + '\'' +
                '}';
    }
}
