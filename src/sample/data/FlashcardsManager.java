package sample.data;

import java.sql.*;
import java.util.LinkedList;

public class FlashcardsManager extends Credentials{
    public LinkedList<Flashcard> getFlashcards() {
        Connection conn = null;
        LinkedList<Flashcard> data = new LinkedList<>();
        try{

            conn = DriverManager.getConnection(url, credentialsUser, credentialsPassword);

            Statement stmt = conn.createStatement();

            String strSelect = "select id, front_side, back_side, front_side_lang, back_side_lang\n" +
                               "from flashcards.flashcards\n";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                int id = rset.getInt(1);
                String frontSide = rset.getString(2);
                String backSide = rset.getString(3);
                String frontSideLang = rset.getString(4);
                String backSideLang = rset.getString(5);

                Flashcard fc = new Flashcard(id, frontSide, backSide, frontSideLang, backSideLang);

                data.add(fc);
            }
            conn.close();
            rset.close();
            stmt.close();

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try{
                if (conn!=null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return data;
    }

    public void addFlashcard(String frontSide, String backSide, String frontSideLang, String backSideLang) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, credentialsUser, credentialsPassword);

            String query = "insert into flashcards.flashcards (front_side, back_side, front_side_lang, back_side_lang)\n" +
                    "values (\n" +
                    "        ?,\n" +
                    "        ?,\n" +
                    "        ?,\n" +
                    "        ?\n" +
                    "       );";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, frontSide);
            preparedStmt.setString (2, backSide);
            preparedStmt.setString(3, frontSideLang);
            preparedStmt.setString(4, backSideLang);

            preparedStmt.execute();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void editFlashcard(int id, String frontSide, String backSide, String frontSideLang, String backSideLang) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, credentialsUser, credentialsPassword);

            String query = "update flashcards.flashcards\n" +
                    "set\n" +
                    "    front_side = ?,\n" +
                    "    back_side = ?,\n" +
                    "    front_side_lang = ?,\n" +
                    "    back_side_lang = ?\n" +
                    "where id = ?;";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, frontSide);
            preparedStmt.setString (2, backSide);
            preparedStmt.setString(3, frontSideLang);
            preparedStmt.setString(4, backSideLang);
            preparedStmt.setInt(5, id);

            preparedStmt.execute();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public void deleteFlashcard (int id) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, credentialsUser, credentialsPassword);

            String query = "delete from flashcards.flashcards where id = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }
//    Test:
//    public static void main(String[] args) throws SQLException {
//        FlashcardsManager fcm = new FlashcardsManager();
//
////        fcm.addFlashcard("열기", "heat", "Korean", "English");
////        fcm.addFlashcard("حرارة", "heat", "Arabic", "English");
//
//        fcm.editFlashcard(5, "hsdgf", "jefgwh", "jnshfgyrt", "hjwtyr");
//
//        LinkedList<Flashcard> flashcards = fcm.getFlashcards();
//
//        int i = flashcards.size();
//
//        Flashcard fc = flashcards.get(0);
//        System.out.println(i );
//        for (Flashcard flcrd : flashcards) {
//            System.out.println(flcrd.getFrontSide() + " (" + flcrd.getFrontSideLang()+ "), " + flcrd.getBackSide() + " (" + flcrd.getBackSideLang() + ")");
//        }
//    }
}
