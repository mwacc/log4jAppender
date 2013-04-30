package log4j.web.sample;

public class Message {

    private String message;
    private String stackTrace;

    private String hostName;
    private String threadName;
    private String className;
    private String methodName;
    private String lineNumber;
    private String fileName;

    private String priority;
    private String date;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if( getMessage() != null ) builder.append( String.format("\"message\":\"%s\",", getMessage()) );
        if( getStackTrace() != null ) builder.append( String.format("\"stackTrace\":\"%s\",", getStackTrace()) );
        if( getHostName() != null ) builder.append( String.format("\"hostName\":\"%s\",", getHostName()) );
        if( getThreadName() != null ) builder.append( String.format("\"threadName\":\"%s\",", getThreadName()) );
        if( getClassName() != null ) builder.append( String.format("\"className\":\"%s\",", getClassName()) );
        if( getMethodName() != null ) builder.append( String.format("\"methodName\":\"%s\",", getMethodName()) );
        if( getLineNumber() != null ) builder.append( String.format("\"lineNumber\":\"%s\",", getLineNumber()) );
        if( getFileName() != null ) builder.append( String.format("\"fileName\":\"%s\",", getFileName()) );
        if( getPriority() != null ) builder.append( String.format("\"priority\":\"%s\",", getPriority()) );
        if( getDate() != null ) builder.append( String.format("\"date\":\"%s\",", getDate()) );

        return String.format("{%s}", builder.toString().substring(0, builder.length()-1) );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}