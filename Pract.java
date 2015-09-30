import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.SortedMap;


public class Pract extends JFrame
{
    final static JTextPane textArea = new JTextPane();

    public static void main(String[] args)
    {
        final JFrame frame = new JFrame("notepad");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        final JFileChooser fc = new JFileChooser();
        frame.setLayout(new GridBagLayout());
        final JLabel labelForAllText = new JLabel("change the text");
        final JScrollPane scrollPane = new JScrollPane(textArea);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsEnvironment.getAvailableFontFamilyNames();
        String[] fonts = graphicsEnvironment.getAvailableFontFamilyNames();
        final JComboBox<String> comboBoxFonts = new JComboBox<>(fonts);
        final JComboBox<String> comboBoxFontsAllText = new JComboBox<>(fonts);
        String[] petListColors = {"White", "Red", "Blue", "Black"};
        final JComboBox<String> comboBoxColors = new JComboBox<>(petListColors);
        final JComboBox<String> comboBoxColorsAllText = new JComboBox<>(petListColors);
        String[] petListSize = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        final JComboBox<String> comboBoxSize = new JComboBox<>(petListSize);
        final JComboBox<String> comboBoxSizeAllText = new JComboBox<>(petListSize);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JToolBar tb = new JToolBar();
        JButton buttonForFind = new JButton("find a word");
        buttonForFind.setFocusPainted(false);
        final JTextField textFieldFind = new JTextField();
        JButton buttonForReplace = new JButton("replace on");
        buttonForReplace.setFocusPainted(false);
        final JTextField textFieldReplace = new JTextField();
        comboBoxColorsAllText.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                Style style = textArea.addStyle("MyHilite", null);
                switch ((String) comboBoxColorsAllText.getSelectedItem())
                {
                    case "White":
                        StyleConstants.setForeground(style, Color.white);
                        doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
                        break;
                    case "Blue":
                        StyleConstants.setForeground(style, Color.blue);
                        doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
                        break;
                    case "Red":
                        StyleConstants.setForeground(style, Color.red);
                        doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
                        break;
                    case "Black":
                        StyleConstants.setForeground(style, Color.black);
                        doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
                        break;
                }
            }
        });
        comboBoxFontsAllText.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                Style style = textArea.addStyle("MyHilite", null);
                StyleConstants.setFontFamily(style, ((String) comboBoxFontsAllText.getSelectedItem()));
                //style = textPane.getStyle("MyHilite");
                doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
            }
        });
        comboBoxSizeAllText.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                Style style = textArea.addStyle("MyHilite", null);
                StyleConstants.setFontSize(style, Integer.parseInt((String) comboBoxSizeAllText.getSelectedItem()));
                //style = textPane.getStyle("MyHilite");
                doc.setCharacterAttributes(0, textArea.getText().length(), style, false);
            }
        });
        buttonForReplace.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String replacedString = textArea.getText().replaceAll(textFieldFind.getText(), textFieldReplace.getText());
                textArea.setText(replacedString);
            }
        });
        buttonForFind.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Highlighter h = textArea.getHighlighter();
                h.removeAllHighlights();
                String word = textFieldFind.getText();
                Document doc = textArea.getDocument();
                try
                {
                    String text = doc.getText(0, doc.getLength());
                    if (!word.equals(""))
                    {
                        int pos = 0;
                        while ((pos = text.toUpperCase().indexOf(word.toUpperCase(), pos)) >= 0)
                        {
                            h.addHighlight(pos, pos + word.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                            pos += word.length();
                        }
                    }
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        tb.setLayout(new GridBagLayout());
        tb.add(buttonForFind, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(textFieldFind, new GridBagConstraints(1, 0, 2, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(labelForAllText, new GridBagConstraints(3, 0, 1, 3, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxColorsAllText, new GridBagConstraints(4, 0, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxFontsAllText, new GridBagConstraints(4, 1, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxSizeAllText, new GridBagConstraints(4, 2, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(buttonForReplace, new GridBagConstraints(0, 1, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(textFieldReplace, new GridBagConstraints(1, 1, 2, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxFonts, new GridBagConstraints(0, 2, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxColors, new GridBagConstraints(1, 2, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        tb.add(comboBoxSize, new GridBagConstraints(2, 2, 1, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        comboBoxSize.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start == end)
                { // No selection, cursor position.
                    return;
                }
                if (start > end)
                { // Backwards selection?
                    int life = start;
                    start = end;
                    end = life;
                }
                Style style = textArea.addStyle("MyHilite", null);
                StyleConstants.setFontSize(style, Integer.parseInt((String) comboBoxSize.getSelectedItem()));
                //style = textPane.getStyle("MyHilite");
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });
        comboBoxFonts.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start == end)
                { // No selection, cursor position.
                    return;
                }
                if (start > end)
                { // Backwards selection?
                    int life = start;
                    start = end;
                    end = life;
                }
                Style style = textArea.addStyle("MyHilite", null);
                StyleConstants.setFontFamily(style, (String) comboBoxFonts.getSelectedItem());
                //style = textPane.getStyle("MyHilite");
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });
        comboBoxColors.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StyledDocument doc = textArea.getStyledDocument();
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start == end)
                { // No selection, cursor position.
                    return;
                }
                if (start > end)
                { // Backwards selection?
                    int life = start;
                    start = end;
                    end = life;
                }
                Style style = textArea.addStyle("MyHilite", null);
                switch ((String) comboBoxColors.getSelectedItem())
                {
                    case "White":
                        StyleConstants.setForeground(style, Color.white);
                        break;
                    case "Blue":
                        StyleConstants.setForeground(style, Color.blue);
                        break;
                    case "Red":
                        StyleConstants.setForeground(style, Color.red);
                        break;
                    case "Black":
                        StyleConstants.setForeground(style, Color.black);
                        break;
                }
                //style = textPane.getStyle("MyHilite");
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });
        SortedMap<String, Charset> map = Charset.availableCharsets();
        map.keySet().toArray(new String[map.keySet().size()]);
        String[] codings = map.keySet().toArray(new String[map.keySet().size()]);
        final JPanel panelForCombo = new JPanel(new GridBagLayout());
        JLabel labelForCodings = new JLabel("choose coding");
        final JComboBox<String> comboBoxCodings = new JComboBox<>(codings);
        comboBoxCodings.setSelectedItem("UTF-8");
        panelForCombo.add(labelForCodings, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.9, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelForCombo.add(comboBoxCodings, new GridBagConstraints(0, 1, 1, 1, 0.1, 0.9, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        fc.setAccessory(panelForCombo);
        JMenuBar menu = new JMenuBar();
        JMenu m = new JMenu("file");
        JMenuItem openItem = new JMenuItem("open file");
        JMenuItem writeItem = new JMenuItem("save file");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    try (FileInputStream fis = new FileInputStream(file))
                    {
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        String sb = new String(buffer);
                        textArea.setText(getCodingStr(sb, (String) comboBoxCodings.getSelectedItem()));
                        frame.add(scrollPane, new GridBagConstraints(0, 3, 3, 1, 0.1, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(2, 2, 2, 2), 10, 10));
                        frame.revalidate();
                        frame.repaint();
                        frame.setSize(700, 400);
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }

                }
                if (returnVal == JFileChooser.CANCEL_OPTION)
                {
                    fc.cancelSelection();
                }

            }
        });
        writeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fc.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    try (FileOutputStream fos = new FileOutputStream(file))
                    {
                        String forWrite;
                        forWrite = new String(textArea.getText().getBytes(), (String) comboBoxCodings.getSelectedItem());
                        fos.write(forWrite.getBytes());
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }

                }
                if (returnVal == JFileChooser.CANCEL_OPTION)
                {
                    fc.cancelSelection();
                }
            }
        });
        m.add(openItem);
        m.add(writeItem);
        menu.add(m);
        frame.add(tb, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 10, 10));
        frame.setJMenuBar(menu);
        frame.pack();
        frame.setVisible(true);
    }

    public static String getCodingStr(String string, String code) throws Exception
    {
        String str;
        Charset coding = Charset.forName(code);
        str = new String(string.getBytes(), coding);
        return str;
    }
}
