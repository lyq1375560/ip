package tasky;

class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }
}