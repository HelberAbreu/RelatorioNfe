import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JProgressBar;  
import javax.swing.SwingUtilities;  
  
public class ProgressBarExamplo extends JPanel {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = -3510692886926771783L;  
  
    JProgressBar pbar;  
  
    static final int MY_MINIMUM = 0;  
  
    static final int MY_MAXIMUM = 100;  
  
    public ProgressBarExamplo() {  
        pbar = new JProgressBar();  
        pbar.setMinimum(MY_MINIMUM);  
        pbar.setMaximum(MY_MAXIMUM);  
        add(pbar);  
    }  
  
    public void atualizaBarra(int valor) {  
        pbar.setValue(valor);  
    }  
  
    public static void main(String args[]) {  
  
        final ProgressBarExamplo barra = new ProgressBarExamplo();  
  
        JFrame frame = new JFrame("Progress Bar Example");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setContentPane(barra);  
        frame.pack();  
        frame.setVisible(true);  
        frame.setLocation(400, 300);  
  
        for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {  
            final int percent = i;  
            try {  
                SwingUtilities.invokeLater(new Runnable() {  
                    public void run() {  
                        barra.atualizaBarra(percent);  
                    }  
                });  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                ;  
            }  
        }  
    }  
}