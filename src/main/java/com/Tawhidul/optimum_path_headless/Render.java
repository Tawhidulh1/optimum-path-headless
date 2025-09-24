package com.Tawhidul.optimum_path_headless;

import java.io.IOException;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Render extends BasicWindow {
  private Screen screen;
  private MultiWindowTextGUI gui;

  private Panel mainPanel;
  private Settings settings;
  private Editor editor;
  private Object activePanel;

  public Render() throws IOException {
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    screen = defaultTerminalFactory.createScreen();
    screen.startScreen();

    gui = new MultiWindowTextGUI(screen);

    mainPanel = new Panel();
    settings = new Settings("Settings");
    editor = new Editor("Editor");
    activePanel = settings;

    editor.lock();
  }

  @Override
  public boolean handleInput(KeyStroke keyStroke) {
    switch (keyStroke.getKeyType()) {
      case Tab:
        swapPanel();
        return true;

      case Escape:

        close();
        return true;
      default:
        break;
    }

    if (activePanel instanceof Settings) {
      Settings settings = (Settings) activePanel;
      settings.handleInput(keyStroke);
    } else {
      Editor editor = (Editor) activePanel;
      editor.handleInput(keyStroke);
    }

    return super.handleInput(keyStroke);
  }

  public void drawLayout() throws IOException {

    mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

    mainPanel.addComponent(settings.getPanel().withBorder(Borders.singleLine()),
        LinearLayout.createLayoutData(LinearLayout.Alignment.Fill));
    mainPanel.addComponent(editor.getPanel().withBorder(Borders.singleLine()),
        LinearLayout.createLayoutData(LinearLayout.Alignment.Fill));

    this.setComponent(mainPanel);
    gui.addWindowAndWait(this);
  }

  public void update() throws IOException {
    gui.updateScreen();
  }

  public void swapPanel() {
    if (activePanel == settings) {
      activePanel = editor;
      editor.unlock();
      settings.lock();

      editor.takeFocus();
    } else {
      activePanel = settings;
      editor.lock();
      settings.unlock();

      settings.takeFocus();
    }
  }

  public void close() {
    try {
      screen.stopScreen();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
