# Running Instructions

This is written using javafx 21.0.2, and is not tested on other versions.

```bash
javac --module-path <your-config>/javafx-sdk-21.0.2/lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,javafx.swt -d out src/**/*.java
```

cp -r src/sprites out/
```bash
java --module-path <your-config>/javafx-sdk-21.0.2/lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,javafx.swt -cp out model.Bloons
```

