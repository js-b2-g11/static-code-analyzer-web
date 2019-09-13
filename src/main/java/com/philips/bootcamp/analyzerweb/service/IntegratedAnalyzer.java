/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.philips.bootcamp.analyzerweb.exceptions.FilePathNotValidException;
import com.philips.bootcamp.analyzerweb.model.AbstractTool;

public class IntegratedAnalyzer extends AbstractTool{

  private final List<AbstractTool> tools = new ArrayList<>();

  public IntegratedAnalyzer(String filepath) {
    super(filepath);
  }

  @Override
  public StringBuilder generateReport() throws IOException, InterruptedException, FilePathNotValidException  {
    final StringBuilder finalReport = new StringBuilder();
    for (final AbstractTool tool : tools) {
      finalReport.append(tool.generateReport());
      finalReport.append("\n");
    }
    return finalReport;
  }



  @Override
  public void add(AbstractTool tool) {
    tools.add(tool);
  }

  @Override
  public void remove(AbstractTool tool) {
    tools.remove(tool);
  }

  @Override
  public boolean isValidReport() {
    return false;
  }

}