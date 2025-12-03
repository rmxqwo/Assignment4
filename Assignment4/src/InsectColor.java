public enum InsectColor {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow");

    private final String name;

    InsectColor(String name){
        this.name = name;
    }

    public String getDisplayName(){
        return name;
    }

    public static InsectColor toColor(String s) throws InvalidInsectColorException {
        try{
            return valueOf(s.trim().toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidInsectColorException();
        }
        //return s.equals("RED") ? RED : s.equals("GREEN") ? GREEN :  s.equals("BLUE") ? BLUE :  YELLOW;
    }
}
