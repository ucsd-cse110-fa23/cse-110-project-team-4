package client;

public class RecipeShareLogic {
    public static String createShareLinkString(String uuid){
        return "localhost:8100/recipe/share?=" + uuid;
    }
}
