package com.qpros.common;

import com.qpros.helpers.ReadWriteHelper;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarLog;
import de.sstoehr.harreader.model.HarPageTiming;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.*;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DTCMAnalysis2 {

    String driverPath = "C:\\Users\\HamzahAlrawi\\IdeaProjects\\ScholarshipsAutomatedTests\\src\\main\\resources\\browserDrivers\\windowschromedriver.exe";
    String sFileName = "C:\\HARFiles\\SeleniumEasy.har";

    public WebDriver driver;
    public BrowserMobProxy proxy;
    public boolean failed = false;
    public void setUp() throws UnknownHostException {
        try {
            // start the proxy
            proxy = new BrowserMobProxyServer();
            proxy.enableHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
            proxy.setTrustAllServers(true);
            proxy.setMitmDisabled(false);
            proxy.start(0);

            //get the Selenium proxy object - org.openqa.selenium.Proxy;
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
            // configure it as a desired capability
            DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
            capabilities.setCapability("applicationCacheEnabled", false);
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            logPrefs.enable(LogType.CLIENT, Level.ALL);
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito --media-cache-size=1 --disk-cache-size=1");
            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            //set chromedriver system property
            System.setProperty("webdriver.chrome.driver", driverPath);
            //options.setProxy(seleniumProxy);
            driver = new ChromeDriver(capabilities);

            // enable more detailed HAR capture
            proxy.enableHarCaptureTypes(CaptureType.getAllContentCaptureTypes());

            failed = false;
        }
        catch (Exception e){failed = true;}


    }

    @Test
    public void doMagic() throws HarReaderException, IOException, InterruptedException {
        int totalNumberOfPages = 7;
        String fileReadPath = "C:\\HARFiles\\pageList.csv";
        String fileResultPath = "C:\\HARFiles\\Result.csv";
        String fileErrorPath = "C:\\HARFiles\\Errors.csv";
        String[] resultData = new String[totalNumberOfPages + 2];
        String[] errorData = new String[totalNumberOfPages + 2];
        try {
            String[][] pageList = ReadWriteHelper.readCSVFile(fileReadPath, totalNumberOfPages);
            resultData[0] = "URL, DOMInteractive, Load, FirstRequests, FirstTransferred, Finish, FinalRequests, FinalTransferred\n";
            for (int i = 0; i < totalNumberOfPages; i++) {
                setUp();
                if (!failed) {
                    driver.manage().deleteAllCookies();
                    String savePath = "C:\\HARFiles\\cuurentHAR.har";
                    proxy.newHar(savePath);
                    driver.get(pageList[i][0]);
                    driver.get("https://www.google.com");
                    driver.get(pageList[i][0]);
                    Thread.sleep(1000);
                    int oldNumRequests = getFinalNumberRequests();
                    double fileSize = getFinalPageSize();
                    double domInteractive = getFinalDomInteractive();
                    double finishTime = getFinalFinishTime();
                    double loadedTime = getFinalLoadedTime();

                    if (domInteractive >= 900 || loadedTime >= 1000) {
                        driver.get(pageList[i][0]);
                        Thread.sleep(1000);
                        oldNumRequests = getFinalNumberRequests();
                        fileSize = getFinalPageSize();
                        domInteractive = getFinalDomInteractive();
                        finishTime = getFinalFinishTime();
                        loadedTime = getFinalLoadedTime();
                        System.out.println("Very suspicious!");
                    }

                    try {
                        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                        while (true) {
                            for (int j = 1; j <= 100; j++) {
                                //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight/" + j + ");");
                                Actions builder = new Actions(driver);
                                builder.sendKeys(Keys.DOWN);
                                builder.perform();
                                Thread.sleep(50);
                            }
                            long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                            if (newHeight == lastHeight) {
                                break;
                            }
                            lastHeight = newHeight;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resultData[i + 1] = pageList[i][0] + "," +
                            Double.toString(domInteractive) + "," +
                            Double.toString(loadedTime) + "," +
                            Double.toString(oldNumRequests) + "," +
                            Double.toString(fileSize) + "," +
                            Double.toString(finishTime) + "," +
                            Double.toString(getFinalNumberRequests()) + "," +
                            Double.toString(getFinalPageSize());
                    errorData[i + 1] = pageList[i][0] + ", Passed";
                    System.out.println(resultData[i + 1]);
                    tearDown();
                } else if (failed) {
                    resultData[i + 1] = pageList[i][0] + ", AN ERROR OCCURED HERE!, THIS PAGE NEEDS TO BE RETESTED";
                    errorData[i + 1] = pageList[i] + ", THIS PAGE DID NOT WORK!";
                }
            }
            for (String result : resultData) {
                System.out.println(result);
            }
        }
        finally {
            ReadWriteHelper.writeCSVFile(fileResultPath, resultData);
            ReadWriteHelper.writeCSVFile(fileErrorPath, errorData);
        }


    }
    public void tearDown() {
        if (driver != null) {
            proxy.stop();
            driver.quit();
        }
    }

    public void saveHarFile(String path){
        Har har = proxy.getHar();

        // Write HAR Data in a File
        File harFile = new File(path);
        try {
            har.writeTo(harFile);
        } catch (IOException ex) {
            System.out.println (ex.toString());
            System.out.println("Could not find file " + sFileName);
        }
    }

    public double getFileSize(String filePath){
        File file =new File(filePath);
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        return kilobytes;
    }

    public int getOnLoad(String filepath) throws HarReaderException {
        HarReader harReader = new HarReader();
        de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File(filepath));

        HarLog log = har.getLog();
        HarPageTiming myTest = log.getPages().get(0).getPageTimings();
        return myTest.getOnLoad().intValue();

    }

    public int getContentLoad(String filepath) throws HarReaderException {
        HarReader harReader = new HarReader();
        de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File(filepath));

        HarLog log = har.getLog();
        HarPageTiming myTest = log.getPages().get(0).getPageTimings();
        return myTest.getOnContentLoad().intValue();

    }

    public double calculatePageLoadTime(String filename) throws HarReaderException {
        HarReader harReader = new HarReader();
        de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File(filename));

        HarLog log = har.getLog();
        // Access all pages elements as an object

        long startTime =   log.getPages().get(0).getStartedDateTime().getTime();

        // Access all entries elements as an object

        List<HarEntry> hentry = log.getEntries();

        long loadTime = 0;

        int entryIndex = 0;
        //Output "response" code of entries.
        for (HarEntry entry : hentry)
        {

            long entryLoadTime = entry.getStartedDateTime().getTime() + entry.getTime();

            if(entryLoadTime > loadTime){
                loadTime = entryLoadTime;
            }

            entryIndex++;
        }

        long loadTimeSpan = loadTime - startTime;

        Double webLoadTime = ((double)loadTimeSpan) / 1000;
        double webLoadTimeInSeconds = Math.round(webLoadTime * 100.0) / 100.0;

        return webLoadTimeInSeconds;
    }

    public int getNumberRequests(String filename) throws HarReaderException {
        HarReader harReader = new HarReader();
        de.sstoehr.harreader.model.Har har = harReader.readFromFile(new File(filename));

        HarLog log = har.getLog();
        return log.getEntries().size();
    }
    public int getFinalPageSize(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher dataLengthMatcher1 = Pattern.compile("transferSize=(.*?),").matcher(netData);
        int sumEncoded = 0;
        while (dataLengthMatcher1.find()){
            //System.out.println("File size: " + dataLengthMatcher1.group(1));
            sumEncoded = sumEncoded + Integer.parseInt(dataLengthMatcher1.group(1));
        }
        return sumEncoded;
    }

    public double getFinalDomInteractive(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher domInteractive = Pattern.compile("domInteractive=(.*?),").matcher(netData);
        double amountDom = 0.0;
        while (domInteractive.find()){
            //System.out.println("DomInteractive: " + domInteractive.group(1));
            if (Double.parseDouble(domInteractive.group(1)) >= amountDom){
                amountDom = Double.parseDouble(domInteractive.group(1));
            }
        }
        return amountDom;
    }
    public double getFinalLoadedTime(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher loadEventEnd = Pattern.compile("loadEventEnd=(.*?),").matcher(netData);
        double loadEventTime = 0.0;
        while (loadEventEnd.find()){
           // System.out.println("LoadEventEnd: " + loadEventEnd.group(1));
            if (Double.parseDouble(loadEventEnd.group(1)) >= loadEventTime){
                loadEventTime = Double.parseDouble(loadEventEnd.group(1));
            }
        }
        return loadEventTime;
    }

    public double getFinalFinishTime(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher maxResponse = Pattern.compile("responseEnd=(.*?),").matcher(netData);
        double makResponseTime = 0.0;
        while (maxResponse.find()){
            //System.out.println("MaxResponse: " + maxResponse.group(1));
            if (Double.parseDouble(maxResponse.group(1)) >= makResponseTime){
                makResponseTime = Double.parseDouble(maxResponse.group(1));
            }
        }
        return makResponseTime;
    }
    public int getFinalNumberRequests(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher maxResponse = Pattern.compile("transferSize=(.*?),").matcher(netData);
        int numRequests = 0;
        while (maxResponse.find()){
            numRequests++;
        }
        return numRequests;
    }
    public int getFinalTransferSize(){
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        Matcher dataLengthMatcher2 = Pattern.compile("encodedBodySize=(.*?),").matcher(netData);
        int sumEncoded2 = 0;
        while (dataLengthMatcher2.find()){
            sumEncoded2 = sumEncoded2 + Integer.parseInt(dataLengthMatcher2.group(1));
        }
        return sumEncoded2;
    }

    public int getFinalTransferSize2(){
        int totalBytes = 0;
        for (LogEntry entry : driver.manage().logs().get(LogType.PERFORMANCE)) {
            if (entry.getMessage().contains("Network.dataReceived")) {
                Matcher dataLengthMatcher = Pattern.compile("dataLength\":(.*?),").matcher(entry.getMessage());
                dataLengthMatcher.find();
                totalBytes = totalBytes + Integer.parseInt(dataLengthMatcher.group(1));
                //Do whatever you want with the data here.
            }
        }
        return totalBytes;
    }




    public static void main(String[] args) throws HarReaderException, IOException, InterruptedException {
        /*ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/K lighthouse https://vduat.testvisitdubai.com/ --only-audits network-requests --output json --output-path=C:\\HARFiles\\TempJSON.json --emulated-form-factor desktop --disable-device-emulation", "start");
        Process myProcess = pb.start();
        Thread.sleep(20000);
        myProcess.destroy();
        System.out.println("Reached here now!");*/
        //DTCMAnalysis2 test = new DTCMAnalysis2();
        //System.out.println("Total page size: " + test.getTransferSizeString("C:\\HARFiles\\TempJSON.json"));
        //System.out.println("Total requsts: " +test.getTotalRequests("C:\\HARFiles\\TempJSON.json"));
        //pb.command("lighthouse https://edit.co.uk/blog/save-time-by-batch-processing-lighthouse-audit-files-from-the-command-line/ --output csv --output-path=C:\\\\HARFiles\\\\ExampleLighthouse.csv");
        //lighthouse https://edit.co.uk/blog/save-time-by-batch-processing-lighthouse-audit-files-from-the-command-line/ --output csv --output-path=C:\\HARFiles\\ExampleLighthouse.csv
        //Runtime.getRuntime().exec("cmd.exe /C start");
        //Runtime rt = Runtime.getRuntime();
        //rt.exec("cd C:/");
        //Process pr = rt.exec("lighthouse https://edit.co.uk/blog/save-time-by-batch-processing-lighthouse-audit-files-from-the-command-line/ --output csv --output-path=C:\\HARFiles\\ExampleLighthouse.csv");
        //Process pr = rt.exec("java -version");
        //rt.exec("node -version");
    }
    public String fileToString(String filepath) throws IOException {
        InputStream is = new FileInputStream(filepath);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine(); StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n"); line = buf.readLine();
        }
        return sb.toString();
    }
    public int getTransferSizeString (String filepath) throws IOException {
        String fileString = fileToString(filepath);
        int totalSize = 0;
        Matcher dataLengthMatcher2 = Pattern.compile("\"transferSize\": (.*?),").matcher(fileString);
        while (dataLengthMatcher2.find()){
            //System.out.println("Transfer size: " + dataLengthMatcher2.group(1));
            totalSize = totalSize + Integer.parseInt(dataLengthMatcher2.group(1));
        }
        return totalSize;
    }
    public int getTotalRequests (String filepath) throws IOException {
        String fileString = fileToString(filepath);
        int totalSize = 0;
        Matcher dataLengthMatcher2 = Pattern.compile("\"transferSize\": (.*?),").matcher(fileString);
        while (dataLengthMatcher2.find()){
            //System.out.println("Transfer size: " + dataLengthMatcher2.group(1));
            totalSize++;
        }
        return totalSize;
    }
}
