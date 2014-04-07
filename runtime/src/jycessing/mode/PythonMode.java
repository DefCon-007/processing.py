package jycessing.mode;

import java.io.File;
import java.io.IOException;

import processing.app.Base;
import processing.app.Editor;
import processing.app.EditorState;
import processing.app.Formatter;
import processing.app.Mode;
import processing.app.syntax.TokenMarker;

public class PythonMode extends Mode {

  private final FormatServer formatServer;

  public PythonMode(final Base base, final File folder) {
    super(base, folder);
    formatServer = new FormatServer(folder);
    formatServer.startup();
  }

  @Override
  public String getTitle() {
    return "Python";
  }

  @Override
  public Editor createEditor(final Base base, final String path, final EditorState state) {
    return new PyEditor(base, path, state, this);
  }

  @Override
  public String getDefaultExtension() {
    return "pde";
  }

  @Override
  public String[] getExtensions() {
    return new String[] { "pde", "py", "pyde" };
  }

  @Override
  public String[] getIgnorable() {
    return new String[] {};
  }

  Formatter getFormatter() {
    return formatServer;
  }

  @Override
  protected void loadKeywords(File keywordFile) throws IOException {
    loadKeywords(keywordFile, "//");
  }

  @Override
  public TokenMarker createTokenMarker() {
    return new PythonTokenMarker();
  }
}