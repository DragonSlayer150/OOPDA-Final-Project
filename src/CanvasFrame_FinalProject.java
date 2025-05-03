/**
 * 2D Frame for 2D Graphics
 * 
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
import javax.swing.*;
import java.awt.*;

public class CanvasFrame_FinalProject
{
    private JFrame frame;       // the actual frame(window) we'll be showing
    private CanvasPanel_FinalProject canvas; // the canvas we'll be drawing
    
    /**
     * Creates a new CanvasFrame object.
     */
    public CanvasFrame_FinalProject() {
        frame = new JFrame("OPPDA Spring 2025 CanvasFrame"); //make the JFrame, and set thw window bar title 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new CanvasPanel_FinalProject();  // CanvasPanel extends a JPanel
        
        // Use the canvasPanel size & borders to define window size
        canvas.setPreferredSize(new Dimension(2 * CanvasPanel_FinalProject.getCanvasXBorder() + CanvasPanel_FinalProject.getCanvasWidth(),
                                              2 * CanvasPanel_FinalProject.getCanvasYBorder() + CanvasPanel_FinalProject.getCanvasHeight() ));
        frame.getContentPane().add(canvas); //put the canvas (JPanel) in the frame
        frame.setResizable(false);
        frame.pack();                       //make everything the preferred size
        frame.setVisible(true);             //show the frame
    }
}
