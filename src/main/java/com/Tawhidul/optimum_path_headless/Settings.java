package com.Tawhidul.optimum_path_headless;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.input.KeyStroke;

public class Settings implements InputHandler {
  private Panel panel;
  private String title;

  private List<Component> components;

  private int currentComponent;
  private boolean isLocked;

  public Settings(String title) {
    this.title = title;
    panel = new Panel();
    panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    panel.removeAllComponents();
    components = new ArrayList<>();
    load();
  }

  @Override
  public boolean handleInput(KeyStroke keyStroke) {
    switch (keyStroke.getKeyType()) {
      case ArrowDown:
        currentComponent++;
      case ArrowUp:
        currentComponent--;

      default:
        return panel.handleInput(keyStroke);
    }
  }

  public void add(Component component) {
    panel.addComponent(component);
  }

  private void load() {
    SettingButton button1 = new SettingButton("button1");
    SettingButton button2 = new SettingButton("button2");
    components.add(button1.getButton());
    components.add(button2.getButton());

    for (Component component : components) {
      panel.addComponent(component);
    }
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
    if (!components.isEmpty() && components.get(currentComponent) instanceof Interactable) {
      ((Interactable) components.get(currentComponent)).takeFocus();
    } else if (!components.isEmpty()) {
      for (Component component : components) {
        if (component instanceof Interactable) {
          ((Interactable) component).takeFocus();
        }
      }
    }
  }

  public Panel getPanel() {
    return panel;
  }

  private class SettingButton {
    private Button button;
    private boolean selected;

    public SettingButton(String title) {
      selected = false;
      button = new Button("[] " + title, () -> toggled());
    }

    private void toggled() {
      selected = !selected;
      button.setLabel((selected ? "[X]" : "[] ") + button.getLabel().substring(3));
    }

    public Button getButton() {
      return button;
    }
  }
}
