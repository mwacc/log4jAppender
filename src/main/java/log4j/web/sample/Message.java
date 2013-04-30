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
        return String.format("{ \"message\":\"%s\", \"stackTrace\":\"%s\", \"hostName\":\"%s\", \"threadName\":\"%s\", " +
                "\"className\":\"%s\", \"\"methodName\":\"%s\", \"lineNumber\":\"%s\", \"fileName\":\"%s\"," +
                "\"priority\":\"%s\", \"date\":\"%s\" }", getMessage(), getStackTrace(), getHostName(), getThreadName(),
                getClassName(), getMethodName(), getLineNumber(), getFileName(), getPriority(), getDate());
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