package apache_commons_confSample;

public class WPSPattern {
    private String name;
    private String pattern;
    public WPSPattern(String name, String pattern) {
        this.name = name;
        this.pattern = pattern;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
