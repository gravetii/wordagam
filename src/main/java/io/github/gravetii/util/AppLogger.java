package io.github.gravetii.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class AppLogger {
  private static ConcurrentHashMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

  public static void fine(String c, String msg) {
    getLogger(c).fine(msg);
  }

  public static void info(String c, String msg) {
    getLogger(c).info(msg);
  }

  public static void severe(String c, String msg) {
    getLogger(c).severe(msg);
  }

  private static Logger getLogger(String c) {
    Logger logger = loggerMap.getOrDefault(c, Logger.getLogger(c));
    loggerMap.putIfAbsent(c, logger);
    return logger;
  }
}
