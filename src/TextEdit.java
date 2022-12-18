import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEdit implements ActionListener {
    //frame for textEditor
    JFrame frame;
    JMenuBar menuBar;

    //menus
    JMenu file, edit;

    //menuItems for file menu
    JMenuItem newFile, openFile, saveFile;

    //menuItems for edit menu
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    //constructor
    TextEdit() {
        //initialize frame
        frame = new JFrame();

        //initialize textArea
        textArea = new JTextArea();

        //initialize MenuBar
        menuBar = new JMenuBar();

        //initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //add file menu to MenuBar
        menuBar.add(file);

        //add edit menu to MenuBar
        menuBar.add(edit);

        //initialize menu items for file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //add actionListener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //adding menu items for file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize menu items for edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        //add actionListener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menu items for edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add textArea to frame
        frame.add(textArea);

        //add MenuBar to frame
        frame.setJMenuBar(menuBar);

        //set dimension for frame
        frame.setBounds(100, 100, 800, 500);

        ///mark frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //if source is cut
        if(actionEvent.getSource() == cut) {
            //perform cut
             textArea.cut();
        }
        //if source is copy
        if(actionEvent.getSource() == copy) {
            //perform copy
            textArea.copy();
        }
        //if source is paste
        if(actionEvent.getSource() == paste) {
            //perform paste
            textArea.paste();
        }
        //if source is selectAll
        if(actionEvent.getSource() == selectAll) {
            //perform selectAll
            textArea.selectAll();
        }
        //if source is close
        if(actionEvent.getSource() == close) {
            //perform close
            System.exit(0);
        }
        //if source is new
        if(actionEvent.getSource() == newFile) {
            //create new window
            TextEdit newTextEditor = new TextEdit();
        }
        //if source is open
        if(actionEvent.getSource() == openFile) {
            //open a text file
            //initialized file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //if choose option is equal to approve
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //get selected file
                File file = fileChooser.getSelectedFile();
                //get selected file path
                String filePath = file.getPath();
                try{
                    //created buffer reader
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    //create strings to store content from file
                    String intermediate = "", output = "";
                    //read content line by line
                    while((intermediate = bufferedReader.readLine())!=null) {
                        output+=intermediate+"\n";
                    }
                    //set output to text area
                    textArea.setText(output);
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        //if source is saveFile
        if(actionEvent.getSource() == saveFile) {
            //save a file
            //create a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if choose option approve
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //create file object with selected pathgit
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // create buffer writer
                    BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
                    //get content from text area to outfile
                    textArea.write(outFile);
                    outFile.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        //initialize textEditor
        TextEdit textEdit = new TextEdit();
    }
}