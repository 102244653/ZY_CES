package Tools.MyListener;

import Tools.ScreenShot.ScreenShot;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.collections.Objects;
import org.testng.internal.IResultListener2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */
public class TestNgListener implements IResultListener2 {
    private List<ITestNGMethod> m_allTestMethods = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_passedTests = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_failedTests = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_skippedTests = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_failedButWSPerTests = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestContext> m_testContexts = Collections.synchronizedList(new ArrayList());
    private List<ITestResult> m_failedConfs = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_skippedConfs = Collections.synchronizedList(Lists.newArrayList());
    private List<ITestResult> m_passedConfs = Collections.synchronizedList(Lists.newArrayList());

    public TestNgListener() {
    }

    public  String picname;

    public String getpicname(){
        return picname;
    }

    public void onTestSuccess(ITestResult tr) {
        // 自动截图
        picname= ScreenShot.screenShots(2,tr.getMethod().getMethodName());
        this.m_allTestMethods.add(tr.getMethod());
        this.m_passedTests.add(tr);
    }

    public void onTestFailure(ITestResult tr) {
        if(!this.m_failedTests.contains(tr.getMethod())) {
            // 自动截图
            picname= ScreenShot.screenShots(2,tr.getMethod().getMethodName());
            this.m_allTestMethods.add(tr.getMethod());
            this.m_failedTests.add(tr);
        }
    }

    public void onTestSkipped(ITestResult tr) {
        if(!this.m_skippedTests.contains(tr.getMethod())){
            // 自动截图
            picname= ScreenShot.screenShots(2,tr.getMethod().getMethodName());
            this.m_allTestMethods.add(tr.getMethod());
            this.m_skippedTests.add(tr);
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        this.m_allTestMethods.add(tr.getMethod());
        this.m_failedButWSPerTests.add(tr);
    }

    protected ITestNGMethod[] getAllTestMethods() {
        return (ITestNGMethod[])this.m_allTestMethods.toArray(new ITestNGMethod[this.m_allTestMethods.size()]);
    }

    public void onStart(ITestContext testContext) {
        this.m_testContexts.add(testContext);
    }

    public void onFinish(ITestContext testContext) {}

    public List<ITestResult> getFailedButWithinSuccessPercentageTests() {
        return this.m_failedButWSPerTests;
    }

    public List<ITestResult> getFailedTests() {
        return this.m_failedTests;
    }

    public List<ITestResult> getPassedTests() {
        return this.m_passedTests;
    }

    public List<ITestResult> getSkippedTests() {
        return this.m_skippedTests;
    }

    private static void ppp(String s) {
        System.out.println("[TestListenerAdapter] " + s);
    }

    public void setAllTestMethods(List<ITestNGMethod> allTestMethods) {
        this.m_allTestMethods = allTestMethods;
    }

    public void setFailedButWithinSuccessPercentageTests(List<ITestResult> failedButWithinSuccessPercentageTests) {
        this.m_failedButWSPerTests = failedButWithinSuccessPercentageTests;
    }

    public void setFailedTests(List<ITestResult> failedTests) {
        this.m_failedTests = failedTests;
    }

    public void setPassedTests(List<ITestResult> passedTests) {
        this.m_passedTests = passedTests;
    }

    public void setSkippedTests(List<ITestResult> skippedTests) {
        this.m_skippedTests = skippedTests;
    }

    public void onTestStart(ITestResult result) {
    }

    public List<ITestContext> getTestContexts() {
        return this.m_testContexts;
    }

    public List<ITestResult> getConfigurationFailures() {
        return this.m_failedConfs;
    }

    public void onConfigurationFailure(ITestResult itr) {
        this.m_failedConfs.add(itr);
    }

    public List<ITestResult> getConfigurationSkips() {
        return this.m_skippedConfs;
    }

    public void beforeConfiguration(ITestResult tr) {
    }

    public void onConfigurationSkip(ITestResult itr) {
        this.m_skippedConfs.add(itr);
    }

    public void onConfigurationSuccess(ITestResult itr) {
        this.m_passedConfs.add(itr);
    }

    public String toString() {
        return Objects.toStringHelper(this.getClass()).add("passed", Integer.valueOf(this.getPassedTests().size())).add("failed", Integer.valueOf(this.getFailedTests().size())).add("skipped", Integer.valueOf(this.getSkippedTests().size())).toString();
    }
}
