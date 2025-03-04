package data;

public class LoginLandingPageData {
    private String testCaseId; 
    private String username;
    private String password;
    private String description;
    private String Run;       // ✅ Add "Run" Column
    private String TestSuite; // ✅ Add "TestSuite" Column
    
    
    // Getters and Setters
    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setRun(String Run) {
        this.Run = Run;
    }
    
    public void setTestSuite(String TestSuite) {
        this.TestSuite= TestSuite;
    }
    public String getRun() { return Run; }  // ✅ Getter for "Run"
    public String getTestSuite() { return TestSuite; } // ✅ Getter for "TestSuite"
}
