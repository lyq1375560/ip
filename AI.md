# AI-Assisted Development (A-AiAssisted)

## AI Tool Used

ChatGPT (OpenAI)

## Overview

AI tools were used as a coding assistant to review the codebase, suggest improvements, and refine documentation. All suggestions were carefully reviewed, modified where necessary, and tested before integration into the project.

The goal was to improve **code quality, robustness, and maintainability** without changing the core functionality of the application.

---

# Improvements Implemented with AI Assistance

## 1. Case-Insensitive Keyword Search (TaskList)

**Problem**

The original implementation of `findByKeyword()` only matched keywords with exact case sensitivity.

Example:

```
find homework
```

would not match:

```
Homework
HOMEWORK
```

**Improvement**

The search logic was updated to perform case-insensitive matching.

Updated implementation:

```java
public ArrayList<Task> findByKeyword(String keyword) {
    return tasks.stream()
            .filter(task -> task.getDescription().toLowerCase()
                    .contains(keyword.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
}
```

**Benefit**

* Improves usability
* Makes search behavior more intuitive for users

---

## 2. Safer Date Parsing for Deadline Tasks

**Problem**

The original `Deadline` constructor directly parsed dates using:

```java
LocalDate.parse(by, INPUT);
```

If the user entered an invalid date format, the program would crash.

**Improvement**

Date parsing was wrapped inside a `try-catch` block and a `TaskyException` is thrown when the format is invalid.

```java
try {
    this.by = LocalDate.parse(by, INPUT);
} catch (Exception e) {
    throw new TaskyException(
        "Invalid date format. Please use yyyy-MM-dd (e.g., 2026-03-10)."
    );
}
```

**Benefit**

* Prevents application crashes
* Provides clear feedback to the user
* Improves program robustness

---

## 3. Refactored Command Parsing Using Switch Statement

**Problem**

The original `parseAndExecute()` method relied on many sequential `if` statements:

```
if (input.equals("bye"))
if (input.equals("list"))
if (input.startsWith("mark "))
...
```

This approach becomes harder to maintain as the number of commands increases.

**Improvement**

The command is now extracted first, and a `switch` statement is used to dispatch commands.

Example:

```java
String[] parts = input.split(" ", 2);
String command = parts[0];

switch (command) {
case "bye":
    handleBye(ui);
    return;
case "list":
    handleList(tasks, ui);
    return;
...
}
```

**Benefit**

* Improves readability
* Reduces repeated condition checks
* Makes it easier to add new commands in the future

---

## 4. Improved Handling for Empty Task Lists

**Problem**

The `list` command previously always printed:

```
Here are the tasks in your list:
```

even when there were no tasks.

**Improvement**

A check was added to detect when the task list is empty.

Example improvement:

```java
if (tasks.size() == 0) {
    ui.showMessage(" Your task list is empty.");
    return;
}
```

**Benefit**

* Improves user experience
* Provides clearer feedback when no tasks exist

---

# Reflection on AI Usage

AI tools were used as a **collaborative coding assistant**, similar to a pair programmer. The AI helped identify potential improvements in the code and suggested refactoring ideas.

However, all suggestions were carefully evaluated before adoption. In several cases, AI-generated solutions were modified to better fit the structure of the existing codebase.

This process helped improve code quality while maintaining full understanding of the implementation.

The AI tool was used primarily for:

* Code review
* Refactoring suggestions
* Error handling improvements
* Documentation support
