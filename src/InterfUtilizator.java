
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * aceasta clasa formeaza o fereastra cu butoane si casutze de text pentru simulrea
 * unui dictionar
 * 
 * 
 * @author Andrei
 */
public class InterfUtilizator extends JFrame implements ActionListener {
/**
 * 
 * 
 */
    JLabel word;
/**s
 * 
 * 
 */
    JLabel explic;
/**
 * 
 * 
 */
    JTextField search;
/**
 * 
 * 
 */
    TextArea tArea;
/**
 * 
 * 
 */
    JButton buttonCons;
/**
 * 
 * 
 */
    JButton buttonSearch;
/**
 * 
 * 
 */
    JButton buttonAdd;
/**
 * 
 * 
 */
    JButton buttonClear;
/**
 * 
 * 
 */
    JButton buttonRemove;
/**
 * 
 * 
 */
    JButton buttonSave;
/**
 * 
 * 
 */
    JButton buttonLoad;
/**
 * 
 * 
 */
    JButton buttonCheck;
/**
 * 
 * 
 */
    JScrollPane scroll;
/**
 * 
 * 
 */
    Controller d;

/**
 * constructorul clasei nu are nici un parametru de intrare si apeleaza constructorul super clasei
 * JFrame pentru a seta numele ferestrei, si pentru a seta diferiti
 * paramtri a ferestrei care va aparea: dimensiune, layout, setarea
 * butoanelor si label-uri in fereastra la pozitzii specifice si atribuirea
 * butoanelor nishte ascultatori si comenzi
 * 
 */
     InterfUtilizator() {        
		super("Dictionar");
		setSize(655,250);//marimea
        setLocationRelativeTo(null);//alinierea
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        d=new Controller();
		
		word=new JLabel("Cauta:");
        word.setBounds(50,50,130,20);
        explic=new JLabel("Sinonime:");
        explic.setBounds(280,50,130,20);
        
        search=new JTextField();
        search.setBounds(50,70,100,20);
        
        buttonSearch=new JButton("GO");
        buttonAdd=new JButton("Adauga");
        buttonClear=new JButton("Curata");
        buttonRemove=new JButton("Sterge");
        buttonLoad=new JButton("Populeaza");
        buttonSave=new JButton("Salveaza");
        buttonCons=new JButton("Este consistent?");
        
        buttonSearch.addActionListener(this);
        buttonAdd.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonRemove.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonCons.addActionListener(this);
        
        buttonSearch.setActionCommand("bs");
        buttonAdd.setActionCommand("ba");
        buttonClear.setActionCommand("bc");
        buttonRemove.setActionCommand("br");
        buttonLoad.setActionCommand("bl");
        buttonSave.setActionCommand("bsv");
        buttonCons.setActionCommand("bcs");
        
        buttonSearch.setBounds(160,70,100,30); 
        buttonAdd.setBounds(50,10,100,30);          
        buttonClear.setBounds(160,10,100,30);          
        buttonRemove.setBounds(270,10,100,30);          
        buttonSave.setBounds(380,10,100,30);          
        buttonLoad.setBounds(490,10,100,30);
        buttonCons.setBounds(50,120,150,60);
        
        tArea = new TextArea();
        tArea.setBounds(270,70,320,120); 
        
        add(word);add(explic);add(search);add(buttonSearch);add(tArea);
        add(buttonAdd);add(buttonClear);add(buttonRemove);add(buttonSave);
        add(buttonLoad);add(buttonCons);
        setVisible(true);
    } 

/**
 * aceasta este implementarea metodei din interfata ActionListener
 * care reprezinta tratarea evenimentului aparut
 * 
 * 
 * @param e -evenimentu care apare in timpul executiei, adica apasarea unui buton
 */
    public void actionPerformed(ActionEvent e) {        
		String comand = e.getActionCommand();
		if (comand=="bs"){
			tArea.setText("");
			String s=getFromCauta();
			if (d.search(getFromCauta())==null)
				if(d.searchDeep(s)==null)
					this.addToExpl("Nu a fost gasit cuvantul cautat, sau a aparut una din erori: " +
							"\nDictionar gol, Nu toate cuvintele au explicatie ");
				else this.addToExpl(d.searchDeep(s));
			else this.addToExpl(d.search(s));
			
			
		}else if(comand=="ba"){
			if (d.add(this.getFromCauta(),this.getFromExpl())==0)
				this.afis("A fost adaugat cuvantul in dictionar cu succes!!!", "Mesaj");
			else
				this.afis("Sunt erori de asertiune!!!", "Eroare");
			search.setText("");
			tArea.setText("");
			
			
		}else if(comand=="bc"){
			tArea.setText("");
			search.setText("");
			
			
		}else if(comand=="br"){
			if (d.remove(this.getFromCauta())==1)
				this.afis("Sunt erori de asertiune!!!", "Eroare");
			else
				this.afis("A fost sters cuvantul din dictionar cu succes!!!", "Mesaj");
			tArea.setText("");
			search.setText("");
			
		}else if(comand=="bl"){
			tArea.setText("");
			search.setText("");
			int t=d.populate(this.getString("Introduceti numele fisierului"));
			if (t==0)
				this.afis("A fost populat dictionarul cu succes!!!", "Mesaj");
			else if(t==1)
				this.afis("Sunt erori de asertiune!!!", "Eroare");
			else if(t==2)
				this.afis("IO Eroare!!!", "Eroare");
			else if(t==3)
				this.afis("Nu a fost gasit fisierul respectiv", "Eroare");
			
			
		}else if(comand=="bsv"){
			tArea.setText("");
			search.setText("");
			int u=d.save(this.getString("Introduceti numele fisierului"));
			if (u==0)
				this.afis("A fost salvat dictionarul cu succes!!!", "Mesaj");
			else if (u==1)
				this.afis("Sunt erori de asertiune!!!", "Eroare");
			else
				this.afis("IO Eroare!!!", "Eroare");
		}else if(comand=="bcs"){
			if (d.esteCons()==0)
				this.afis("Dictionarul este consistent!","Mesaj");
			else if (d.esteCons()==-1)
				this.afis("Dictionarul nu este consistent!","Mesaj");
			else
				this.afis("Sunt erori de asertiune!!!", "Eroare");
		}
    } 

/**
 * metoda care formeza un JOptionPane cu mesaje definite de parametrii
 * de la intrarea metodei
 * 
 * 
 * @param s1 -Informatia din mesaj
 * @param s2 -numele ferestrei
 */
    public static void afis(String s1, String s2) {        
		JOptionPane.showMessageDialog(null,s1,s2, JOptionPane.INFORMATION_MESSAGE);
    } 

/**
 * metoda care formeza un JOptionPane cu mesaje definite de parametrii
 * de la intrarea metodei, in  care pot fi intoduse date care sunt returnate de metoda
 * 
 * 
 * @return (String)informatia intodusa de la tastatura
 * @param s -informatia din ferestra de mesaj
 */
    public static String getString(String s) {        
		return JOptionPane.showInputDialog(s);
    } 

/**
 * o metoda privata clasei care returneaza stringul scris in casuta de
 * cautare a dictionarului
 * 
 * 
 * @return -stringul scris in aceasta casuta
 */
    private String getFromCauta() {        
		if (search.getText()!="")
			return search.getText();
		else return null;
    } 

/**
 * aceasta metoda adauga un string in casutza de explicatii a dictionarului
 * 
 * 
 * @param s - stringul de daugat
 */
    private void addToExpl(String s) {        
		tArea.append(s);
    } 

/**
 * o metoda privata clasei care returneaza stringul scris in casuta de
 * explicatii a dictionarului
 * 
 * 
 * @return -stringul scris in aceasta casuta
 */
    private String getFromExpl() {        
		if (tArea.getText()!="")
			return tArea.getText();
		else return null;
    } 

/**
 * metoda care apeleaza inceputul programului
 * 
 * 
 * @param args -argumente de intrare
 */
    public static void main(String[] args) {        
		InterfUtilizator n=new InterfUtilizator(); 
    } 
 }
