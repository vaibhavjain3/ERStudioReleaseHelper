package main.java.com.erstudio.tsversionchange.model;

public class VersionFormat {

String format;
int majorVersion;
int minorVersion;
int revisionNumber;
int buildNumber;

    public VersionFormat(int majorVersion, int minorVersion, int revisionNumber, int buildNumber) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.revisionNumber = revisionNumber;
        this.buildNumber = buildNumber;
    }

    public VersionFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }
}
