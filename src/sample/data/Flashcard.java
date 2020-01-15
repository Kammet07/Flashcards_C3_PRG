package sample.data;

public class Flashcard {

    int id;
    String frontSide;
    String backSide;
    String frontSideLang;
    String backSideLang;

    public Flashcard(int id, String frontSide, String backSide, String frontSideLang, String backSideLang){
        this.id = id;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.frontSideLang = frontSideLang;
        this.backSideLang = backSideLang;
    }

    public int getId() {
        return id;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public String getFrontSideLang() {
        return frontSideLang;
    }

    public String getBackSideLang() {
        return backSideLang;
    }

}
