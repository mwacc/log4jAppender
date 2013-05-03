package common;

public class MonitoringMessage {
    private String label;
    private long usedTime;

    public MonitoringMessage() {}

    public MonitoringMessage(String label, long usedTime){
        this.label = label;
        this.usedTime = usedTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if( getLabel() == null ) throw new IllegalArgumentException("Nullable label!");

        builder.append( String.format("\"label\":\"%s\",", getLabel()) );
        builder.append( String.format("\"usedTime\":\"%s\",", getUsedTime()) );

        return String.format("{%s}", builder.toString().substring(0, builder.length() - 1));
    }
}