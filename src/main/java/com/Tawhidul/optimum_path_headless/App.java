package com.Tawhidul.optimum_path_headless;

import java.io.*;
import org.jline.terminal.*;
import org.jline.reader.*;
import org.jline.keymap.*;
import org.jline.widget.*;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;

public class App {
  public static void main(String[] args) throws IOException {
    Terminal terminal = TerminalBuilder.builder()
        .system(true)
        .build();

    LineReader reader = LineReaderBuilder.builder()
        .terminal(terminal)
        .build();

    Widget right = () -> {
      terminal.writer().println("right");
      return true;
    };
    Widget left = () -> {
      terminal.writer().println("left");
      return true;
    };
    Widget up = () -> {
      terminal.writer().println("up");
      return true;
    };
    Widget down = () -> {
      terminal.writer().println("down");
      return true;
    };
    reader.getWidgets().put("right", right);
    reader.getWidgets().put("left", left);
    reader.getWidgets().put("up", up);
    reader.getWidgets().put("down", down);

    KeyMap<Binding> keyMap = reader.getKeyMaps().get(LineReader.MAIN);
    keyMap.bind(new Reference("right"), keyMap.key(terminal, InfoCmp.Capability.key_right));
    keyMap.bind(new Reference("left"), keyMap.key(terminal, InfoCmp.Capability.key_left));
    keyMap.bind(new Reference("up"), keyMap.key(terminal, InfoCmp.Capability.key_up));
    keyMap.bind(new Reference("down"), keyMap.key(terminal, InfoCmp.Capability.key_down));

    while (true) {
      String input = reader.readLine("> ");
      if (input.toLowerCase().equals("quit") || input.toLowerCase().equals("exit")) {
        try {
          terminal.close();
        } catch (Exception e) {
          terminal.writer().println("closed");
        }
        break;
      }
    }

  }
}
