package com.Tawhidul.optimum_path_headless;

import java.util.List;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.input.KeyStroke;

public class Editor implements InputHandler {
  private Panel panel;
  private String title;
  private int selectedIndex;
  private boolean isLocked;

  public Editor(String title) {
    this.title = title;
    panel = new Panel();
    selectedIndex = 0;
    panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    add(new Button("[]"));
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

}
