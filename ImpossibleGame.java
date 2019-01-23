import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class ImpossibleGame extends Applet implements KeyListener
{
    int xC = 100, yC = 100, dy = 0, dx = 0;
    boolean startUp = true, rules = false, tutorial = false, level1 = false, tTelePad = false, TelePad1 = false, TeleToggle1 = false, TeleToggle2 = false, ColorReverse = false, EGN = false;
    Color SkyBlue = new Color (135, 206, 250);
    Color FinishLine = new Color (108, 108, 108);
    Color TeleportColor = new Color (10, 250, 10);
    Color fTeleportColor = new Color (5, 5, 245);


    public void init ()
    {
	addKeyListener (this);
	requestFocus ();
    }


    public void paintBackground (Graphics g)
    {
	g.setColor (SkyBlue);
	g.fillRect (0, 0, 1400, 1400);

	if (startUp)
	{
	    startUpDisplay (g);
	}
	if (rules)
	{
	    rulesDisplay (g);
	}
	if (tutorial)
	{
	    tutorialMap (g);
	}
	if (level1)
	{
	    Map_Level1 (g);
	}
	if (EGN)
	{
	    EndOfGame (g);
	}
    }


    public void paint (Graphics g)
    {
	paintBackground (g);

	//CHARACTER CONTROL BALL
	g.setColor (Color.black);
	g.fillOval (xC, yC, 25, 25);
	//System.out.println ("X Cord:" + xC + " Y Cord:" + yC);
    }


    public void keyPressed (KeyEvent e)
    {
	int key = e.getKeyCode ();
	//System.out.println (key);

	//Rules Display
	if (startUp)
	{
	    if (key == 27)
		rules = true;
	}

	//Tutorial Display
	if (key == 84)
	{
	    startUp = false;
	    tutorial = true;
	    xC = 63;
	    yC = 63;
	}
	//QUICK DEVELOPER ESCAPE
	if (key == 27)
	{
	    startUp = true;
	    tutorial = false;
	    level1 = false;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	    ColorReverse = false;
	    EGN = false;
	    xC = 100;
	    yC = 100;
	}
	//Level 1 Map Display
	if (key == 80)
	{
	    startUp = false;
	    level1 = true;
	    //xC = 655;
	    // yC = 60;
	    xC = 653;
	    yC = 60;
	}
	//Arrow Key Movement
	if (key == 38)
	    dy = -5;

	if (key == 40)
	    dy = 5;

	if (key == 37)
	    dx = -5;

	if (key == 39)
	    dx = 5;

	xC += dx;
	yC += dy;

	//Window Boundaries
	if (xC < 0)
	    xC = 0;
	if (yC < 0)
	    yC = 0;
	if (xC > 1341)
	    xC = 1341;
	if (yC > 697)
	    yC = 697;

	System.out.println (xC + ", " + yC);
	repaint ();
    }


    public void keyReleased (KeyEvent e)
    {
	int key = e.getKeyCode ();
	repaint ();

	if (key == 38)
	    dy = 0;

	if (key == 40)
	    dy = 0;

	if (key == 37)
	    dx = 0;

	if (key == 39)
	    dx = 0;
	if (startUp)
	{
	    if (key == 27)
		rules = false;
	}
    }



    public void keyTyped (KeyEvent e)
    {
	//broken
    }


    public void startUpDisplay (Graphics g)
    {
	//Displaying Title
	g.setColor (Color.black);
	Font RobotoTitle = new Font ("Roboto", Font.BOLD, 45);
	g.setFont (RobotoTitle);
	g.drawString ("Impossible Game", 500, 45);

	//Displaying Prompt Message
	Font RobotoText = new Font ("Roboto", Font.BOLD, 25);
	g.setFont (RobotoText);
	g.setColor (Color.white);
	g.drawString ("Hold Esc for Rules", 550, 300);
	g.drawString ("Press T to play Tutorial", 550, 400);
	g.drawString ("Press P to play the game", 550, 500);
    }


    public static void rulesDisplay (Graphics g)
    { //Displaying Rules
	g.setColor (Color.black);
	g.fillRect (485, 355, 410, 140);
	g.setColor (Color.white);
	g.fillRect (490, 360, 400, 130);
	g.setColor (Color.black);
	Font RobotoRules = new Font ("Roboto", Font.BOLD, 15);
	g.setFont (RobotoRules);
	g.drawString ("1.Use arrow keys to move", 550, 400);
	g.drawString ("2.Head to the finish box without", 550, 420);
	g.drawString ("hitting obstacles (Walls are permitted)", 565, 440);
	g.drawString ("3.Green is a teleport pad", 550, 460);

    }


    public void tutorialMap (Graphics g)
    {
	//Tutorial Map Design
	g.setColor (Color.white); //Pathing
	g.fillRect (50, 50, 1000, 622);

	g.setColor (SkyBlue); //Inner Blue Rectangle
	g.fillRect (100, 0, 900, 597);

	g.setColor (FinishLine); //Finish Line
	g.fillRect (600, 50, 200, 100);

	//Finish Line Indicator
	g.setColor (Color.white);
	Font FinishS = new Font ("Roboto", Font.BOLD, 25);
	g.setFont (FinishS);
	g.drawString ("Finish Line", 635, 100);


	//Boundary Display
	g.setColor (Color.red);
	g.fillRect (100, 597, 25, 45); //Object 1
	g.fillRect (172, 628, 25, 45); //Object 2
	g.fillRect (242, 597, 25, 45); //Object 3
	g.fillRect (312, 628, 25, 45); //Object 4
	g.fillRect (500, 597, 175, 20); //Tunnel Top
	g.fillRect (500, 653, 210, 20); //Tunnel Bottom
	g.fillRect (710, 628, 100, 45); //Tunnel Exit

	//Screen Boundaries
	while (xC < 50)
	    xC = 50;
	if (yC < 50)
	    yC = 50;
	if (xC > 1025)
	    xC = 1025;
	if (yC > 647)
	    yC = 647;

	if (xC > 75 && xC < 90 && yC < 596) //Physical Boundary
	    xC = 75;

	if (yC < 596 && yC > 150 && xC > 90 && xC < 980) //Physical Boundary
	    yC = 596;

	if (xC > 980 && xC < 999 && yC < 596) //Physical Boundary
	    xC = 1000;

	if (yC > 572 && yC < 642 && xC > 75 && xC < 123) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}

	if (yC > 628 && yC < 672 && xC > 148 && xC < 196) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}

	if (yC > 572 && yC < 642 && xC > 217 && xC < 265) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}

	if (yC > 628 && yC < 672 && xC > 289 && xC < 337) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}

	if (yC > 572 && yC < 618 && xC > 475 && xC < 675) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}

	if (yC > 630 && yC < 672 && xC > 475 && xC < 710) //Physical Boundary
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}
	//Tunnel Exit
	if (yC > 605 && yC < 672 && xC > 710 && xC < 810)
	{
	    xC = 62;
	    yC = 63;
	    tTelePad = false;
	}
	//Player Clue
	if (!tTelePad)
	{
	    g.setColor (Color.orange);
	    g.fillRect (1000, 50, 50, 25);
	}
	//Teleport Pad Toggle
	if (xC >= 1000 && yC < 71)
	{
	    tTelePad = true;
	}
	//Teleport Pad Display and Teleport
	if (tTelePad)
	{
	    g.setColor (TeleportColor);
	    g.fillRect (550, 50, 50, 50); //Teleport Pad Finish
	    g.fillRect (50, 50, 50, 50); // Teleport Pad Start

	    //Teleporting the Ball
	    if (xC < 82 && yC < 95)
	    {
		xC = 553;
		yC = 53;
	    }
	    //Teleport Finish Boundary
	    if (xC < 550 && yC < 100)
		xC = 550;
	    if (yC < 50 && xC > 550 && xC < 600)
		yC = 50;
	    if (xC > 549 && xC < 600 && yC > 75 && yC < 100)
		yC = 75;
	    //Finish Boundary
	    if (xC > 602 && xC < 800 && yC > 49 && yC < 130)
	    {
		tutorial = false;
		tTelePad = false;
		startUp = true;
	    }
	}
    }


    public void Map_Level1 (Graphics g)
    {
	if (!ColorReverse)
	{
	    g.setColor (Color.black);
	    g.fillRect (50, 50, 1266, 622);
	    g.setColor (Color.white);
	    g.fillRect (60, 60, 618, 296); //Wallblock 1
	    g.fillRect (60, 366, 618, 296); //Wallblock 2
	    g.fillRect (688, 60, 618, 296); //Wallblock 3
	    g.fillRect (688, 366, 618, 296); //Wallblock 4
	    g.setColor (fTeleportColor);
	    g.fillRect (628, 512, 25, 25);
	    g.fillRect (1281, 60, 25, 25);
	}
	if (ColorReverse)
	{
	    g.setColor (Color.white);
	    g.fillRect (50, 50, 1266, 622);
	    g.setColor (Color.black);
	    g.fillRect (60, 60, 618, 296); //Wallblock 1
	    g.fillRect (60, 366, 618, 296); //Wallblock 2
	    g.fillRect (688, 60, 618, 296); //Wallblock 3
	    g.fillRect (688, 366, 618, 296); //Wallblock 4
	}
	//Wall Boundaries
	if (xC < 60)
	    xC = 60;
	if (yC < 60)
	    yC = 60;
	if (xC > 1281)
	    xC = 1281;
	if (yC > 637)
	    yC = 637;

	//Inner Wall Boundaries
	if (xC > 653 && xC < 678) //Physical Boundary
	    xC = 653;
	if (xC > 679 && xC < 688) //Physical Boundary
	    xC = 688;
	if (yC > 331 && yC < 355) //Physical Boundary
	    yC = 331;
	if (yC < 366 && yC > 355) //Physical Boundary
	    yC = 366;

	//WallBlock 1 Design

	//WallBlock 1 Reset Boundaries Designs
	g.setColor (Color.red);
	g.fillRect (90, 90, 588, 30);
	g.fillRect (90, 120, 30, 206);
	g.fillRect (90, 296, 558, 30);
	g.fillRect (618, 160, 30, 146);
	g.fillRect (150, 150, 498, 30);
	g.fillRect (150, 180, 30, 86);
	g.fillRect (180, 236, 408, 30);
	g.fillRect (558, 210, 30, 30);
	g.fillRect (498, 180, 30, 25);
	g.fillRect (438, 210, 30, 26);
	g.fillRect (378, 180, 30, 25);
	g.fillRect (318, 210, 30, 26);
	g.fillRect (258, 180, 30, 25);
	g.fillRect (180, 210, 48, 26);

	//Physical Resets
	if (yC > 65 && yC < 110 && xC <= 653 && xC > 70) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC > 65 && xC < 110 && yC > 70 && yC < 321) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC < 326 && yC > 316 && xC > 65 && xC < 648) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC < 648 && xC > 638 && yC > 125 && yC < 326) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC < 120 && yC > 110 && xC <= 653 && xC > 110) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC > 125 && yC < 135 && xC < 648 && xC > 125) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC < 120 && xC > 110 && yC > 110 && yC < 281) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC > 125 && xC < 135 && yC > 125 && yC < 266) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC > 271 && yC < 281 && xC >= 120 && xC <= 593) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC < 266 && yC > 256 && xC > 125 && xC < 588) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;

	}
	if (xC >= 593 && xC <= 603 && yC >= 180 && yC <= 271) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;

	}
	if (xC < 588 && xC > 578 && yC > 185 && yC < 266) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;

	}
	if (yC < 180 && yC > 170 && xC >= 180 && xC <= 593) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC > 211 && yC < 221 && xC >= 228 && xC < 538) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC > 185 && yC <= 215 && xC > 533 && xC < 588) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (yC >= 180 && yC < 205 && xC > 473 && xC < 528) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC > 413 && xC < 468 && yC > 185 && yC <= 211) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC > 353 && xC < 408 && yC >= 180 && yC < 205) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC > 293 && xC < 348 && yC <= 211 && yC > 185) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC < 288 && xC > 233 && yC >= 180 && yC < 205) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC >= 180 && xC < 228 && yC > 185 && yC < 211) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}
	if (xC < 180 && xC > 170 && yC >= 180 && yC < 185) //Physical Boundary
	{
	    xC = 653;
	    yC = 60;
	    TelePad1 = false;
	}

	//Displaying Objective
	if (!TelePad1)
	{
	    g.setColor (Color.orange);
	    g.fillRect (180, 180, 25, 30);
	}
	//Toggle Telepad1
	if (xC > 180 && xC < 205 && yC >= 180 && yC <= 185)
	{
	    TelePad1 = true;
	}

	if (TelePad1)
	{
	    g.setColor (TeleportColor);
	    g.fillRect (653, 60, 25, 30); //Teleport1 Pad Finish
	    g.fillRect (1281, 632, 25, 30); // Teleport1 Pad Start

	    //Teleporting the Ball
	    if (xC == 653 && yC >= 60 && yC <= 65)
	    {
		xC = 1291;
		yC = 632;
		TelePad1 = false;
	    }
	}

	//WallBlock 3 Design
	g.setColor (Color.red);
	g.fillRect (688, 366, 618, 175);
	g.setColor (Color.white);
	g.fillRect (982, 366, 30, 145);
	g.fillRect (922, 396, 30, 145);
	g.fillRect (811, 396, 111, 30);
	g.fillRect (811, 396, 30, 95);
	g.fillRect (688, 461, 123, 30);
	g.fillRect (688, 366, 30, 95);
	g.fillRect (1042, 366, 30, 175);
	g.fillRect (1042, 366, 264, 30);
	g.fillRect (1276, 366, 30, 145);
	g.fillRect (1100, 481, 176, 30);
	g.setColor (Color.black);
	g.drawRect (982, 510, 30, 31);
	if (ColorReverse)
	{
	    g.setColor (Color.black);
	    g.fillRect (982, 366, 30, 145);
	    g.fillRect (922, 396, 30, 145);
	    g.fillRect (811, 396, 111, 30);
	    g.fillRect (811, 396, 30, 95);
	    g.fillRect (688, 461, 123, 30);
	    g.fillRect (688, 366, 30, 95);
	    g.fillRect (1042, 366, 30, 175);
	    g.fillRect (1042, 366, 264, 30);
	    g.fillRect (1276, 366, 30, 145);
	    g.fillRect (1100, 481, 176, 30);
	}

	//Reset Boundaries
	if (!TeleToggle1 || !TeleToggle2)
	{
	    //System.out.println (TeleToggle1 + "   " + TeleToggle2);
	    if (yC < 541 && yC > 531 && xC >= 688 && xC < 922 || yC < 541 && yC > 531 && xC > 927 && xC < 1043 || yC < 541 && yC > 531 && xC > 1047)
	    {
		xC = 1291;
		yC = 632;
		TeleToggle1 = false;
		TeleToggle2 = false;
	    }
	}

	if (xC < 922 && xC > 911 && yC < 541 && yC > 401) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC > 927 && xC < 937 && yC < 541 && yC > 366) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC < 396 && yC > 366 && xC <= 927 && xC > 811) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC < 922 && xC > 816 && yC > 401 && yC < 411) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC < 811 && xC > 801 && yC >= 396 && yC < 461) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC >= 816 && xC < 826 && yC > 401 && yC <= 466) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC < 461 && yC > 451 && xC > 693 && xC < 811) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC > 466 && yC < 476 && xC <= 816 && xC > 681) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC > 693 && xC < 703 && yC < 461 && yC >= 366) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC >= 366 && yC < 541 && xC < 1041 && xC > 1030) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC > 371 && yC < 541 && xC > 1047 && xC < 1056) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC > 371 && yC < 381 && xC > 1046 && xC < 1276) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC < 1276 && xC > 1266 && yC > 371 && yC < 481) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC > 486 && yC < 496 && xC > 1099 && xC <= 1281) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (yC < 481 && yC > 471 && xC > 1099 && xC < 1276) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	if (xC < 1100 && xC > 1090 && yC > 456 && yC <= 486) //Physical Boundary
	{
	    xC = 1291;
	    yC = 632;
	    TeleToggle1 = false;
	    TeleToggle2 = false;
	}
	//Displaying Objective
	if (!TeleToggle1)
	{
	    g.setColor (Color.orange);
	    g.fillRect (1100, 481, 25, 30);
	}
	if (!TeleToggle2)
	{
	    g.setColor (Color.orange);
	    g.fillRect (688, 366, 30, 25);
	}
	//Toggle TeleToggle1
	if (xC <= 1125 && xC >= 1100 && yC > 481 && yC <= 486)
	{
	    TeleToggle1 = true;
	}
	//Toggle TeleToggle2
	if (xC >= 688 && xC <= 693 && yC > 366 && yC < 391)
	{
	    TeleToggle2 = true;
	}

	//Teleport to Final Puzzle
	if (TeleToggle1 && TeleToggle2)
	{
	    //Entrance Opens
	    if (yC < 541 && yC > 531 && xC >= 688 && xC < 922 || xC < 983 && xC > 988 && yC < 541 && yC > 531 || yC < 541 && yC > 531 && xC > 988 && yC < 1042 || yC < 541 && yC > 531 && xC > 927 && xC < 983 || yC < 541 && yC > 531 && xC > 1047)
	    {
		yC = 541;
		//xC = 1291;
		//yC = 632;
	    }
	    //Wall Boundaries
	    if (xC < 982 && xC > 972 && yC < 541 && yC >= 366) //Physical Boundary
	    {
		xC = 1291;
		yC = 632;
	    }
	    if (xC > 988 && xC < 998 && yC < 541 && yC >= 366) //Physical Boundary
	    {
		xC = 1291;
		yC = 632;
	    }

	    g.setColor (Color.white);
	    g.fillRect (982, 510, 31, 32);
	    g.setColor (TeleportColor);
	    g.fillRect (982, 366, 30, 25); //Teleport1 Pad Finish
	    g.fillRect (60, 632, 25, 30); // Teleport1 Pad Start

	    //Teleporting the Ball
	    if (xC >= 983 && xC <= 987 && yC == 366)
	    {
		xC = 60;
		yC = 632;
		TeleToggle1 = false;
		TeleToggle2 = false;
		ColorReverse = true;
	    }
	}

	//WallBlock 2 Design

	//entrance to the end of Game
	if (xC >= 628 && xC <= 653 && yC >= 512 && yC <= 537)
	{
	    xC = 1281;
	    yC = 60;
	    ColorReverse = false;
	}

	//WallBloxk 4 Design
	g.setColor (FinishLine);
	g.fillRect (688, 85, 592, 271);
	if (xC >= 688 && xC < 1281 && yC > 60 && yC <= 331)
	{
	    level1 = false;
	    EGN = true;
	}
    }


    public void EndOfGame (Graphics g)
    {
	Font FinalDisplay = new Font ("Roboto", Font.BOLD, 100);
	g.setColor (Color.pink);
	g.setFont (FinalDisplay);
	g.drawString ("Thanks For Playing", 240, 440);
    }
}

