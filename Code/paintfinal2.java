/*<Applet code = "paintfinal2" height = "625" width = "1345"> </Applet>*/
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.*;

public class paintfinal2 extends Applet implements ActionListener, AdjustmentListener, MouseListener, MouseMotionListener
{
 /* Operation Constants */
 //private Image dbImage; 
 //private Graphics dbg; 
	  //Image backbuffer;
 //Graphics backg;
 Image img= null;
 String msg;
 private final int  PEN_OP         = 1;
 private final int  LINE_OP        = 2;
 private final int  ERASER_OP      = 3;
 private final int  CLEAR_OP       = 4;
 private final int  RECT_OP        = 5;
 private final int  OVAL_OP        = 6;
 private final int  FRECT_OP       = 7;
 private final int  FOVAL_OP       = 8;
 private final int  POLY_OP        = 10;
 private final int  SQR_OP		   = 11;
 private final int  CIR_OP		   = 12;
 private final int  FSQR_OP		   = 13;
 private final int  FCIR_OP		   = 14;
 private final int  BG_OP	   	   = 15;
 private final int  BRSH_OP        = 16; 
 private final int  CRV_OP       = 17; 
 
 /* Current mouse coordinates */
 private int mousex                = 0;
 private int mousey                = 0;

 /* Previous mouse coordinates */
 private int premousex                 = 0;
 private int premousey                 = 0;

 /* Initial state flags for operation */
 private boolean penFlag        = true;
 private boolean lineFlag       = true;
 private boolean eraserFlag     = true;
 private boolean rectFlag       = true;
 private boolean ovalFlag       = true;
 private boolean frectFlag      = true;
 private boolean fovalFlag      = true;
 private boolean polygonFlag    = true;
 private boolean sqrFlag	    = true;
 private boolean cirFlag	    = true;
 private boolean fsqrFlag	    = true;
 private boolean fcirFlag	    = true;
 private boolean bgFlag			= true;
 private boolean brshFlag       = true;
 private boolean crvFlag       = true;
 
 /* Main Mouse X and Y coordiante variables */
 private int  Orx                  = 0;
 private int  Ory                  = 0;
 private int  OrWidth              = 0;
 private int  OrHeight             = 0;
 private int  drawX                = 0;
 private int  drawY                = 0;
 private int  eraserLength         = 10;
 private int  udefRedValue         = 255;
 private int  udefGreenValue       = 255;
 private int  udefBlueValue        = 255;

 /* Primitive status & color variables */
 private int    opStatus           = PEN_OP;
 private int    colorStatus        = 1;
 private Color  mainColor          = new Color(0,0,0);
 private Color  xorColor           = new Color(255,255,255);
 private Color  statusBarColor     = new Color(150,150,150);
 private Color  Brown			   = new Color(166,68,26);
 private Color  Dgreen			   = new Color(0,137,0);
 private Color  violet			   = new Color(72,16,76);
 private Color  userDefinedColor   = new Color(udefRedValue,udefGreenValue,udefBlueValue);

 JButton penButton = new JButton(new ImageIcon("pencil1.gif"));
 JButton eraserButton = new JButton(new ImageIcon("eraser1.gif"));
 JButton lineButton = new JButton(new ImageIcon("line.gif"));
 JButton brshButton = new JButton(new ImageIcon("brush1.gif"));
 JButton rectButton = new JButton(new ImageIcon("rectangle.gif"));
 JButton polygonButton = new JButton(new ImageIcon("polygon.gif"));
 JButton ovalButton = new JButton(new ImageIcon("oval.gif"));
 JButton sqrButton = new JButton(new ImageIcon("square1.gif"));
 JButton cirButton = new JButton(new ImageIcon("circle1.gif"));
 JButton fillRectButton = new JButton(new ImageIcon("frect.gif"));
 JButton fillOvalButton = new JButton(new ImageIcon("foval.gif"));
 JButton fcirButton = new JButton(new ImageIcon("fcir.gif"));
 JButton fsqrButton = new JButton(new ImageIcon("fsqr.gif"));
 JButton bgButton = new JButton(new ImageIcon("bg.gif"));
 JButton clearButton = new JButton(new ImageIcon("clear.gif"));
 JButton saveButton = new JButton(new ImageIcon("save.gif"));
 JButton imageButton        = new JButton("Load");
 JButton crvButton       = new JButton(new ImageIcon("curve.gif"));
 	/* Operation Button definitions */
 //private Button penButton          = new Button("Pen");
 //private Button lineButton         = new Button("Line");
 //private Button eraserButton       = new Button("Eraser");
 //private Button clearButton        = new Button("Clear");
 //private Button rectButton         = new Button("Rect");
 //private Button sqrButton	   	   = new Button("Sqr");
 //private Button ovalButton         = new Button("Oval");
 //private Button fillRectButton     = new Button("FRect");
 //private Button fillOvalButton     = new Button("FOval");
 //private Button polygonButton      = new Button("Polygon");
 //private Button cirButton          = new Button("Cir");
 //private Button fsqrButton         = new Button("Fsqr");
 //private Button fcirButton         = new Button("Fcir");
 //private Button bgButton		   = new Button("Bg");
// private Button brshButton         = new Button("Brush");
 
