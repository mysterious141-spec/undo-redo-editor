package com.alex.editor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EditorTest {
    @Test void undoRedoWorks() {
        Editor e = new Editor();
        e.type("Hello");
        e.type(" World");
        e.undo();
        assertEquals("Hello", e.getText());
        e.redo();
        assertEquals("Hello World", e.getText());
    }

    @Test void newEditClearsRedo() {
        Editor e = new Editor();
        e.type("Hi");
        e.undo();
        e.type("Yo");
        assertFalse(e.redo());
    }

    @Test void insertInTheMiddle() {
        Editor e = new Editor();
        e.type("Helo");
        e.moveCursor(-2);      // He|lo
        e.type("l");
        assertEquals("Hello", e.getText());
        assertEquals(3, e.getCursor());
        e.undo();
        assertEquals("Helo", e.getText());
        assertEquals(2, e.getCursor());
    }

    @Test void backspaceIsUndoable() {
        Editor e = new Editor();
        e.type("Hello");
        e.backspace(2);
        assertEquals("Hel", e.getText());
        e.undo();
        assertEquals("Hello", e.getText());
        assertEquals(5, e.getCursor());
    }

    @Test void backspaceAtStartDoesNothing() {
        Editor e = new Editor();
        e.type("Hi");
        e.home();
        e.backspace(1);
        assertEquals("Hi", e.getText());
        assertEquals(1, e.undoSize());
    }

    @Test void historyIsLimited() {
        Editor e = new Editor();
        for (int i = 0; i < Editor.HISTORY_LIMIT + 50; i++) e.type("x");
        int undone = 0;
        while (e.undo()) undone++;
        assertEquals(Editor.HISTORY_LIMIT, undone);
    }

    @Test void emptyStacksAreSafe() {
        Editor e = new Editor();
        assertFalse(e.undo());
        assertFalse(e.redo());
    }
}