public class Subtitle {
    private double start;
    private double end;
    private String text;

    public Subtitle(double start, double end, String text) {
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public String getText() {
        return text;
    }
}
