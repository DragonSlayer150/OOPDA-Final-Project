/**
 * 2D Frame for 2D Graphics
 * 
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
import javax.swing.*;
import java.awt.*;

public class CanvasFrame_P7
{
    private JFrame frame;       // the actual frame(window) we'll be showing
    private CanvasPanel_P7 canvas; // the canvas we'll be drawing
    
    /**
     * Creates a new CanvasFrame object.
     */
    public CanvasFrame_P7() {
        frame = new JFrame("OPPDA Spring 2025 CanvasFrame"); //make the JFrame, and set thw window bar title 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new CanvasPanel_P7();  // CanvasPanel extends a JPanel
        
        // Use the canvasPanel size & borders to define window size
        canvas.setPreferredSize(new Dimension(2 * CanvasPanel_P7.getCanvasXBorder() + CanvasPanel_P7.getCanvasWidth(),
                                              2 * CanvasPanel_P7.getCanvasYBorder() + CanvasPanel_P7.getCanvasHeight() ));
        frame.getContentPane().add(canvas); //put the canvas (JPanel) in the frame
        frame.setResizable(false);
        frame.pack();                       //make everything the preferred size
        frame.setVisible(true);             //show the frame
    }
}