 /* Color Button definitions */
 
 private Button blackButton        = new Button("Black");
 private Button blueButton         = new Button("Blue");
 private Button redButton          = new Button("Red");
 private Button greenButton        = new Button("Green");
 private Button purpleButton       = new Button("Purple");
 private Button orangeButton       = new Button("Orange");
 private Button pinkButton         = new Button("Pink");
 private Button grayButton         = new Button("Gray");
 private Button yellowButton       = new Button("Yellow");
 private Button cyanButton         = new Button("Cyan");
 private Button brownButton        = new Button("Brown");
 private Button dgreenButton       = new Button("Dgreen");
 private Button violetButton        = new Button("Violet");

 private Button userDefButton      = new Button("UDef");

 /* User defined Color variables */
 private Scrollbar redSlider       = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1, 0, 255);
 private Scrollbar blueSlider      = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1, 0, 255);
 private Scrollbar greenSlider     = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1, 0, 255);

 /* Assorted status values for different variables */
 private TextField colorStatusBar  = new TextField(20);
 private TextField opStatusBar     = new TextField(20);
 private TextField mouseStatusBar  = new TextField(10);
 private TextField imageStatusBar  = new TextField(20);
 private TextField redValue        = new TextField(3);
 private TextField greenValue      = new TextField(3);
 private TextField blueValue       = new TextField(3);

 /* Labels for operation and color fields */
 private Label operationLabel      = new Label("SELECTED TOOL:");
 private Label colorLabel          = new Label("SELECTED COLOR:");
 private Label cursorLabel         = new Label("POINTER:");
 private Label imageLabel         = new Label("ADD IMAGE:URL");

 /* Sub panels of the main applet */
 private Panel controlPanel1        = new Panel(new GridLayout(16,1,0,0));//colors
 private Panel controlPanel2        = new Panel(new GridLayout(17,1,0,0));//tools
 private Panel controlPanel3        = new Panel(new GridLayout(1,0,0,0));//u-def
 private Panel udefcolPanel         = new Panel(new GridLayout(1,6,0,0));
 private Panel drawPanel            = new Panel();//draw
 private Panel statusPanel          = new Panel();//status
 
