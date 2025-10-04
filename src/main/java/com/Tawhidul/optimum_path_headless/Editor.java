package com.Tawhidul.optimum_path_headless;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextGraphicsWriter;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Editor implements InputHandler {
  private Panel panel;
  private String title;
  private boolean isLocked;

  private String[][] map;;

  private List<Component> components;

  public Editor(String title) {
    this.title = title;
    panel = new Panel();
    map = new String[8][8];
    components = new ArrayList<>();
    GridLayout panelLayout = new GridLayout(map[0].length);
    panelLayout.setLeftMarginSize(0);
    panelLayout.setRightMarginSize(0);
    panelLayout.setHorizontalSpacing(0);
    panel.setLayoutManager(panelLayout);

    map[0] = new String[] { "W", "W", "W", "O", "E", "W", "W", "W" };
    map[1] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[2] = new String[] { "W", "W", "W", "O", "O", "W", "W", "W" };
    map[3] = new String[] { "W", "W", "W", "O", "W", "W", "W", "W" };
    map[4] = new String[] { "W", "W", "W", "O", "O", "W", "W", "W" };
    map[5] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[6] = new String[] { "W", "W", "W", "W", "O", "W", "W", "W" };
    map[7] = new String[] { "W", "W", "W", "S", "O", "W", "W", "W" };

    load();
  }

  @Override
  public boolean handleInput(KeyStroke keyStroke) {
    return true;
  }

  public void lock() {
    isLocked = true;
    for (Component component : panel.getChildrenList()) {
      if (component instanceof Interactable) {
        ((Interactable) component).setEnabled(false);
      }
    }
  }

  public void unlock() {
    isLocked = false;
    for (Component component : panel.getChildrenList()) {
      ((Interactable) component).setEnabled(true);
    }
  }

  private void load() {
    for (int r = 0; r < map.length; r++) {
      EditorTextBox cur = new EditorTextBox("");
      for (int c = 0; c < map[0].length; c++) {
        components.add(new EditorTextBox(map[r][c]));
      }
    }
    for (Component component : components) {
      panel.addComponent(component);
    }
  }

  public void takeFocus() {
    List<Component> components = panel.getChildrenList();
    if (!components.isEmpty() && components.get(0) instanceof Interactable) {
      ((Interactable) components.get(0)).takeFocus();
    } else if (!components.isEmpty()) {
      for (Component component : components) {
        if (component instanceof Interactable) {
          ((Interactable) component).takeFocus();
        }
      }
    }
  }

  public void add(Component component) {
    panel.addComponent(component);
  }

  public Panel getPanel() {
    return panel;
  }

  private class EditorTextBox extends TextBox {
    public EditorTextBox(String initialContent) {
      super(initialContent);
      setHorizontalFocusSwitching(true);
      setVerticalFocusSwitching(true);

      setValidationPattern(Pattern.compile("^.{0,1}$"));

    }

    private void handleTextChange(Character str) {
      setText(str.toString());
    }

    @Override
    public Interactable.Result handleKeyStroke(KeyStroke keyStroke) {
      switch (keyStroke.getKeyType()) {
        case ArrowRight:
          return Result.MOVE_FOCUS_RIGHT;

        case ArrowLeft:
          return Result.MOVE_FOCUS_LEFT;

        case ArrowUp:
          return Result.MOVE_FOCUS_UP;

        case ArrowDown:
          return Result.MOVE_FOCUS_DOWN;

        case Character:
          Character c = keyStroke.getCharacter();
          handleTextChange(c);

          return Result.HANDLED;
        default:

          return Result.UNHANDLED;
      }
    }

  }

}
