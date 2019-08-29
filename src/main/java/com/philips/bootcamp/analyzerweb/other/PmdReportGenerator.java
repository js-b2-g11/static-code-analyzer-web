/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.bootcamp.analyzerweb.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.philips.bootcamp.analyzerweb.model.Tool;
import com.philips.bootcamp.analyzerweb.utils.FileValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmdReportGenerator extends Tool {

  public PmdReportGenerator(String filepath, String pmdRuleset) {
    super(filepath);
    this.pmdRuleset = pmdRuleset;
  }

  public static PmdReportGenerator getPmdReportObject(String filepath) throws FileNotFoundException, IOException {
    final Properties p = new Properties();
    p.load(new FileReader("./../sca.properties"));
    final String pmdRuleset = p.getProperty("pmdRuleset");
    final PmdReportGenerator report = new PmdReportGenerator(filepath, pmdRuleset);
    return report;
  }

  @Override
  public void generateReport() throws IOException {
    if (isValidReport()) {
      final String command[] = new String[] { "pmd.bat", "-d", this.getFilepath(), "-f", "text", "-R", pmdRuleset};
      final Runtime rt = Runtime.getRuntime();

      Process pmdProcess;
      pmdProcess = rt.exec(command);

      final JavaFileGetter jfg = new JavaFileGetter();
      final List<String> result = jfg.getFile(this.getFilepath());

      final BufferedReader stdInput = new BufferedReader(new InputStreamReader(pmdProcess.getInputStream()));

      String s = null;
      while ((s = stdInput.readLine()) != null) {

        for (int i = 0; i < result.size(); i++) {
          final File file = new File(result.get(i));
          final String fileNameWithOutExt = file.getName().replaceFirst("[.][^.]+$", "");
          final Pattern p = Pattern.compile("^.*\\b(" + result.get(i).replace("\\", "\\\\") + ")\\b.*$");
          final Matcher m = p.matcher(s);
          if (m.find()) {
            final BufferedWriter writer = new BufferedWriter(
                new FileWriter("./reports/" + fileNameWithOutExt + ".txt", true) // Set true for append
                // mode
                );
            writer.newLine(); // Add new line
            writer.write(m.group());
            writer.close();
          }
        }
      }
      System.out.print("PMD report generated\n");
    }
    else {
      System.out.println("Invalid/Empty file specified!");
    }
  }

  @Override
  public boolean isValidReport() {
    if (this.getFilepath().equals(null) || this.getFilepath() == "") {
      return false;
    }
    return (FileValidator.isValidPath(this.getFilepath()));
  }
}