// private Panel new1 				= new Panel(); 
 private Graphics g1;
 public void init()
 {
	         
        setLayout(new BorderLayout());

    /* setup color buttons */
    controlPanel2.add(imageButton);
    controlPanel1.add(blackButton);
    controlPanel1.add(blueButton);
    controlPanel1.add(redButton);
    controlPanel1.add(greenButton);
    controlPanel1.add(dgreenButton);
    controlPanel1.add(purpleButton);
    controlPanel1.add(orangeButton);
    controlPanel1.add(pinkButton);
    controlPanel1.add(grayButton);
    controlPanel1.add(yellowButton);
    controlPanel1.add(cyanButton);
    controlPanel1.add(brownButton);
    controlPanel1.add(violetButton);
    controlPanel1.add(userDefButton);
    
	/* set up the background of the color buttons */
    blueButton.setBackground(Color.blue);
    redButton.setBackground(Color.red);
    greenButton.setBackground(Color.green);
    dgreenButton.setBackground(Dgreen);
    purpleButton.setBackground(Color.magenta);
    orangeButton.setBackground(Color.orange);
    pinkButton.setBackground(Color.pink);
    grayButton.setBackground(Color.gray);
    yellowButton.setBackground(Color.yellow);
    cyanButton.setBackground(Color.cyan);
    brownButton.setBackground(Brown);
    violetButton.setBackground(violet);
    userDefButton.setBackground(userDefinedColor);

    /* setup operation buttons */
    controlPanel2.add(penButton);
    controlPanel2.add(lineButton);
    controlPanel2.add(eraserButton);
    controlPanel2.add(clearButton);
    controlPanel2.add(rectButton);
    controlPanel2.add(crvButton);
	controlPanel2.add(sqrButton);
    controlPanel2.add(ovalButton);
    controlPanel2.add(cirButton);
    controlPanel2.add(fillRectButton);
    controlPanel2.add(fsqrButton);
    controlPanel2.add(fillOvalButton);
    controlPanel2.add(fcirButton);
    controlPanel2.add(polygonButton);
    controlPanel2.add(bgButton);   
    controlPanel2.add(brshButton);
    controlPanel2.add(saveButton);
    
	/* set up user defined colors */	
    controlPanel3.add(udefcolPanel);

    /* Add user-defined RGB buttons to panel */
    udefcolPanel.add(redValue);
    udefcolPanel.add(redSlider);

    udefcolPanel.add(greenValue);
    udefcolPanel.add(greenSlider);

    udefcolPanel.add(blueValue);
    udefcolPanel.add(blueSlider);


    /* Add label and color text field */
    statusPanel.add(colorLabel);
    statusPanel.add(colorStatusBar);

    statusPanel.add(imageLabel);
    statusPanel.add(imageStatusBar);

    /* Add label and operation text field */
    statusPanel.add(operationLabel);
    statusPanel.add(opStatusBar);

    /* Add label and cursor text field */
    statusPanel.add(cursorLabel);
    statusPanel.add(mouseStatusBar);

    /* Set not editable */
    colorStatusBar.setEditable(false);
    opStatusBar.setEditable(false);
    mouseStatusBar.setEditable(false);
    imageStatusBar.setEditable(true);

    statusPanel.setBackground(statusBarColor);
    //controlPanel1.setBackground(Color.white);
    //controlPanel2.setBackground(Color.white);
    //drawPanel.setBackground(Color.white);

	/* set up for border layout */
	add(statusPanel, "North");
    add(controlPanel1, "East");
    add(controlPanel2, "West");
    add(controlPanel3, "South");
    add(drawPanel, "Center");

    /* Setup action listener */
    penButton.addActionListener(this);
    lineButton.addActionListener(this);
    eraserButton.addActionListener(this);
    clearButton.addActionListener(this);
    rectButton.addActionListener(this);
    sqrButton.addActionListener(this);
    ovalButton.addActionListener(this);
    cirButton.addActionListener(this);
    fillRectButton.addActionListener(this);
    fsqrButton.addActionListener(this);
    fillOvalButton.addActionListener(this);
    fcirButton.addActionListener(this);
    polygonButton.addActionListener(this);
    bgButton.addActionListener(this);
    crvButton.addActionListener(this);
    brshButton.addActionListener(this);
    saveButton.addActionListener(this);

    blackButton.addActionListener(this);
    blueButton.addActionListener(this);
    imageButton.addActionListener(this);
    redButton.addActionListener(this);
    greenButton.addActionListener(this);
    dgreenButton.addActionListener(this);
    purpleButton.addActionListener(this);
    orangeButton.addActionListener(this);
    pinkButton.addActionListener(this);
    grayButton.addActionListener(this);
    yellowButton.addActionListener(this);
    cyanButton.addActionListener(this);
    brownButton.addActionListener(this);
    violetButton.addActionListener(this);
    userDefButton.addActionListener(this);

    redSlider.addAdjustmentListener(this);
    blueSlider.addAdjustmentListener(this);
    greenSlider.addAdjustmentListener(this);

    /* Adding component listeners to main panel (applet) */
    drawPanel.addMouseMotionListener(this);
    drawPanel.addMouseListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);

    updateRGBValues();

    opStatusBar.setText("Pen");
    colorStatusBar.setText("Black");
    imageStatusBar.setText("");
    }
 

 
 public void actionPerformed(ActionEvent e)
 {
    /* Determine what action has occured */
    /* Set the relative values           */
	 if(e.getSource()==saveButton)//Toolkit.getDefaultToolkit().getScreenSize()
		 {
			
		 	try{
		 		BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle(71,75,1242,606));
		 	
		 	File f=new File("D:/image1.jpg");
		 	showStatus("image saved successfully...");
		 	ImageIO.write(screencapture, "jpg", f);
		 }
		 catch(Exception e1)
		 {
		 System.out.println("error" + e1);	
		 }}
	   	if(e.getActionCommand() == "Load")
			{
	   			Graphics g = drawPanel.getGraphics();
	   			msg = imageStatusBar.getText();
	   			img = getImage(getDocumentBase(),msg);
	   			for(int i=0;i<3;i++)
	   				g.drawImage(img, 0, 0, null);
	   			showStatus("Image is being loaded...");
	   		}	 	
	 if (e.getSource()==imageButton){
	 
		msg=imageStatusBar.getText(); 
		repaint();
	 }
	 
    if (e.getSource()== penButton)
       opStatus = PEN_OP;
    if (e.getSource() == lineButton)
       opStatus = LINE_OP;
    if (e.getSource() == eraserButton)
       opStatus = ERASER_OP;
    if (e.getSource()== clearButton)
       opStatus = CLEAR_OP;
    if (e.getSource() == rectButton)
       opStatus = RECT_OP;
    if (e.getSource() == sqrButton)
       opStatus = SQR_OP;
    if (e.getSource() == ovalButton)
       opStatus = OVAL_OP;
    if (e.getSource() == cirButton)
       opStatus = CIR_OP;
    if (e.getSource() == fillRectButton)
       opStatus = FRECT_OP;
    if (e.getSource() == fsqrButton)
       opStatus = FSQR_OP;
    if (e.getSource() == fillOvalButton)
       opStatus = FOVAL_OP;
    if (e.getSource() == fcirButton)
       opStatus = FCIR_OP;
    if (e.getSource() == polygonButton)
       opStatus = POLY_OP;
	if (e.getSource() == bgButton)
       opStatus = BG_OP;   
	if (e.getSource() == brshButton)
	    opStatus = BRSH_OP;   
	if (e.getSource() == crvButton)
	    opStatus = CRV_OP;   
    
	if (e.getActionCommand() == "Black")
       colorStatus = 1;
    if (e.getActionCommand() == "Blue")
       colorStatus = 2;
    if (e.getActionCommand() == "Green")
       colorStatus = 3;
    if (e.getActionCommand() == "Red")
       colorStatus = 4;
    if (e.getActionCommand() == "Purple")
       colorStatus = 5;
    if (e.getActionCommand() == "Orange")
       colorStatus = 6;
    if (e.getActionCommand() == "Pink")
       colorStatus = 7;
    if (e.getActionCommand() == "Gray")
       colorStatus = 8;
    if (e.getActionCommand() == "Yellow")
       colorStatus = 9;
    if (e.getActionCommand() == "UDef")
       colorStatus = 10;
    if (e.getActionCommand() == "Cyan")
        colorStatus = 11;
    if (e.getActionCommand() == "Brown")
        colorStatus = 12;
    if (e.getActionCommand() == "Dgreen")
        colorStatus = 13;
    if (e.getActionCommand() == "Violet")
        colorStatus = 14;

	   polygonFlag = true;
 
    /* Update Operations status bar, with current tool */
    switch (opStatus)
    {
       case PEN_OP   : opStatusBar.setText("Pen");
                       break;
       case LINE_OP  : opStatusBar.setText("Line");
                       break;
       case ERASER_OP: opStatusBar.setText("Eraser");
                       break;
       case CLEAR_OP : clearPanel(drawPanel);
                       break;
       case RECT_OP  : opStatusBar.setText("Rectangle");
                       break;
       case SQR_OP   : opStatusBar.setText("Square");
                       break;
       case OVAL_OP  : opStatusBar.setText("Oval");
                       break;
       case CIR_OP   : opStatusBar.setText("Circle");
                       break;
       case FRECT_OP : opStatusBar.setText("Fill-Rectangle");
                       break;
       case FSQR_OP  : opStatusBar.setText("Fill-Square");
                       break;
       case FOVAL_OP : opStatusBar.setText("Fill-Oval");
                       break;
       case FCIR_OP  : opStatusBar.setText("Fill-Circle");
                       break;
       case POLY_OP  : opStatusBar.setText("Polygon");
                       break;
       case BG_OP    : opStatusBar.setText("Back Ground Color Change");
                       break;
       case BRSH_OP  : opStatusBar.setText("Brush");
       				   break;
       case CRV_OP  : opStatusBar.setText("Curve");
		   				break;
					   
    }

    /* Update Color status bar, with current color */
    switch (colorStatus)
    {
       case  1: colorStatusBar.setText("Black");
				 break;
       case  2: colorStatusBar.setText("Blue");
                 break;
       case  3: colorStatusBar.setText("Green");
                 break;
       case  4: colorStatusBar.setText("Red");
                 break;
       case  5: colorStatusBar.setText("Purple");
                 break;
       case  6: colorStatusBar.setText("Orange");
                 break;
       case  7: colorStatusBar.setText("Pink");
                 break;
       case  8: colorStatusBar.setText("Gray");
                 break;
       case  9: colorStatusBar.setText("Yellow");
                 break;
       case 10: colorStatusBar.setText("User Defined Color");
                 break;
       case 11: colorStatusBar.setText("Cyan");
       			break;
       case 12: colorStatusBar.setText("Brown");
				break;
       case 13: colorStatusBar.setText("Dgreen");
				break;
       case 14: colorStatusBar.setText("Violet");
				break;

    }

    /*
      Set main color, to equivelent colorStatus value
    */
    setMainColor();
    updateRGBValues();
 }
 

 public void adjustmentValueChanged(AdjustmentEvent e)
 {
    updateRGBValues();
 }

 public void clearPanel(Panel p) // clear panel
 {
    opStatusBar.setText("Clear");
    Graphics g = p.getGraphics();
    g.setColor(p.getBackground());
    g.fillRect(0,0,p.getBounds().width,p.getBounds().height);
  }

 public void crvOperation(MouseEvent e) //draws curve
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (crvFlag)
    {
       setGraphicalDefaults(e);
       crvFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawArc(drawX,drawY,OrWidth,OrHeight,mousex,mousey);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawArc(drawX,drawY,OrWidth,OrHeight,mousex,mousey);
    }
 }

 public void penOperation(MouseEvent e) // pen tool
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (penFlag)
    {
       setGraphicalDefaults(e);
       penFlag = false;
       g.drawLine(premousex,premousey,mousex,mousey);
    }

    if (mouseHasMoved(e))
    {
       mousex = e.getX();
       mousey = e.getY();

       g.drawLine(premousex,premousey,mousex,mousey);

       premousex = mousex;
       premousey = mousey;
    }
 }

 public void lineOperation(MouseEvent e) //line tool
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (lineFlag)
    {
       setGraphicalDefaults(e);
       g.setXORMode(xorColor);
       g.drawLine(Orx,Ory,mousex,mousey);
       lineFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawLine(Orx,Ory,mousex,mousey);

       mousex = e.getX();
       mousey = e.getY();

       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }

 public void rectOperation(MouseEvent e)
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (rectFlag)
    {
       setGraphicalDefaults(e);
       rectFlag = false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(drawPanel.getBackground());
       g.drawRect(drawX,drawY,OrWidth,OrHeight);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawRect(drawX,drawY,OrWidth,OrHeight);
    }
 }

 public void sqrOperation(MouseEvent e) //draws square
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (sqrFlag)
    {
       setGraphicalDefaults(e);
       sqrFlag = false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(drawPanel.getBackground());
       g.drawRect(drawX,drawY,OrWidth,OrWidth);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawRect(drawX,drawY,OrWidth,OrWidth);
    }
 }

 public void ovalOperation(MouseEvent e) //draws oval
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (ovalFlag)
    {
       setGraphicalDefaults(e);
       ovalFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrHeight);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawOval(drawX,drawY,OrWidth,OrHeight);
    }
 }

 public void cirOperation(MouseEvent e) //draws circle
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (cirFlag)
    {
       setGraphicalDefaults(e);
       cirFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrWidth);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawOval(drawX,drawY,OrWidth,OrWidth);
    }
 }

 public void frectOperation(MouseEvent e) //draws filled rect
 {

    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (frectFlag)
    {
       setGraphicalDefaults(e);
       frectFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawRect(drawX,drawY,OrWidth-1,OrHeight-1);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawRect(drawX,drawY,OrWidth-1,OrHeight-1);
    }
 }

 public void fsqrOperation(MouseEvent e) //draws filled sqr
 {

    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (fsqrFlag)
    {
       setGraphicalDefaults(e);
       fsqrFlag=false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawRect(drawX,drawY,OrWidth-1,OrWidth-1);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawRect(drawX,drawY,OrWidth-1,OrWidth-1);
    }
 }
 
 public void fovalOperation(MouseEvent e) //draws filled oval
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (fovalFlag)
    {
       setGraphicalDefaults(e);
       fovalFlag = false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrHeight);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawOval(drawX,drawY,OrWidth,OrHeight);
    }
 }

 public void fcirOperation(MouseEvent e) //draws filled circle
 {
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);

    if (fcirFlag)
    {
       setGraphicalDefaults(e);
       fcirFlag = false;
    }

    if (mouseHasMoved(e))
    {
       g.setXORMode(xorColor);
       g.drawOval(drawX,drawY,OrWidth,OrWidth);

       mousex = e.getX();
       mousey = e.getY();

       setActualBoundry();

       g.drawOval(drawX,drawY,OrWidth,OrWidth);
    }
 }

 public void eraserOperation(MouseEvent e) //eraser 
 {
    Graphics g  = drawPanel.getGraphics();

    if (eraserFlag)
    {
       setGraphicalDefaults(e);
       eraserFlag = false;
       g.setColor(mainColor.white);
       g.fillRect(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(Color.black);
       g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       premousex = mousex;
       premousey = mousey;
    }

    if (mouseHasMoved(e))
    {
       g.setColor(mainColor.white);
       g.drawRect(premousex-eraserLength, premousey-eraserLength,eraserLength*2,eraserLength*2);

       mousex  = e.getX();
       mousey  = e.getY();

       g.setColor(mainColor.white);
       g.fillRect(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(Color.black);
       g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       premousex = mousex;
       premousey = mousey;
    }
 }

 public void brshOperation(MouseEvent e) //brsh
 {
    Graphics g  = drawPanel.getGraphics();

    if (brshFlag)
    {
       setGraphicalDefaults(e);
       brshFlag = false;
       g.setColor(mainColor);
       g.fillOval(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(mainColor);
       g.drawOval(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       premousex = mousex;
       premousey = mousey;
    }

    if (mouseHasMoved(e))
    {
       g.setColor(mainColor);
       g.drawOval(premousex-eraserLength, premousey-eraserLength,eraserLength*2,eraserLength*2);

       mousex  = e.getX();
       mousey  = e.getY();

       g.setColor(mainColor);
       g.fillOval(mousex-eraserLength, mousey-eraserLength,eraserLength*2,eraserLength*2);
       g.setColor(mainColor);
       g.drawOval(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
       premousex = mousex;
       premousey = mousey;
    }
 }

 
 public void polygonOperation(MouseEvent e) //polygon
 {
    if (polygonFlag)
    {
       premousex = e.getX();
       premousey = e.getY();
       polygonFlag = false;
    }
    else
    {
       mousex = e.getX();
       mousey = e.getY();
       Graphics g = drawPanel.getGraphics();
       g.setColor(mainColor);
       g.drawLine(premousex,premousey,mousex,mousey);
       premousex = mousex;
       premousey = mousey;
    }
 }
 
 public void bgOperation(MouseEvent e) //sets a background color
 {
	Graphics g = drawPanel.getGraphics();
	g.setColor(mainColor);
    if (bgFlag)
    {
       setGraphicalDefaults(e);
       bgFlag = false;
	    drawPanel.setBackground(mainColor);
		controlPanel1.setBackground(mainColor);
		controlPanel2.setBackground(mainColor);
   }
 }

 public boolean mouseHasMoved(MouseEvent e)
 {
    return (mousex != e.getX() || mousey != e.getY());
 }

 public void setActualBoundry()
 {
       if (mousex < Orx || mousey < Ory)
       {
          if (mousex < Orx)
          {
             OrWidth = Orx - mousex;
             drawX   = Orx - OrWidth;
          }
          else
          {
             drawX    = Orx;
             OrWidth  = mousex - Orx;
          }
          if (mousey < Ory)
          {
             OrHeight = Ory - mousey;
             drawY    = Ory - OrHeight;
          }
          else
          {
             drawY    = Ory;
             OrHeight = mousey - Ory;
          }
       }
       else
       {
          drawX    = Orx;
          drawY    = Ory;
          OrWidth  = mousex - Orx;
          OrHeight = mousey - Ory;
       }
 }

 public void setGraphicalDefaults(MouseEvent e)
 {
    mousex   = e.getX();
    mousey   = e.getY();
    premousex    = e.getX();
    premousey    = e.getY();
    Orx      = e.getX();
    Ory      = e.getY();
    drawX    = e.getX();
    drawY    = e.getY();
    OrWidth  = 0;
    OrHeight = 0;
 }

 public void mouseDragged(MouseEvent e)
 {
    updateMouseCoordinates(e);

    switch (opStatus)
    {
       case PEN_OP   : penOperation(e);
                       break;
       case LINE_OP  : lineOperation(e);
                       break;
       case RECT_OP  : rectOperation(e);
                       break;
       case SQR_OP   : sqrOperation(e);
                       break;
       case OVAL_OP  : ovalOperation(e);
                       break;
       case CIR_OP   : cirOperation(e);
                       break;
       case FRECT_OP : frectOperation(e);
                       break;
       case FSQR_OP  : fsqrOperation(e);
                       break;
       case FOVAL_OP : fovalOperation(e);
                       break;
       case FCIR_OP  : fcirOperation(e);
                       break;
       case ERASER_OP: eraserOperation(e);
                       break;
       case BG_OP    : bgOperation(e);
                       break;
       case BRSH_OP  : brshOperation(e);
       				   break;
       case CRV_OP  : crvOperation(e);
		   break;

    }
 }

 public void mouseReleased(MouseEvent e)
 {
	updateMouseCoordinates(e);

    switch (opStatus)
    {
       case PEN_OP    : releasedPen();
                        break;
       case LINE_OP   : releasedLine();
                        break;
       case RECT_OP   : releasedRect();
                        break;
       case SQR_OP    : releasedSqr();
                        break;
       case OVAL_OP   : releasedOval();
                        break;
       case CIR_OP    : releasedCir();
                        break;
       case FRECT_OP  : releasedFRect();
                        break;
       case FSQR_OP   : releasedFsqr();
                        break;
       case FOVAL_OP  : releasedFOval();
                        break;
       case FCIR_OP   : releasedFcir();
                        break;
       case ERASER_OP : releasedEraser();
                        break;
       case BG_OP     : releasedBg();
                        break;
       case BRSH_OP   : releasedBrsh();
       					break;
                        
    }
 }

 public void mouseEntered(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }

 public void setMainColor()
 {
    switch (colorStatus)
    {
       case 1 : mainColor = Color.black;
                break;
       case 2:  mainColor = Color.blue;
                break;
       case 3:  mainColor = Color.green;
                break;
       case 4:  mainColor = Color.red;
                break;
       case 5:  mainColor = Color.magenta;
                break;
       case 6:  mainColor = Color.orange;
                break;
       case 7:  mainColor = Color.pink;
                break;
       case 8:  mainColor = Color.gray;
                break;
       case 9:  mainColor = Color.yellow;
                break;
       case 11: mainColor = Color.cyan;
       			break;
       case 10: mainColor = userDefinedColor;
                break;
       case 12: mainColor = Brown;
       			break;
       case 13: mainColor = Dgreen;
				break;
       case 14: mainColor = violet;
				break;
    }
 }

 public void updateMouseCoordinates(MouseEvent e)
 {
    String xCoor ="";
    String yCoor ="";

    if (e.getX() < 0) xCoor = "0";
    else
    {
       xCoor = String.valueOf(e.getX());
    }

    if (e.getY() < 0) xCoor = "0";
    else
    {
       yCoor = String.valueOf(e.getY());
    }
    mouseStatusBar.setText("x:" + xCoor + "   y:" + yCoor);
 }

 public void updateRGBValues()
 {
    udefRedValue = redSlider.getValue();
    udefGreenValue = greenSlider.getValue();
    udefBlueValue = blueSlider.getValue();
    if (udefRedValue > 255)
       udefRedValue = 255;

    if (udefRedValue < 0 )
       udefRedValue =0;

    if (udefGreenValue > 255)
       udefGreenValue = 255;

    if (udefGreenValue < 0 )
       udefGreenValue =0;

    if (udefBlueValue > 255)
       udefBlueValue = 255;

    if (udefBlueValue < 0 )
       udefBlueValue =0;

    redValue.setText(String.valueOf(udefRedValue));
    greenValue.setText(String.valueOf(udefGreenValue));
    blueValue.setText(String.valueOf(udefBlueValue));

    userDefinedColor = new Color(udefRedValue,udefGreenValue,udefBlueValue);
    userDefButton.setBackground(userDefinedColor);
 }

 public void mouseClicked(MouseEvent e)
 {
    updateMouseCoordinates(e);
    switch (opStatus)
    {
       case POLY_OP : polygonOperation(e);
                 break;
    }
}

 public void mouseExited(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }

 public void mouseMoved(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }

 public void mousePressed(MouseEvent e)
 {
    updateMouseCoordinates(e);
 }

 public void releasedPen()
 {
    penFlag = true;
 }

 public void releasedLine()
 {
    if ((Math.abs(Orx - mousex) + Math.abs(Ory - mousey)) != 0)
    {
       showStatus("Line has been released....");
       lineFlag = true;
       Graphics g  = drawPanel.getGraphics();
       g.setColor(mainColor);
       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }

 public void releasedEraser()
 {
    eraserFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor.white);
    g.drawRect(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
 }

 public void releasedBrsh()
 {
    brshFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawOval(mousex-eraserLength,mousey-eraserLength,eraserLength*2,eraserLength*2);
 }

 
 public void releasedRect()
 {
    rectFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawRect(drawX,drawY,OrWidth,OrHeight);
 }

 public void releasedSqr()
 {
    sqrFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawRect(drawX,drawY,OrWidth,OrWidth);
 }

 public void releasedOval()
 {
    ovalFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawOval(drawX,drawY,OrWidth,OrHeight);
 }

 public void releasedCir()
 {
    cirFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.drawOval(drawX,drawY,OrWidth,OrWidth);
 }

 public void releasedFRect()
 {
    frectFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillRect(drawX,drawY,OrWidth,OrHeight);
 }

 public void releasedFsqr()
 {
    fsqrFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillRect(drawX,drawY,OrWidth,OrWidth);
 }

 public void releasedFOval()
 {
    fovalFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillOval(drawX - 1,drawY - 1,OrWidth + 2,OrHeight + 2);
 }

 public void releasedFcir()
 {
    fcirFlag = true;
    Graphics g  = drawPanel.getGraphics();
    g.setColor(mainColor);
    g.fillOval(drawX - 1,drawY - 1,OrWidth + 2,OrWidth + 2);
 }
 public void releasedBg()
 {
    bgFlag = true;
 }
 

 /** Update - Method, implements double buffering */ 

	 
	 /*Point p1 = new Point(prevX,prevY);
	 Point p2 = new Point(x2, y2);
	 g.DrawLine(p, p1, p2);
	 LineStoreGraph linestrgrph = new LineStoreGraph(p, x1, y1, x2, y2);
	 sequenceStore.Add(linestrgrph);
	 
	 private void panel1_Paint(object sender, PaintEventArgs e)
	 {
	     Graphics g = Graphics.FromHwnd(panel1.Handle);
	     foreach (LineStoreGraph lineSG in sequenceStore)
	     {
	         g.DrawLine(lineSG.p, lineSG.x1, lineSG.y1, lineSG.x2, lineSG.y2);
	     }
	 }*/
	 
	 
	 /*private void saveToolStripMenuItem_Click(object sender, EventArgs e)
	 {
	     saveFileDialog1.Filter = "All supported image types|" + 
	       "*.bmp;*.jpg;*.jpeg;*.gif|Bitmaps (*.bmp)|*.bmp|JPEG Images" + 
	       " (*.jpg,*.jpeg)|*.jpg;*.jpeg|Gif Images (*.gif)|*.gif";
	     saveFileDialog1.FilterIndex = 1;
	     Bitmap mybitmap = new Bitmap(panel1.Width, panel1.Height);
	     for (int i = 0; i < mybitmap.Width; i++)
	     {
	         for (int j = 0; j < mybitmap.Height; j++)
	         {
	             mybitmap.SetPixel(i, j, Color.White);
	         }
	     }
	     
	     /* // initialize buffer 
 if (dbImage == null) 
 {
 dbImage = createImage (this.getSize().width, this.getSize().height); 
 dbg = dbImage.getGraphics (); 
 } 

 // clear screen in background 
 dbg.setColor (getBackground ()); 
 dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 

 // draw elements in background 
 dbg.setColor (getForeground()); 
 paint(dbg); 

 // draw image on the screen 
 g.drawImage (dbImage, 0, 0, this);*/ 
  
 //public void paint(Graphics g) {
   //  update(g); // call to the update()method.
//}
// backbuffer=createImage(1250,606 );
// backg=backbuffer.getGraphics();


}
  
