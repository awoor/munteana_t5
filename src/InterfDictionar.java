
/**
 * aceasta este o interfatza pentru clasa Dictionar cu specificarea metodelor care sunt
 * implementatre in aceasta clasa
 * 
 * 
 * @author Andrei
 */
public interface InterfDictionar {
/**
 * 
 * 
 * 
 * @return 
 * @param cuv 
 * @param explic 
 */
    public int add(String cuv, String explic);
/**
 * 
 * 
 * 
 * @return 
 * @param cuv 
 */
    public int remove(String cuv);
/**
 * 
 * 
 * 
 * @return 
 * @param cuv 
 */
    public String search(String cuv);
/**
 * 
 * 
 * 
 * @return 
 * @param cuv 
 */
    public String searchDeep(String cuv);
/**
 * 
 * 
 * 
 * @return 
 * @param cuv 
 */
    public boolean check(String cuv);
/**
 * 
 * 
 * 
 * @return 
 * @param numeFis 
 */
    public int populate(String numeFis);
/**
 * 
 * 
 * 
 * @return 
 * @param numeFis 
 */
    public int save(String numeFis);
/**
 * 
 * 
 * 
 * @return 
 */
    public int esteCons();
}


