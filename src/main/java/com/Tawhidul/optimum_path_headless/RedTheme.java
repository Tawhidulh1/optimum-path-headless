package com.Tawhidul.optimum_path_headless;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;

public class RedTheme extends SimpleTheme {

  public RedTheme() {
    super(TextColor.ANSI.RED, TextColor.ANSI.BLACK_BRIGHT, SGR.BOLD);

  }

}
