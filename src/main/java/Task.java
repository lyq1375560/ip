abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}
