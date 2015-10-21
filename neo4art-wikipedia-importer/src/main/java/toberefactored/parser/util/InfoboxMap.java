package toberefactored.parser.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxMap {

  private static Log logger = LogFactory.getLog(InfoboxMap.class);
  
  private static final String ONE_OR_MORE_NEW_LINES = "[\\n\\r]+";
  private static final String ZERO_OR_MORE_SPACES   = "[\\s]*";
  private static final String JUST_ONE_PIPE         = "[\\|]{1}";
  private static final String JUST_ONE_EQUAL        = "[=]{1}";
  private static final String INFO_KEY              = "([\\S]+)";
  private static final String INFO_VALUE            = "([\\S\\s&&[^\\n^\\r]]*)";

  /**
   * @param text
   * @return
   */
  public static Map<String, String> asMap(String text) {

    Map<String, String> map = new HashMap<String, String>();

    String patternRegex = 
        ONE_OR_MORE_NEW_LINES + 
        ZERO_OR_MORE_SPACES + 
        JUST_ONE_PIPE + 
        ZERO_OR_MORE_SPACES + 
        INFO_KEY + 
        ZERO_OR_MORE_SPACES + 
        JUST_ONE_EQUAL + 
        //ZERO_OR_MORE_SPACES +
        INFO_VALUE;

    Pattern pattern = Pattern.compile(patternRegex);
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {

      if (logger.isTraceEnabled()) {
        for (int i = 1; i <= matcher.groupCount(); i++) {
          logger.trace(i + " [" + matcher.start(i) + "," + matcher.end() + "]:" + matcher.group(i));
        }
      }
      
      map.put(StringUtils.trim(matcher.group(1)), StringUtils.trim(matcher.group(2)));
    }

    return map;
  }
}
