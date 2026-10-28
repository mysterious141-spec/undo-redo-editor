# Undo/Redo Text Editor

A text editor with undo/redo built on **two stacks** (LIFO) and the **command pattern**.

**Live demo:** https://mysterious141-spec.github.io/undo-redo-editor/

## Why stacks?
The most recent edit must be reversed first, which is exactly LIFO.
- Edit: execute the command, push it on the **undo stack**, clear the **redo stack**.
- Undo: pop from undo, reverse it, push onto redo.
- Redo: pop from redo, re-run it, push onto undo.

## Features
- Cursor-based insert and backspace
- Commands store only the change, not full copies of the text
- History limit of 100 edits
- Unit tests and GitHub Actions CI

## Run the Java version
```bash
mvn test
mvn compile
java -cp target/classes com.alex.editor.Main
```

Commands: `type <text>`, `delete <n>`, `left [n]`, `right [n]`, `home`, `end`, `undo`, `redo`, `exit`

## Web version
`docs/index.html` is a single-file version with the same logic and a live view of both stacks, hosted with GitHub Pages.
