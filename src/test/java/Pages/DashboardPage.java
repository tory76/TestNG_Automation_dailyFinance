
package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "add-cost-button")
    WebElement btnAddCost;

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(id = "amount")
    WebElement txtAmount;

    @FindBy(id = "purchaseDate")
    WebElement txtPurchaseDate;

    @FindBy(id = "month")
    WebElement drpMonth;

    @FindBy(id = "remarks")
    WebElement txtRemarks;

    @FindBy(xpath = "//button[text()='+']")
    WebElement btnPlus;

    @FindBy(xpath = "//span[contains(text(),'Total Rows')]")
    WebElement lblTotalRows;

    @FindBy(css = "[type='submit']")
    WebElement btnSubmit;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public boolean isAddCostDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(btnAddCost)
        ).isDisplayed();
    }

    public void waitForTotalRowsToLoad() {

        By totalRowsLocator =
                By.xpath("//span[contains(normalize-space(), 'Total Rows')]");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        totalRowsLocator
                )
        );

        wait.until(driver -> {

            String text =
                    driver.findElement(totalRowsLocator)
                            .getText()
                            .trim();

            System.out.println(
                    "Waiting for Total Rows: " + text
            );

            return text.matches(
                    "Total Rows:\\s*\\d+"
            );
        });
    }
    // Add only mandatory fields
public void addItems(String item, String amountValue) {

    try {

        wait.until(ExpectedConditions.elementToBeClickable(btnAddCost))
                .click();

        wait.until(ExpectedConditions.visibilityOf(txtItemName));

        txtItemName.clear();
        txtItemName.sendKeys(item);

        txtAmount.clear();
        txtAmount.sendKeys(amountValue);

        Utils.scrollDown(driver, 500);

        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btnSubmit);

        // Wait for alert
        Alert alert =
                wait.until(ExpectedConditions.alertIsPresent());

        System.out.println("Alert = " + alert.getText());

        alert.accept();

        // Wait for Total Rows label
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(normalize-space(),'Total Rows')]")
        ));

        System.out.println("Item added successfully.");

    } catch (Exception e) {

        e.printStackTrace();
        throw new RuntimeException(e);
    }
}


    public void addItemAllFields(String item,
                                 String amountValue,
                                 String date,
                                 String monthName,
                                 String remark) {

        try {

            wait.until(ExpectedConditions.elementToBeClickable(btnAddCost)).click();

            wait.until(ExpectedConditions.visibilityOf(txtItemName));

            txtItemName.clear();
            txtItemName.sendKeys(item);

            btnPlus.click();

            txtAmount.clear();
            txtAmount.sendKeys(amountValue);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].value=arguments[1];",
                    txtPurchaseDate,
                    date
            );

            new Select(drpMonth).selectByVisibleText(monthName);

            txtRemarks.clear();
            txtRemarks.sendKeys(remark);

            Utils.scrollDown(driver, 500);

            wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", btnSubmit);

            // Wait for alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert = " + alert.getText());
            alert.accept();
            System.out.println("Alert Accepted");
//         wait.until(driver -> driver.findElements(By.xpath("//table/tbody/tr")).size() > 0);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(normalize-space(),'Total Rows')]")
            ));


           // Wait until Add Cost button becomes clickable again
