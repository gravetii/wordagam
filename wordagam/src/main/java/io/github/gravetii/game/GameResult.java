package io.github.gravetii.game;

import io.github.gravetii.pojo.GamePlayResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

  private Map<String, Integer> wordPoints = new HashMap<>();
  private Map<String, GamePlayResult> wordToResultMap = new LinkedHashMap<>();
  private int totalPoints = 0;

  public GameResult() {
  }

  
}
