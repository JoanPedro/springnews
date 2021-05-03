package reviews.main.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
  public static List<Integer> decodeIntList(String s) {
    List<String> vet = Arrays.asList(s.split(","));
    return vet.stream().map(Integer::parseInt).collect(Collectors.toList());
  }

  public static String decodeParam(String s) {
    try {
      return URLDecoder.decode(s, StandardCharsets.UTF_8);
    } catch (Exception exception) {
      return "";
    }
  }
}
