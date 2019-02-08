package demo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Display {

		public Game game;
		private JFrame frame;
		private JPanel Select;
		private Canvas canvas;
		//Use canvas to draw image and then add it to the JFrame
		private String Title;
		private int Width,Height;


		
		public Display(String Title,int Width,int Height,Game g){
			this.Title = Title;
			this.Width = Width;
			this.Height = Height;
			this.game = g;
			CreateDisplay();
		}
		private void CreateDisplay(){
			frame = new JFrame(Title);
			frame.setSize(Width, Height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			
			//Making a new canvas to draw in and set sizes
			canvas = new Canvas();
			canvas.setPreferredSize(new Dimension(Width,Height));
			canvas.setMaximumSize(new Dimension(Width,Height));
			canvas.setMinimumSize(new Dimension(Width,Height));
			GUI();
			frame.add(Select,BorderLayout.EAST);
			frame.add(canvas,BorderLayout.WEST);
			frame.pack(); //keeps whatever you draw inside the frame
		}
		public void GUI()
		{
			Select = new JPanel();
			JButton Auto = new JButton();
	        JButton button = new JButton();
	        JButton button2 = new JButton();
	        JButton Attack = new JButton();
	        JButton Move = new JButton();
	        JButton EndTurn = new JButton();
	        //set label for turn counter
	       Auto.setText("Automate Move");
	       button.setText("Select Territory");
	       button2.setText("Select Target-Territory");
	       Attack.setText("Attack");
	       Move.setText("Move");
	       EndTurn.setText("End Turn");
	       Select.add(Auto);
	       Select.add(button);
	       Select.add(button2);
	       Select.add(Attack);
	       Select.add(Move);
	       Select.add(EndTurn);
	       Select.setSize(50,50);
	       Select.setVisible(true);
	       Select.setLocation(400, 200);
	       BoxLayout bl = new BoxLayout(Select, BoxLayout.Y_AXIS);
	       Select.setLayout(bl);
	       
	        button.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                String SelectedTerr = JOptionPane.showInputDialog(Select,"Enter Number for Selected Territory?", null); 

	                game.setSelectedTerritory(Integer.parseInt(SelectedTerr));
	            }
	        });
            button2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                String TargetSelect = JOptionPane.showInputDialog(Select,"Enter Number for Target Territory?", null); 
	                game.setTargetTerritory(Integer.parseInt(TargetSelect));
	            }
	        });
            Attack.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int blah = JOptionPane.showConfirmDialog(Select, "Are you Sure");
					if (blah == 0)
						game.Attack(true);
					else if (blah == 1)
						game.Attack(false);
					else
						return;
				}
            });          
            Move.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int blah = JOptionPane.showConfirmDialog(Select, "Are you Sure");
					if (blah == 0)
						game.Move(true);
					else if (blah == 1)
						game.Move(false);
					else
						return;
				}
            }); 
            EndTurn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int blah = JOptionPane.showConfirmDialog(Select, "Are you Sure");
					if (blah == 0)
					{
						game.TurnCheck(true);
						game.Updatechecker(true);
					}
					else if (blah == 1)
						game.TurnCheck(false);
					else
						return;
				}
            });	
		}
		public Canvas getCanvas(){
			return canvas;
		}
}
	 
