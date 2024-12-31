package a02b.e2;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame {
    
    private final Map<JButton,Pair<Integer,Integer>> cells = new LinkedHashMap<>();
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton go = new JButton("Check > Restart");
        main.add(BorderLayout.SOUTH, go);
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(i,j));
                panel.add(jb);
            }
        }

        Logics logics = new LogicsImpl(cells.values()); 
        ActionListener alGo = (ActionEvent e) -> {
            logics.setPressedGo();
            if(logics.quit()){
                restart();
                logics.restart();
            }
            else{
                List<Pair<Integer, Integer>> l = logics.check(size);
                if(l != null){
                    cells.entrySet().stream()
                            .filter(en -> l.contains(en.getValue()))
                            .forEach(en -> en.getKey()
                            .setEnabled(false));
                }
            }
        };

        ActionListener al = (ActionEvent e) -> {
            var button = (JButton)e.getSource();
            //var position = cells.indexOf(button);
            if (button.getText().equals("*")){
                button.setText("");
            }
            else{
                button.setText("*");
                logics.setSelected(cells.get(button));
            }
        };
        cells.entrySet().stream().forEach(c -> c.getKey().addActionListener(al));

        go.addActionListener(alGo);
        this.setVisible(true);
    }    

    private void restart(){
        cells.entrySet().forEach(e -> {
            var butt = e.getKey();
            butt.setText("");
            butt.setEnabled(true);
        });
    }
}
