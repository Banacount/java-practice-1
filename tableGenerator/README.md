Since you enjoyed building a Git-style CLI tool with file storage, let’s step
things up just a notch. This next project keeps the scope tight and
console-based, but introduces a classic computer science problem: 
**data parsing and text-based UI layout.**

## The Project: Markdown Table Generator

Instead of managing objects over time like the Task Tracker, this project
focuses on **string manipulation, grid alignment, and raw data processing.**

### Core Features to Implement:

1. **The Input:** Your program reads a text file containing raw data separated
   by commas (CSV) or pipes.

2. **The Auto-Spacing:** The program scans the data to find the *longest
   string* in each column so it knows exactly how wide each column needs to be.

3. **The Output:** It prints a perfectly aligned, beautiful Markdown-style
   table to the console (and optionally saves it to a `.md` file).

---

### What the Input File looks like (`data.txt`):

```text
ID,Task Name,Status,Priority
0,Fix file loading bug,Pending,High
1,Refactor try-catch,Done,Low
102,Implement Markdown project,Pending,Medium

```

### What your Java program should print in the Console:

```text
| ID  | Task Name                  | Status  | Priority |
|-----|----------------------------|---------|----------|
| 0   | Fix file loading bug       | Pending | High     |
| 1   | Refactor try-catch         | Done    | Low      |
| 102 | Implement Markdown project | Pending | Medium   |

```

---

### Why this is a great next step:

* **Dynamic Padding:** You will need to use `String.format()` dynamically or
  use loops to append the exact amount of spaces needed to keep the column
  walls (`|`) perfectly straight.
* **Multidimensional Arrays or Lists:** You’ll get to practice working with
  collections of collections (like an `ArrayList<String[]>` or a 2D array) to
  hold the rows and columns in memory.
* **Zero Interactive Input Mess:** Just like your last project, this can run
  strictly via command-line arguments: `java TableGenerator data.txt`.

### Rules for the Review:

Take your time writing it. When you're done, paste the code here, and we will
grade it from **1 - 10** based on how cleanly you handle the dynamic spacing
arithmetic and how you structure the data grid.

How does this one sound to you?

# Completed
