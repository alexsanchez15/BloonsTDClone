# Bloons Tower Defense Game Clone -

A personal project to practice writing larger scale code myself in java, learn and grow. Started May 2024

This is written using javafx 21.0.2, and is not tested on other versions.

This is without maven/gradel, with manual javafx setup. Below instructs how to run

Your terminal must support globbing for these steps to work, so some windows shells may have issues

### Running instructions:

Download javafx SDK from https://gluonhq.com/products/javafx/ (21.0.2 is an older and now unsupported version at this time)

### 1. clone the repo:

```bash
git clone https://github.com/alexsanchez15/BloonsTDClone.git
cd BloonsTDClone
```
We will stay in this directory for the remainder of the steps.

### 2. Compile the application

```bash
javac --module-path <your-config>/javafx-sdk-21.0.2/lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -d out src/**/*.java
```
Run this command in the /BloonsTDClone location, and replace <your-config> to the location of the javafx SDK on your machine.

### 3. Copy over spirtes

```bash
cp -r src/sprites out/
```
Must be done manually, image files are not compiled by java naturally.

### 4. Run the game

```bash
java --module-path "C:/Users/alex/Desktop/javafx-sdk-21.0.2/lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -cp out model.Bloons
```
Again, replace <your-config> with the path to the javafx SDK on your machine.

This project currently is unpolished especially in terms of the art, but exists as a proof of idea for now, as logically works as I intended
(although I plan to polish at least somewhat more in the future)

