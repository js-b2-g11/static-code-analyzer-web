/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;

public class IntegratedAnalyzer extends Tool{

  private final List<Tool> tools = new ArrayList<>();

  public IntegratedAnalyzer(String filepath) {
    super(filepath);
  }

  @Override
  public String generateReport() throws IOException {
    String finalReport = null;
    for (final Tool tool : tools) {
      finalReport += tool.generateReport();
    }
    return finalReport;
  }

  @Override
  public boolean isValidReport() {
    if (this.getFilepath() == null || this.getFilepath().equals("")) {
      return false;
    }
    return (FileValidator.isValidPath(this.getFilepath()));
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder("Integrated Analyzer: ");
    builder.append(filepath);
    builder.append(",");
    builder.append(tools);
    builder.append(",");

    return builder.toString();
  }

  @Override
  public void add(Tool tool) {
    tools.add(tool);
  }

  @Override
  public void remove(Tool tool) {
    tools.remove(tool);
  }

}
