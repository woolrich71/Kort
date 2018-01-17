/* Decompiled by Mocha from application1.class */

/* Originally compiled from Application1.java */

package test;

import java.awt.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import javax.swing.JFrame;



public class Application1

{

    private Kortspel mKortPanel;



    public Application1()

    {

        try

        {

            KortRam kortRam = new KortRam();

            kortRam.initComponents();

            mKortPanel = new Kortspel();

            kortRam.getContentPane().add(mKortPanel, "Center");

            Dimension dimension1 = kortRam.getSize();

            Dimension dimension2 = Toolkit.getDefaultToolkit().getScreenSize();

            kortRam.setBounds((dimension2.width - dimension1.width) / 2, (dimension2.height - dimension1.height) / 2, dimension1.width, dimension1.height);

            mKortPanel.actionPerformed(new ActionEvent(mKortPanel.nyOmgong, 1001, ""));

            kortRam.setResizable(false);

            kortRam.setVisible(true);

        }

        catch (Exception e)

        {

            e.printStackTrace();

        }

    }



    public static void main(String astring[])

    {

        new Application1();

    }

}