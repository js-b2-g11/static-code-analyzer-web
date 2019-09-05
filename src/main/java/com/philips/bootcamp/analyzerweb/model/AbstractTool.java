/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.model;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */

public abstract class AbstractTool {

  protected String filepath;

  public String getFilepath() {
    return filepath;
  }

  public AbstractTool(String filepath) {
    this.filepath = filepath;
  }

  public abstract String generateReport() throws IOException, TimeoutException, InterruptedException;

  public abstract boolean isValidReport();

  public void add(final AbstractTool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }

  public void remove(final AbstractTool tool) {
    throw new UnsupportedOperationException("Cannot add tool by default");
  }
}

