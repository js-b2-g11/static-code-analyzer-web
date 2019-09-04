/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.model;

import java.io.IOException;

import com.philips.bootcamp.analyzerweb.utils.CommandLine.ShellCommandException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Tool {

  protected String filepath;

  public String getFilepath() {
    return filepath;
  }

  public Tool(String filepath) {
    this.filepath = filepath;
  }

  public abstract String generateReport() throws ShellCommandException;

  public abstract boolean isValidReport();

  public void add(Tool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }

  public void remove(Tool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }

  @Override
  public abstract String toString();
}
