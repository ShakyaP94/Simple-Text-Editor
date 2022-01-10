package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFormController {
    public TextArea txtText;
    public Path path;
    public String fileName;
    public String selectedText;
    public Button btnPaste;
    public Button btnCopy;
    public ImageView btnNewFile;
    public Button btncut;
    public ImageView btnSave;
    public ImageView btnOpenFile;

    public void btnOpenOnAction(ActionEvent actionEvent) throws IOException {
        open();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        save();

    }

    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Destination Folder");
        File file = directoryChooser.showDialog(null);
        String destinationPath = file.getAbsolutePath();
        path= Paths.get(file.getAbsolutePath());

        String fileContent=txtText.getText();
        byte[] bytes=fileContent.getBytes();
        OutputStream os=Files.newOutputStream(Paths.get(path + "/sample.txt"));
        os.write(bytes);
        os.close();
    }

    public void cut() {
       selectedText= txtText.getSelectedText();
       String[] texts=txtText.getText().split(txtText.getSelectedText());
       txtText.clear();
       for (String text:texts) {
           txtText.setText(txtText.getText() + text);
       }
        System.out.println(selectedText);

    }

    public void btnCopy(ActionEvent actionEvent) {
        selectedText=txtText.getSelectedText();
    }

    public void btnPaste(ActionEvent actionEvent) {


    }

    private void save() throws IOException {
        File ops=new File(path+"/"+fileName);

        String content=txtText.getText();

        byte[] bytes=content.getBytes();

        FileOutputStream st=new FileOutputStream(ops);
        st.write(bytes);
        st.close();

    }

    private void open() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files only", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        String sourcePath = file.getAbsolutePath();
        path= Paths.get(sourcePath);
        fileName=file.getName();

        InputStream is= Files.newInputStream(Paths.get(sourcePath));
        byte[] fileBytes=new byte[is.available()];
        is.read(fileBytes);
        String fileContent=new String(fileBytes);
        txtText.setText(fileContent);
    }

    public void btnCutOnAction(ActionEvent actionEvent) {
        cut();
    }

    public void btnPasteOnAction(ActionEvent actionEvent) {
    }

    public void btnCopyOnAction(ActionEvent actionEvent) {
    }

    public void btnOpenFileOnAction(MouseEvent mouseEvent) {
    }
}