//            wait.until(ExpectedConditions.elementToBeClickable(btnAddCost));

            System.out.println("END addItemAllFields");

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Handle Alert
    private void handleAlert() {

        try {

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            System.out.println(alert.getText());

            alert.accept();

        } catch (TimeoutException e) {

            System.out.println("No alert appeared.");

        }
    }

    public boolean isItemPresent(String itemName) {

        By rowsLocator =
                By.xpath("//table/tbody/tr");

        By itemNamesLocator =
                By.xpath("//table/tbody/tr/td[1]");

        By pageButtonsLocator =
                By.cssSelector("button.page-number");

        // ==========================================
        // ALWAYS START FROM PAGE 1
        // ==========================================

        List<WebElement> pageButtons =
                driver.findElements(pageButtonsLocator);

        if (!pageButtons.isEmpty()) {

            WebElement pageOne =
                    driver.findElement(
                            By.cssSelector(
                                    "button.page-number:first-of-type"
                            )
                    );

            if (!pageOne.getAttribute("class")
                    .contains("active")) {

                pageOne.click();

                wait.until(
                        ExpectedConditions.attributeContains(
                                pageOne,
                                "class",
                                "active"
                        )
                );
            }
        }


        // ==========================================
        // GET ALL PAGE BUTTONS
        // ==========================================

        pageButtons =
                driver.findElements(pageButtonsLocator);

        int totalPages =
                pageButtons.size();


        // ==========================================
        // LOOP THROUGH EVERY PAGE
        // ==========================================

        for (int pageIndex = 0;
             pageIndex < totalPages;
             pageIndex++) {

            int pageNumber =
                    pageIndex + 1;

            System.out.println(
                    "\nSearching Page " +
                            pageNumber +
                            " for: " +
                            itemName
            );


            // ==========================================
            // WAIT FOR TABLE
            // ==========================================

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            rowsLocator
                    )
            );


            // ==========================================
            // GET ITEMS FROM CURRENT PAGE
            // ==========================================

            List<WebElement> items =
                    driver.findElements(itemNamesLocator);


            // ==========================================
            // SEARCH CURRENT PAGE
            // ==========================================

            for (WebElement item : items) {

                String currentItem =
                        item.getText().trim();

                System.out.println(
                        "Checking item: " +
                                currentItem
                );

                if (currentItem.equals(itemName)) {

                    System.out.println(
                            "SUCCESS: Item '" +
                                    itemName +
                                    "' found on Page " +
                                    pageNumber
                    );

                    return true;
                }
            }


            // ==========================================
            // GO TO NEXT PAGE
            // ==========================================

            if (pageIndex < totalPages - 1) {

                // Re-find buttons to avoid stale elements
                pageButtons =
                        driver.findElements(
                                pageButtonsLocator
                        );

                WebElement nextPage =
                        pageButtons.get(pageIndex + 1);

                System.out.println(
                        "Clicking Page " +
                                (pageNumber + 1)
                );

                nextPage.click();


                // Wait until the next page becomes active
                final int expectedPage =
                        pageNumber + 1;

                wait.until(driver -> {

                    List<WebElement> updatedButtons =
                            driver.findElements(
                                    pageButtonsLocator
                            );

                    if (updatedButtons.size()
                            < expectedPage) {
                        return false;
                    }

                    return updatedButtons
                            .get(expectedPage - 1)
                            .getAttribute("class")
                            .contains("active");
                });
            }
        }


        // ==========================================
        // ITEM NOT FOUND
        // ==========================================

        System.out.println(
                "FAILED: Item '" +
                        itemName +
                        "' was not found on any page."
        );

        return false;
    }

    public int getTotalRows() {

        By totalRowsLocator =
                By.xpath("//span[contains(normalize-space(), 'Total Rows')]");

        WebElement totalRows =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                totalRowsLocator
                        )
                );

        String text = totalRows.getText().trim();

        System.out.println("Total Rows Label = " + text);

        if (!text.matches("Total Rows:\\s*\\d+")) {
            throw new RuntimeException(
                    "Unexpected Total Rows format: " + text
            );
        }

        return Integer.parseInt(
                text.replaceAll("\\D+", "")
        );
    }

    public void printAllVisibleRows() {

        List<WebElement> rows =
                driver.findElements(
                        By.xpath("//table/tbody/tr")
                );

        System.out.println(
                "Visible table rows = " +
                        rows.size()
        );

        for (WebElement row : rows) {

            System.out.println(
                    "ROW = " +
                            row.getText()
            );
        }
    }


}