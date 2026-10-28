package com.alex.editor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Scanner sc = new Scanner(System.in);
        System.out.println("Commands: type <text> | delete <n> | left [n] | right [n] | home | end | undo | redo | exit");

        while (true) {
            System.out.print("> ");
            if (!sc.hasNextLine()) break;
            String[] p = sc.nextLine().split(" ", 2);

            try {
                switch (p[0].toLowerCase()) {
                    case "type"   -> { if (p.length > 1) editor.type(p[1]); }
                    case "delete" -> editor.backspace(Integer.parseInt(p[1].trim()));
                    case "left"   -> editor.moveCursor(-(p.length > 1 ? Integer.parseInt(p[1].trim()) : 1));
                    case "right"  -> editor.moveCursor(p.length > 1 ? Integer.parseInt(p[1].trim()) : 1);
                    case "home"   -> editor.home();
                    case "end"    -> editor.end();
                    case "undo"   -> { if (!editor.undo()) System.out.println("Nothing to undo"); }
                    case "redo"   -> { if (!editor.redo()) System.out.println("Nothing to redo"); }
                    case "exit"   -> { return; }
                    default       -> System.out.println("Unknown command");
                }
            } catch (RuntimeException e) {
                System.out.println("Invalid input. Example: delete 3");
            }
            System.out.printf("Text: \"%s\" [undo: %d, redo: %d]%n",
                    editor.getTextWithCursor(), editor.undoSize(), editor.redoSize());
        }
    }
}