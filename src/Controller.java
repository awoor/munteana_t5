
import java.awt.TextArea;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Aceasta clasa formeaza un obiect de tip Dictionar care contine un HashMap de 1000
 * ca variabila instanta si nu are constructor definit folosind pe cel predefinit de
 * java fara parametrii
 * 
 * 
 * @author Andrei
 */
public class Controller implements InterfDictionar {

/**
 * metoda care primeste ca parmetru numele fisierului care va fi format pt
 * salvarea informatiei din dictionar
 * 
 * 
 * @return -cod de terminare a metodei 0-succes; 1- eroare de asertiune;
 * 2- IO exceptie
 * @param numeFis - numele fisierului
 * @pre isNotEmpty()
 * @pre ifAllHaveExplain();
 */
    public int save(String numeFis) {        
		try{
			assert isNotEmpty();
			assert ifAllHaveExplain();
			assert numeFis!=null;
			Object[] s=(h.keySet()).toArray();
			BufferedWriter out = new BufferedWriter(new FileWriter(numeFis+".txt"));
			for(int i=0;i<h.size();i++){
				out.write(s[i]+"="+this.search((String)s[i]));
				out.newLine();
			}
			out.close();
		}catch(IOException e){
				return 2;
		}
		catch(AssertionError e){
			return 1;
		}
		
		return 0;
    } 

/**
 * Aceasta metoda adauga un cuvant si explicatia lui la dictionar
 * cu ajutorul paramatrilor de la intrare a metodei
 * 
 * 
 * @return - cod terminare metod: 0 -succes; 1 -eroare de asertiune;
 * @param cuv -cuvantul care va fi adaugat in dictionar
 * @param explic -explicatia cre va fi adugaqta in dictionar
 * @pre ifAllHaveExplain()
 * @pre cuv!=""
 * @pre explic!=""
 */
    public int add(String cuv, String explic) {        
		try{
			assert ifAllHaveExplain();
			assert cuv!="";
			assert explic!="";
			h.put((Object)cuv,(Object)explic);
		}catch(AssertionError e){
			return 1;
		}
		return 0;
    } 

/**
 * aceasta metoda cauta daca exista un cuvant in dictionar
 * 
 * 
 * @return -true daca exista si false dac nu
 * @param cuv -cuvantul de cautat
 */
    public boolean check(String cuv) {        
		if ((String)h.get((Object)cuv)==null)return false;
		return true;
    } 

/**
 * aceasta metoda populeaza dictionarul
 * 
 * 
 * @return -cod de iesire din metoda: 0 -succes; 1 -eroare de asertiune; 2 -IO exceptie;
 * 3 -FileNotFound eror;
 * @param numeFis -fisierului din care trebuie preluata informtia pentru a fi
 * introdusa in dictionar, aici se introduce o regula "cuv"="explic" doar asa
 * aceasta metoda va prelua informatia din fisierul txt
 * @pre ifAllHaveExplain()
 */
    public int populate(String numeFis) {        
		try{
			assert ifAllHaveExplain();
			String s;
			BufferedReader in=new BufferedReader(new FileReader(numeFis+".txt") );
			while((s=in.readLine())!=null){//addWordsToMap(in,h);
				String[] temp=s.split("=");
				h.put((Object)temp[0],(Object)temp[1]);
			}
			in.close();
		}catch (FileNotFoundException e){
			return 3;
		}
		catch (IOException e){
			return 2;
		}
		catch(AssertionError e){
			return 1;
		}
		return 0;
    } 

/**
 * aceasta metoda sterge un cuvant din dictionar
 * 
 * 
 * @return -cod de iesire din metoda: 0 -succes; 1 -erore de asertiune;
 * @param cuv -cuvantul de sters
 * @pre isNotEmpty()
 * @pre ifAllHaveExplain();
 * @pre cuv!=null
 */
    public int remove(String cuv) {        
		try{
			assert isNotEmpty();
			assert ifAllHaveExplain();
			assert cuv!=null;
			if ((String)h.get((Object)cuv)==null)return 1;
			else {
				h.remove((Object)cuv);
			}
		}catch(AssertionError e){
			return 1;
		}
		return 0;
    } 

/**
 * aceasta metoda cauta un cuvant dat ca parametru a metodei in dictionar si daca il gaseste
 * returneaza explicatia lui altfel returneza null
 * 
 * 
 * @return -explicatia cuvantului daca a fost gasit sau null altfel
 * @param cuv - cuvantul de cautat din dictionar
 * @pre isNotEmpty()
 * @pre ifAllHaveExplain();
 * @pre cuv!=null
 */
    public String search(String cuv) {        
		try{
			assert isNotEmpty();
			assert ifAllHaveExplain();
			assert cuv!=null;
			if ((String)h.get((Object)cuv)!=null)
				return cuv+"="+(String)h.get((Object)cuv);
		}catch(AssertionError e){
			return null;
		}
		return null;
    } 

/**
 * acesta este Variabila in care se pastreaza informatia din dictionar
 * 
 */
    private static HashMap h = new HashMap(1000);

/**
 * aceasta este de asemenea o metoda de cautare a unui cuvant in dictionar,
 * dar este o metoda de cautare mai avansata, cauta si cuvinte care
 * nu sunt cunoscute unele litere
 * 
 * 
 * @return -explicatia cuvantului daca a fost gasit sau null altfel
 * @param cuv -cuvantul de cautat din dictionar
 * @pre isNotEmpty()
 * @pre ifAllHaveExplain();
 * @pre cuv!=null
 */
    public String searchDeep(String cuv) {        
		StringBuffer ss=new StringBuffer();
		try{	
			assert isNotEmpty();
			assert ifAllHaveExplain();
			assert cuv!=null;
			cuv=cuv.replace("*","[a-zA-Z]*");
			cuv=cuv.replace("?", "[a-z]");
			Pattern p=Pattern.compile(cuv);
			Set set=h.keySet();
			Iterator i=set.iterator(); 
			while(i.hasNext()){
				String s=(String)i.next();
				Matcher m=p.matcher(s);
				if(m.matches()){
					ss.append(s+"="+h.get(m.group())+"\n");
				}
			}
		}catch(AssertionError e) {
			return null;
		}
		return ss.toString();
    } 

/**
 * 
 * 
 * 
 * @return -returneaza 0 daca dictionarul este consistent, -1 daca nu
 * si -1 daca apare eroare de asertiune
 * @pre isNotEmpty()
 * @pre ifAllHaveExplain();
 */
    public int esteCons() {        
		int ok=1;  //presupunem ca dictionarul e consistent pentru ok=1
		try{
			assert isNotEmpty();
			assert ifAllHaveExplain();
			Set setk=h.keySet();       //instantiem un obiect de clasa Set pentru ca sa putem parcurge cheile cu un Iterator
			Iterator ik=setk.iterator(); 
			String word=null,def=null;
			while (ik.hasNext()){  
				try{
					String s=(String)h.get(ik.next()); //punem definitia intr-un string
					StringTokenizer st2=new StringTokenizer(s); //impartim definitia dupa spatiu in cuvinte 
					String[] search=new String[100];     //in search vom pune cuvintele
					int i=0;
					while((search[i]=st2.nextToken())!=null) //cat timp avem cuvinte in definitie...
						{
						if((search[i].substring(search[i].length()-1).equals(","))||(search[i].substring(search[i].length()-1).equals("."))||search[i].substring(search[i].length()-1).equals(";")) search[i]=search[i].substring(0,search[i].length()-1); //eliminam virgula, doua puncte sau punct si virgula daca este cazul
						if(check(search[i])==false) ok=0; //daca cuvantul nu este definit ok ia valoarea 0
							i++;
						};
				}catch(NoSuchElementException e){}
		     }
		}catch(AssertionError e){
			return 1;
		}
		if (ok==1) return 0;
		else return -1;
    } 

/**
 * aceasta este o metoda privata si statica a clasei care este
 * folosita ca si assertiune
 * 
 * 
 * @return - true daca nu este goala si false daca este goala
 */
    private static boolean isNotEmpty() {        
		if (h.isEmpty()) return false;
		return true;
    } 

/**
 * aceasta este o metoda privata si statica a clasei care este
 * folosita ca si assertiune
 * 
 * 
 * @return - daca toate cuvintele din dictionar au explicatie si false altfel
 */
    private static boolean ifAllHaveExplain() {        
		if (h.entrySet().size()!=h.keySet().size()) return false;
		return true;
    } 
 }
