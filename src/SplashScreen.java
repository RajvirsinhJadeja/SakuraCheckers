import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
@SuppressWarnings("serial")

/**
 * Provides a Splash Screen Introduction.
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */
public class SplashScreen extends JFrame 
{
	/**
     * Constructs a new SplashScreen object.
     */
    public SplashScreen() 
    {
        initUI();
    }
    
    /**
     * Initializes the user interface components.
     */
    private void initUI() 
    {
        setLayout(new BorderLayout());
        
        BufferedImage imageSplashScreen = null;
 	   	BufferedImage imageLogo = null;
 	   	try 
 	   	{
 	   		imageSplashScreen = ImageIO.read(getClass().getResource("CheckersBoardGameLogoSplashScreen.png"));
 	   		imageLogo = ImageIO.read(getClass().getResource("CheckersBoardGameLogoWindows.png"));
 	   	}
 	   	catch (IOException e) 
 	   	{
 	   	    e.printStackTrace();
 	   	}
 	   	
        ImageIcon logoIcon = new ImageIcon("CheckersBoardGameLogoSplashScreen.png");
        setIconImage(imageLogo);
        Image scaledLogo = imageSplashScreen.getScaledInstance(450, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledLogo);

        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        Timer logoTimer = new Timer(3000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fadeOutLogo(logoLabel);
                ((Timer) e.getSource()).stop();
            }
        });
        logoTimer.setRepeats(false);

        logoTimer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
    
    /**
     * Fades out the logo label.
     *
     * @param logoLabel The JLabel to be faded out.
     */
    private void fadeOutLogo(final JLabel logoLabel) 
    {
        Timer fadeTimer = new Timer(20, new ActionListener() 
        {
            private float alpha = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                alpha = Math.max(0.0f, alpha - 0.02f);
                if (alpha <= 0) 
                {
                    ((Timer) e.getSource()).stop();
                    showMenu();
                }
                logoLabel.setIcon(updateAlpha(logoLabel.getIcon(), alpha));
            }
        });

        fadeTimer.start();
    }
    
    /**
     * Shows the main menu.
     */
    private void showMenu() 
    {
        StartGame startGame = new StartGame();
        startGame.setVisible(true);

        dispose();
    }
    
    /**
     * Updates the alpha channel of the given icon.
     *
     * @param icon  The icon to be updated.
     * @param alpha The new alpha value.
     * @return      The updated ImageIcon with the specified alpha value.
     */
    private ImageIcon updateAlpha(Icon icon, float alpha) 
    {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return new ImageIcon(buffer);
    }
    
    /**
     * The entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    @SuppressWarnings("unused")
					SplashScreen frame = new SplashScreen();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
