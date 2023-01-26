package edu.miu.mpp.librarysystem.utility;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPropertyExtractor {
  public  static  String findProperties(List<String> properties,String source) {
            AtomicReference<String> response= new AtomicReference<>("");

            properties.forEach(property -> {
                Matcher m = Pattern.compile(property+"=.*?(?=,|})").matcher(source);
                while (m.find())
                    response.set(response.get().concat(m.group(0).replace("'","").concat("\n")));
            });
      return response.get();

  }
}
