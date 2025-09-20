package com.Tawhidul.optimum_path_headless;

import java.awt.BorderLayout;
import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Render {
  private Screen screen;
  private MultiWindowTextGUI gui;
  private BasicWindow window;

  public Render() throws IOException {
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    screen = defaultTerminalFactory.createScreen();
    screen.startScreen();

    gui = new MultiWindowTextGUI(screen);
    window = new BasicWindow("My Window");

  }

  public void drawLayout() throws IOException {
    TerminalSize termSize = screen.getTerminalSize();

    Panel mainPanel = new Panel();
    Panel panelA = new Panel();
    Panel panelB = new Panel();
    Panel panelC = new Panel();

    mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

    panelA.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    panelA.addComponent(new Label("Panel A"));
    panelA.setPreferredSize(new TerminalSize(termSize.getColumns() / 4, termSize.getRows() - 4));

    panelB.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    panelB.addComponent(new Label("Panel B"));
    panelB.setPreferredSize(new TerminalSize(termSize.getColumns() / 2, termSize.getRows() - 4));

    panelC.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    panelC.addComponent(new Label("Panel C"));
    panelC.setPreferredSize(new TerminalSize(
        termSize.getColumns() - (termSize.getColumns() / 4) - (termSize.getColumns() / 2) - 6, termSize.getRows() - 4));

    mainPanel.addComponent(panelA);
    mainPanel.addComponent(panelB);
    mainPanel.addComponent(panelC);

    window.setComponent(mainPanel);
    gui.addWindowAndWait(window);
  }

  public void update() throws IOException {
    gui.updateScreen();
  }

  public void close() throws IOException {
    screen.stopScreen();
  }

}
