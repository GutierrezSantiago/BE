package utn.frc.bda.k4.lib.util.menu;

public abstract class MenuItem implements MenuCall {
    private final String key;
    private final String text;

    public MenuItem(String key, String text) {
        this.key = key;
        this.text = text;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the Text
     */
    public String getText() {
        return text;
    }

    @Override
    public abstract void execute();
}
