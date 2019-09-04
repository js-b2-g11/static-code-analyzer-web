/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.model;

import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractTool {

  protected String filepath;

  public String getFilepath() {
    return filepath;
  }

  public AbstractTool(final String filepath) {
    this.filepath = filepath;
  }

  public abstract StringBuilder generateReport() throws IOException;

  public abstract boolean isValidReport();

  public void add(final AbstractTool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }

  public void remove(final AbstractTool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }

  @Override
  public abstract String toString();
}
