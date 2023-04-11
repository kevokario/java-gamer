import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;


        
        
public class ImageNameGuessor extends JFrame implements ActionListener{
    
    JPanel gamePlayPanel = new JPanel();
    JPanel mainPanel = new JPanel();  
    JPanel containerPanel = new JPanel();
    
    JTextField txtAnswer = new JTextField();

    JLabel lblTitle = new JLabel();
    JLabel lblImage = new JLabel();
    
    JPanel bottomPanel = new JPanel();  
    JPanel buttonPanel = new JPanel();

    JButton btnOkay = new JButton(); 
    JButton btnClose = new JButton();

    JLabel lblImageName = new JLabel();
    
    Font font = new Font("Helvetica",Font.BOLD, 14);
    
    //list for images
    ArrayList<String> images = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    
    //index for current image and answer
    int index = 0;
    int correctAnswers = 0;
    
    
    
    public ImageNameGuessor(){
        //set title for window
        setTitle("Image Name Guessor");
        
        //set size and location for window
        setSize(1200,1000);
        setLocationRelativeTo(null);
        
        //set window behaviour
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //set main layout
        setLayout(new BorderLayout());
        
        //add content to window
        setLayout(new BorderLayout());
        add(containerPanel,BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        //add main panel views
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 36));
        lblTitle.setText("Can you guess the name ?");
        
        lblImage.setPreferredSize(new Dimension(800, 650));
//        lblImage.setIcon(new ImageIcon(getClass().getResource("apple.png")));
        
        mainPanel.setLayout(new BorderLayout(55,5));
        mainPanel.add(lblImage,BorderLayout.CENTER);
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(txtAnswer, BorderLayout.SOUTH);
        txtAnswer.setFont(new Font("Helvetica",Font.PLAIN,23));
        txtAnswer.setPreferredSize(new Dimension(800,40));
        
        containerPanel.add(mainPanel);
         TitledBorder containerTitleBorder = new TitledBorder("Guess the name!");
        containerTitleBorder.setTitleFont(font);
        containerPanel.setBorder(containerTitleBorder);
        
        //add buttons to button panel
        bottomPanel.setLayout(new GridLayout(1, 2, 5, 5));
        bottomPanel.add(new JLabel());
        bottomPanel.add(buttonPanel);
        
        TitledBorder titleBorder = new TitledBorder("Click here");
        titleBorder.setTitleFont(font);
        bottomPanel.setBorder(titleBorder);
        
        //set text and action handler to button okay
        buttonPanel.add(btnClose);
        buttonPanel.add(btnOkay);        
        buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        btnOkay.setText("Save Answer");
        btnOkay.setFont(new Font("Helvetica", Font.BOLD, 24));
        btnOkay.addActionListener(this);
        btnOkay.setPreferredSize(new Dimension(300,100));
        
        btnClose.setText("Give up");
        btnClose.setFont(new Font("Helvetica", Font.BOLD, 24));
        btnClose.addActionListener(this);
        btnClose.setPreferredSize(new Dimension(250,100));
        
      
        
        
        //Add Images to the options
        images.add("apple.png"); 
        images.add("cat.jpeg");  
        images.add("dog.jpeg");

        
        //Add answers to the options
        answers.add("apple");
        answers.add("cat");  
        answers.add("dog");    
  
        
        

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageNameGuessor imageNameGuessor = new ImageNameGuessor();
        imageNameGuessor.setVisible(true);
        imageNameGuessor.loadImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Save Answer")){
            checkAnswer();
        }
        if(action.equals("Give up")){
            giveUp();
        }
  
        if(action.equals("Exit Game")){
            this.dispose();
        }
    }
    
   
    public void checkAnswer(){
        String answer = txtAnswer.getText().trim();
        if(answer.equalsIgnoreCase(answers.get(index))){
            //correct answer, next question;
            JOptionPane.showMessageDialog(this,"Correct!, click ok to answer some more");
            index++;
            correctAnswers++;
            loadImage();
        } else {
            //clear text area, tell them try again
            JOptionPane.showMessageDialog(null,"Wrong answer, please try again");
            txtAnswer.setText("");
        }
    }
    
    public void giveUp(){
        JOptionPane.showMessageDialog(null,"Correct answer was "+answers.get(index));
        index++;
        loadImage();
    }
    
    public void loadImage(){
        if(index <= images.size()-1){
            String imageUrl = images.get(index);
            lblImage.setIcon(new ImageIcon(getClass().getResource(imageUrl)));
            txtAnswer.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Game Over. Score was ("+correctAnswers+" out of "+images.size()+") Press ok to play again");
            index = 0;
            loadImage();
        }
        
    }
    
}
