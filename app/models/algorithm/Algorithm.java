package models.algorithm;

/**
 * Created with IntelliJ IDEA.
 * User: adinata
 * Date: 11/28/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Algorithm{
    public static final String KMP="KMP";
    public static final String BM="BM";
    private static Algorithm instance;

    private IAlgorithm algorithm;
    private String algorithmName;
    private Algorithm(){
        algorithm = new KMP();
        algorithmName = KMP;
    }
    public static Algorithm S() {
        if (instance == null) {
            instance = new Algorithm();
        }
        return instance;
    }
    public void setAlgorithm(String algoName) {
        if (algoName.equals(algorithmName)) {
            return;
        }
        if (algoName.equals(KMP)) {
            algorithm = new KMP();
        } else {
            algorithm = new BoyerMoore();
        }
    }
    public int countWord(String text, String pattern) {
        return algorithm.countWord(text, pattern);
    }
}
