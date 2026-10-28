package com.alex.editor;

import java.util.ArrayDeque;
import java.util.Deque;

public class Editor {
    public static final int HISTORY_LIMIT = 100;

    private final Document doc = new Document();
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    /** Inserts text at the cursor. */
    public void type(String s) {
        if (s == null || s.isEmpty()) return;
        run(new InsertCommand(doc, doc.getCursor(), s));
    }

    /** Deletes up to n characters before the cursor (like Backspace). */
    public void backspace(int n) {
        int end = doc.getCursor();
        int start = Math.max(0, end - n);
        if (start == end) return;
        run(new DeleteCommand(doc, start, end - start));
    }

    // Cursor moves are not edits, so they are not undoable.
    public void moveCursor(int delta) { doc.setCursor(doc.getCursor() + delta); }
    public void home() { doc.setCursor(0); }
    public void end()  { doc.setCursor(doc.length()); }

    private void run(Command c) {
        c.execute();
        undoStack.push(c);
        if (undoStack.size() > HISTORY_LIMIT) undoStack.removeLast(); // drop oldest
        redoStack.clear(); // a new edit invalidates redo
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        Command c = undoStack.pop();
        c.undo();
        redoStack.push(c);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        Command c = redoStack.pop();
        c.execute();
        undoStack.push(c);
        return true;
    }

    public String getText()           { return doc.getText(); }
    public String getTextWithCursor() { return doc.getTextWithCursor(); }
    public int getCursor()            { return doc.getCursor(); }
    public int undoSize()             { return undoStack.size(); }
    public int redoSize()             { return redoStack.size(); }
}