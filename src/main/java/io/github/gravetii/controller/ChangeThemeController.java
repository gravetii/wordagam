package io.github.gravetii.controller;

import io.github.gravetii.theme.ThemeService;
import io.github.gravetii.theme.ThemeType;
import io.github.gravetii.util.Utils;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ChangeThemeController implements FxController {
  private final Stage stage;
  private final ChangeThemeFooterController ref;
  private final Map<Integer, ImageView> imgViewMap;
  private final ThemeService service;
  private final ChangeThemeStyler styler;

  @FXML private ImageView imgView_0;
  @FXML private ImageView imgView_1;
  @FXML private ImageView imgView_2;
  @FXML private ImageView imgView_3;
  @FXML private ImageView imgView_4;
  @FXML private ImageView imgView_5;
  @FXML private ImageView imgView_6;
  @FXML private ImageView imgView_7;
  @FXML private ImageView imgView_8;
  @FXML private ImageView imgView_9;
  @FXML private ImageView imgView_10;
  @FXML private ImageView imgView_11;
  @FXML private ImageView imgView_12;
  @FXML private ImageView imgView_13;
  @FXML private ImageView imgView_14;

  public ChangeThemeController(Stage stage, ChangeThemeFooterController ref) {
    this.stage = stage;
    this.ref = ref;
    this.imgViewMap = new HashMap<>();
    this.service = new ThemeService();
    this.styler = new ChangeThemeStyler();
  }

  @FXML
  public void initialize() {
    this.imgViewMap.put(0, this.imgView_0);
    this.imgViewMap.put(1, this.imgView_1);
    this.imgViewMap.put(2, this.imgView_2);
    this.imgViewMap.put(3, this.imgView_3);
    this.imgViewMap.put(4, this.imgView_4);
    this.imgViewMap.put(5, this.imgView_5);
    this.imgViewMap.put(6, this.imgView_6);
    this.imgViewMap.put(7, this.imgView_7);
    this.imgViewMap.put(8, this.imgView_8);
    this.imgViewMap.put(9, this.imgView_9);
    this.imgViewMap.put(10, this.imgView_10);
    this.imgViewMap.put(11, this.imgView_11);
    this.imgViewMap.put(12, this.imgView_12);
    this.imgViewMap.put(13, this.imgView_13);
    this.imgViewMap.put(14, this.imgView_14);
    this.initStyle();
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    if (!event.getButton().equals(MouseButton.PRIMARY)) return;
    ImageView imgView = (ImageView) event.getSource();
    int idx = Utils.getImageViewIndexFromLabel(imgView.getId());
    if (idx >= this.service.getAll().size()) return;
    ThemeType type = this.service.getAll().get(idx);
    this.styler.applySelectStyle(imgView);
    if (this.service.changeTheme(type)) {
      this.service.dispatch(this.stage.getOwner());
      this.ref.updateThemeChange(type);
    }

    if (event.getClickCount() == 2) stage.close();
  }

  private void initStyle() {
    ThemeType current = this.service.getCurrent();
    int idx = this.service.getAll().indexOf(current);
    ImageView imgView = this.imgViewMap.get(idx);
    this.styler.applySelectStyle(imgView);
  }
}
