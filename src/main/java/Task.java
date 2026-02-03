abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() { isDone = true; }
    public void unmarkDone() { isDone = false; }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String toFileString() {
        return type.name() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}
